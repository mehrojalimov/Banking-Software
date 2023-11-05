public class CommandValidator {

	private Bank bank;

	public CommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {

		String[] parts = command.split(" ");

		if ("create".equals(parts[0])) {
			if (bank.accountExistsByUniqueID(Integer.parseInt(parts[2]))) {
				return false;
			} else {
				return true;
			}
		} else if ("deposit".equals(parts[0])) {
			if (bank.accountExistsByUniqueID(Integer.parseInt(parts[1]))) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
