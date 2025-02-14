package banking;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public abstract class Account {

	protected final double APR;
	protected final int UNIQUE_ID;
	protected final String ACCOUNT_TYPE;
	protected final ArrayList<String> validCommands;
	protected int passTime;
	protected double balance;
	protected int LAST_WITHDRAW_MONTH = -1;

	protected Account(String ACCOUNT_TYPE, int UNIQUE_ID, double APR) {
		this.APR = APR;
		this.UNIQUE_ID = UNIQUE_ID;
		this.ACCOUNT_TYPE = ACCOUNT_TYPE;
		this.validCommands = new ArrayList<>();
		this.validCommands.add(currentState());
		this.passTime = 0;
	}

	protected Account(String ACCOUNT_TYPE, int UNIQUE_ID, double APR, double balance) {
		this.APR = APR;
		this.balance = balance;
		this.UNIQUE_ID = UNIQUE_ID;
		this.ACCOUNT_TYPE = ACCOUNT_TYPE;
		this.validCommands = new ArrayList<>();
		this.validCommands.add(currentState());
		this.passTime = 0;
	}

	public static String capitalizeFirstLetter(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
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
		if (amount > 0) {
			balance += amount;
		}
	}

	public void withdraw(double amount) {
		if (amount > 0) {
			balance -= amount;
			if (balance < 0) {
				balance = 0;
			}
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
		return this.passTime;
	}

	public void setPassTime(int TIME) {
		this.passTime += TIME;
	}

	public void calculateApr(int months) {
		double decimalAprPerMonth = getAPR() / 100 / 12;

		for (int z = 0; z < months; z++) {
			double newBalance = (getBalance() * decimalAprPerMonth) + getBalance();
			setBalance(newBalance);
		}
	}

	int getLastWithdrawMonth() {
		return LAST_WITHDRAW_MONTH;
	}

	void setLastMonthWithdrawMonth(int newMonth) {
		LAST_WITHDRAW_MONTH = newMonth;
	}

	public void addValidCommand(String command) {
		validCommands.add(command);
	}

	public String currentState() {
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		decimalFormat.setRoundingMode(RoundingMode.FLOOR);
		String myString;
		myString = capitalizeFirstLetter(getAccountType()) + " " + getUNIQUE_ID() + " "
				+ decimalFormat.format(getBalance()) + " " + decimalFormat.format(getAPR());

		return myString;
	}

	public ArrayList<String> getValidCommands() {
		this.validCommands.set(0, currentState());
		return this.validCommands;
	}
}
