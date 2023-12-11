package banking;

public abstract class Account {

	private final double APR;
	private final int UNIQUE_ID;
	private final String ACCOUNT_TYPE;
	private double balance;
	private int PASS_TIME = 0;

	public Account(String ACCOUNT_TYPE, int UNIQUE_ID, double APR) {
		this.APR = APR;
		this.UNIQUE_ID = UNIQUE_ID;
		this.ACCOUNT_TYPE = ACCOUNT_TYPE;
	}

	public Account(String ACCOUNT_TYPE, int UNIQUE_ID, double APR, double balance) {
		this.APR = APR;
		this.balance = balance;
		this.UNIQUE_ID = UNIQUE_ID;
		this.ACCOUNT_TYPE = ACCOUNT_TYPE;
	}

	public double getBalance() {
		return balance;
	}

	void setBalance(double amount) {
		balance = amount;
	}

	public double getAPR() {
		return APR;

	}

	public int getUNIQUE_ID() {
		return UNIQUE_ID;

	}

	public void deposit(double amount) {
		if (amount >= 0) {
			balance += amount;
		} else {
			System.out.println("Cannot deposit negative amount!");
		}
	}

	public void withdraw(double amount) {
		if (amount >= 0) {
			balance -= amount;
			if (balance < 0) {
				balance = 0;
			}
		} else {
			System.out.println("Cannot withdraw negative amount!");
		}
	}

	public String getAccountType() {
		return ACCOUNT_TYPE;
	}

	public abstract boolean isInMaxWithdrawLimit(double amount);

	public abstract boolean isInMaxDepositLimit(double amount);

	public abstract boolean acceptsTransfer();

	public abstract double getTransferAmount(double amount);

	public int getPassTime() {
		return PASS_TIME;
	}

	public void setPassTime(int TIME) {
		PASS_TIME += TIME;
	}

	public void calculateApr(int months) {
		double decimalAprPerMonth = getAPR() / 100 / 12;
		double newBalance = (getBalance() * decimalAprPerMonth) + getBalance();

		setBalance(newBalance);
	}
}
