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
	void bank_has_the_correct_checking_with_ID_and_APR_as_in_is_the_command() {
		commandProcessor.process("create checking 11111111 1.2");

		assertNotNull(bank.retrieveAccount(11111111));
		assertEquals(1.2, bank.retrieveAccount(11111111).getAPR());
		assertEquals("checking", bank.retrieveAccount(11111111).getAccountType());
	}

	@Test
	void bank_creates_CD_account_with_right_ID_apr_amount() {
		commandProcessor.process("create CD 12345678 2.2 2000");

		assertNotNull(bank.retrieveAccount(12345678));
		assertEquals(12345678, bank.retrieveAccount(12345678).getUNIQUE_ID());
		assertEquals(2.2, bank.retrieveAccount(12345678).getAPR());
		assertEquals("cd", bank.retrieveAccount(12345678).getAccountType());
		assertEquals(2000, bank.retrieveAccount(12345678).getBalance());
	}

	@Test
	void bank_creates_saving_account_with_correct_id_and_apr() {
		commandProcessor.process("create Saving 99999999 6.6");

		assertNotNull(bank.retrieveAccount(99999999));
		assertEquals(99999999, bank.retrieveAccount(99999999).getUNIQUE_ID());
		assertEquals(6.6, bank.retrieveAccount(99999999).getAPR());
		assertEquals("saving", bank.retrieveAccount(99999999).getAccountType());
	}

}
