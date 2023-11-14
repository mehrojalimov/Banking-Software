import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandProcessorTest {
	CommandProcessor commandProcessor;
	Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandProcessor = new CommandProcessor(bank);
	}

	@Test
	void bank_has_the_correct_ID_and_APR_as_in_is_the_command() {
		commandProcessor.process("create checking 11111111 1.2");

		// createChecking(11111111, 1.2)

		assertNotNull(bank.retrieveAccount(11111111));
		assertEquals(1.2, bank.retrieveAccount(11111111).getAPR());
		assertEquals("checking", bank.retrieveAccount(11111111).getAccountType());
	}
}
