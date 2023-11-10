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
			if (parts.length == 3 && bank.accountExistsByUniqueID(Integer.parseInt(parts[1]))) {

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

	private boolean depositLimit(String uniqueId, String depositAmountStr) {
		try {
			int accountId = Integer.parseInt(uniqueId);
			Account account = bank.retrieveAccount(accountId);

			if (account != null) {
				String accountType = account.getAccountType().toLowerCase();
				System.out.println(accountType);
				double depositAmount = Double.parseDouble(depositAmountStr); // Initialize depositAmount

				double depositLimit = getDepositLimitForAccountType(accountType);

				if (depositAmount <= depositLimit) {
					return true; // Deposit amount is within the limit
				}
			}
		} catch (NumberFormatException e) {
			return false;
		}

		return false; // Deposit amount exceeds the limit or an error occurred
	}

	private double getDepositLimitForAccountType(String accountType) {
		// Implement logic to retrieve deposit limits for different account types
		// Return 0 if there's no limit or if the account type is not recognized.
		// For example:
		switch (accountType) {
		case "saving":
			return 5000; // Set the deposit limit for Saving accounts
		case "checking":
			return 3000; // Set the deposit limit for Checking accounts
		case "cd":
			return 10000; // Set the deposit limit for CD accounts
		default:
			return 0; // No limit or unrecognized account type
		}
	}

}
