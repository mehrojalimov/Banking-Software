import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SavingTest {

	private static final double APR = 5.5;

	Saving saving;

	@BeforeEach
	void setUp() {
		saving = new Saving(APR);
	}

	@Test
	void checking_account_with_0_balance() {
		saving = new Saving(APR);
		double actual = saving.getBalance();

		assertEquals(0, actual);
	}

	@Test
	void depositing_one_million_dollars() {
		saving.deposit(1000000);

		double actual = saving.getBalance();

		assertEquals(1000000, actual);
	}
}
