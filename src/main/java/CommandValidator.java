public class CommandValidator {

	private final Bank bank;

	public CommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		String[] parts = command.split(" ");
		String accountCommand = parts[0].toLowerCase();

		if ("create".equals(accountCommand)) {
			return isCreateCommandValid(parts);

		} else if ("deposit".equals(accountCommand)) {
			return isDepositCommandValid(parts);
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

		return CDAmount >= 1000 && CDAmount <= 10000;
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

	boolean isCreateCommandValid(String[] parts) {

		return parts.length >= 4 && !doesAccountExist(parts[2]) && hasValidFormat(parts);
	}

	private boolean hasValidFormat(String[] parts) {
		String accountType = parts[1].toLowerCase();
		if (hasValidUniquesIdAndApr(parts[2], parts[3])) {
			if ("saving".equals(accountType) || "checking".equals(accountType) && parts.length == 4) {
				return true;
			} else {
				return "cd".equals(accountType) && parts.length == 5 && isInAmountRange(parts[4]);
			}
		}

		return false;
	}

	private boolean isDepositCommandValid(String[] parts) {
		return parts.length == 3 && bank.accountExistsByUniqueID(Integer.parseInt(parts[1]))
				&& isInDepositLimit(parts[1], parts[2]);
	}

}
