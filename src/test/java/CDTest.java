import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CDTest {

	private static final double APR = 3.2;
	private static final double BALANCE = 1000.00;
	CD cd;

	@BeforeEach
	void setUp() {
		cd = new CD(APR, BALANCE);
	}

	@Test
	void get_the_balance_of_cd() {

		double actual = cd.getBalance();

		assertEquals(1000, actual);

	}
}
