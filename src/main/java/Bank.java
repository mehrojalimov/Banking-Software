import java.util.HashMap;
import java.util.Map;

public class Bank {
    private static Map<Integer, Accounts> accounts;

    Bank() {
        accounts = new HashMap<>();
    }

    public static Map<Integer, Accounts> getAccounts() {
        return accounts;
    }

    public void addCheckingAccount(int uniqueID, double apr) {
        accounts.put(uniqueID, new Checking(apr));

    }

    public void addSavingAccount(int uniqueID, double apr) {
        accounts.put(uniqueID, new Saving(apr));
    }

    public void addCDAccount(int uniqueID, double apt, double initialBalance) {
        accounts.put(uniqueID, new CD(apt, initialBalance));
    }

    public Accounts retrieveAccount(int uniqueId) {
        return accounts.get(uniqueId);
    }

    public void deposit(int uniqueId, double money) {
        retrieveAccount(uniqueId).deposit(money);
    }

    public void withdraw(int uniqueId, double money) {
        Accounts account = retrieveAccount(uniqueId);
        account.withdraw(money);
    }
}
