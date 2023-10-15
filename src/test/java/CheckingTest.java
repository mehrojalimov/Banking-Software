import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckingTest {

	Checking checking;
	private double APR = 5.5;

	@BeforeEach
	void setUp() {
		checking = new Checking(APR);
	}

	@Test
	void checking_account_with_0_balance() {
		double actual = checking.getBalance();

		assertEquals(0, actual);
	}

	@Test
	void creating_account_with_APR() {
		double actual = checking.getAPR();

		assertEquals(5.5, actual);
	}

	@Test
	void deposit_50_dollars_to_the_balance() {
		checking.deposit(50);

		double actual = checking.getBalance();
		assertEquals(50, actual);
	}

	@Test
	void withdraw_money_from_balance() {
		checking.withdraw(10);

		double actual = checking.getBalance();
		assertEquals(0, actual);

	}

	@Test
	void deposit_twice_into_account() {
		checking.deposit(50.50);
		checking.deposit(150.50);

		double actual = checking.getBalance();

		assertEquals(201.00, actual);
	}

	@Test
	void withdraw_twice_from_account() {
		checking.deposit(1000);
		checking.withdraw(200);
		checking.withdraw(100);

		double actual = checking.getBalance();

		assertEquals(700, actual);
	}

}
