import java.util.HashMap;
import java.util.Map;

public class Bank {
	private static Map<Integer, Account> accounts;

	Bank() {
		accounts = new HashMap<>();
	}

	public static Map<Integer, Account> getAccounts() {
		return accounts;
	}

	public void addCheckingAccount(int uniqueID, double apr) {
		accounts.put(uniqueID, new Checking(uniqueID, apr));

	}

	public void addSavingAccount(int uniqueID, double apr) {
		accounts.put(uniqueID, new Saving(uniqueID, apr));
	}

	public void addCDAccount(int uniqueID, double apt, double initialBalance) {
		accounts.put(uniqueID, new CD(uniqueID, apt, initialBalance));
	}

	public Account retrieveAccount(int uniqueId) {
		return accounts.get(uniqueId);
	}

	public void deposit(int uniqueId, double money) {
		retrieveAccount(uniqueId).deposit(money);
	}

	public void withdraw(int uniqueId, double money) {
		Account account = retrieveAccount(uniqueId);
		account.withdraw(money);
	}
}
