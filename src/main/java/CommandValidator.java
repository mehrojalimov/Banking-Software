public class CommandValidator {

	private Bank bank;

	public CommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		String[] parts = command.split(" ");

		if ("create".equals(parts[0])) {
			if (parts.length >= 4 && !bank.accountExistsByUniqueID(Integer.parseInt(parts[2]))) {
				return true;
			}
		} else if ("deposit".equals(parts[0])) {
			if (parts.length == 3 && bank.accountExistsByUniqueID(Integer.parseInt(parts[1]))) {
				return true;
			}
		}
		return false;
	}

}
