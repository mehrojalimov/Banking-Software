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

	@Test
	void deposit_money_to_an_account_is_valid() {
		bank.addSavingAccount(11111111, 1.1);
		boolean actual = commandValidator.validate("deposit 11111111 1000");
		assertTrue(actual);
	}

	@Test
	void creating_account_without_apr_is_invalid() {
		boolean actual = commandValidator.validate("create Checking 12345677");
		assertFalse(actual);
	}
}
