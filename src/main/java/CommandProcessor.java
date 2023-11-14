public class CommandProcessor {

	private final Bank bank;

	public CommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void process(String command) {
		String[] parts = command.split(" ");
		String commandType = parts[0].toLowerCase();

		if (commandType.equals("create")) {
			processCreateCommand(parts);
		}
	}

	private void processCreateCommand(String[] parts) {
		String accountType = parts[1].toLowerCase();
		int uniqueId = Integer.parseInt(parts[2]);
		double apr = Double.parseDouble(parts[3]);

		switch (accountType) {
		case "checking":
			bank.addCheckingAccount(uniqueId, apr);
			break;
		case "cd":
			double amount = Double.parseDouble(parts[4]);
			bank.addCDAccount(uniqueId, apr, amount);
			break;
		case "saving":
			bank.addSavingAccount(uniqueId, apr);
			break;
		}
	}
}
