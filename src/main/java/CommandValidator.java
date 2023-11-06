public class CommandValidator {

	private Bank bank;

	public CommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		String[] parts = command.split(" ");

		if ("create".equals(parts[0])) {
			if (parts.length >= 4 && !bank.accountExistsByUniqueID(Integer.parseInt(parts[2]))
					&& hasValidUniquesIdAndApr(parts[2], parts[3])) {
				if ("Saving".equals(parts[1]) || "Checking".equals(parts[1]) && parts.length == 4) {
					return true;
				} else if ("CD".equals(parts[1]) && parts.length == 5 && isInAmountRange(parts[4])) {
					return true;
				}
			}

		} else if ("deposit".equals(parts[0])) {
			if (parts.length == 3 && bank.accountExistsByUniqueID(Integer.parseInt(parts[1]))) {

				return true;
			}
		}
		return false;
	}

	private boolean hasValidUniquesIdAndApr(String uniqueId, String apr) {
		boolean validUniqueId = uniqueId.matches("^\\d{8}$");

		double aprValue = Double.parseDouble(apr);
		boolean validApr = aprValue >= 0 && aprValue <= 10;

		return validUniqueId && validApr;
	}

	private boolean isInAmountRange(String amount) {
		double CDAmount = Double.parseDouble(amount);

		boolean correctRange = CDAmount >= 1000 && CDAmount <= 10000;

		return correctRange;
	}

}
