import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountsTest {
	Accounts accounts;
	private double APR = 2.5;

	@BeforeEach
	void setUp() {
		accounts = new Accounts(APR);
	}

	@Test
	void depositing_money_from_account() {
		accounts.deposit(15000);

		double actual = accounts.getBalance();
		assertEquals(15000, actual);
	}

	@Test
	void withdrawing_money_from_account() {
		accounts.deposit(10000);
		accounts.withdraw(7500);

		double actual = accounts.getBalance();

		assertEquals(2500, actual);
	}

}