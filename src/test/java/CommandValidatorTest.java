import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandValidatorTest {
	CommandValidator commandValidator;
	Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandValidator = new CommandValidator(bank);
	}

	@Test
	void valid_command() {
		boolean actual = commandValidator.validate("create Checking 12345678 1.5");
		assertTrue(actual);
	}

	@Test
	void duplicate_unique_id_is_invalid() {
		bank.addCheckingAccount(12345678, 2.5);
		boolean actual = commandValidator.validate("create Checking 12345678 2.2");
		assertFalse(actual);
	}
}
