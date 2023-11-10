public class CommandValidator {

	private Bank bank;

	public CommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		String[] parts = command.split(" ");
		String accountCommand = parts[0].toLowerCase();
		String accountType = parts[1].toLowerCase();

		if ("create".equals(accountCommand)) {
			if (parts.length >= 4 && !doesAccountExist(parts[2]) && hasValidUniquesIdAndApr(parts[2], parts[3])) {
				if ("saving".equals(accountType) || "checking".equals(accountType) && parts.length == 4) {
					return true;
				} else if ("cd".equals(accountType) && parts.length == 5 && isInAmountRange(parts[4])) {
					return true;
				}
			}

		} else if ("deposit".equals(accountCommand)) {
			if (parts.length == 3 && bank.accountExistsByUniqueID(Integer.parseInt(parts[1]))
					&& isInDepositLimit(parts[1], parts[2])) {

				return true;
			}
		}
		return false;
	}

	private boolean hasValidUniquesIdAndApr(String uniqueId, String apr) {
		try {
			boolean validUniqueId = uniqueId.matches("^\\d{8}$");
			double aprValue = Double.parseDouble(apr);

			boolean validApr = aprValue >= 0 && aprValue <= 10;

			return validUniqueId && validApr;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean isInAmountRange(String amount) {
		double CDAmount = Double.parseDouble(amount);

		boolean correctRange = CDAmount >= 1000 && CDAmount <= 10000;

		return correctRange;
	}

	private boolean doesAccountExist(String uniqueId) {
		try {
			int accountId = Integer.parseInt(uniqueId);
			return bank.accountExistsByUniqueID(accountId);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean isInDepositLimit(String uniqueId, String depositAmountStr) {
		try {
			int accountId = Integer.parseInt(uniqueId);
			Account account = bank.retrieveAccount(accountId);

			if (account != null) {
				String accountType = account.getAccountType().toLowerCase();
				System.out.println(accountType);
				double depositAmount = Double.parseDouble(depositAmountStr); // Initialize depositAmount

				double depositLimit = getDepositLimitForAccountType(accountType);

				if (depositAmount <= depositLimit && depositAmount >= 0) {
					return true; // Deposit amount is within the limit
				}
			}
		} catch (NumberFormatException e) {
			return false;
		}

		return false;
	}

	private double getDepositLimitForAccountType(String accountType) {
		switch (accountType) {
		case "saving":
			return 2500;
		case "checking":
			return 1000;
		default:
			return -1;
		}
	}

}
