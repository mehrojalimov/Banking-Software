public abstract class Account {

	private double balance;
	private double APR;
	private int UNIQUE_ID;

	public Account(int UNIQUE_ID, double APR) {
		this.APR = APR;
		this.UNIQUE_ID = UNIQUE_ID;
	}

	public Account(int UNIQUE_ID, double APR, double balance) {
		this.APR = APR;
		this.balance = balance;
		this.UNIQUE_ID = UNIQUE_ID;
	}

	public double getBalance() {
		return balance;
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

}
