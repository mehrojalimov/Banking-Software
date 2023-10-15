import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {

	public static final int UNIQUE_ID = 14567895;
	private static final double APR = 5.5;
	Bank bank;

	void createAccounts() {
		bank.addCDAccount(11111111, 2.53, 10000);
		bank.addCheckingAccount(UNIQUE_ID, APR);
	}

	@BeforeEach
	void setUp() {
		bank = new Bank();
	}

	@Test
	void bank_has_no_accounts_initially() {
		assertTrue(Bank.getAccounts().isEmpty());
	}

	@Test
	void add_one_checking_account_to_the_bank() {
		bank.addCheckingAccount(UNIQUE_ID, APR);

		assertEquals(1, bank.getAccounts().size());
	}

	@Test
	void add_two_accounts_into_university() {
		createAccounts();

		assertEquals(2, bank.getAccounts().size());

	}

	@Test
	void retrieving_one_account_from_bank_retrieves_the_correct_account() {
		createAccounts();

		double actual = bank.retrieveAccount(UNIQUE_ID).getAPR();

		assertEquals(APR, actual);

	}

	@Test
	void depositing_money_by_ID_through_bank() {
		createAccounts();

		bank.deposit(UNIQUE_ID, 1000.75);
		bank.deposit(UNIQUE_ID, 500);

		double actual = bank.retrieveAccount(UNIQUE_ID).getBalance();

		assertEquals(1500.75, actual);
	}

	@Test
	void withdraw_money_by_ID_through_bank() {
		createAccounts();

		bank.withdraw(11111111, 5000);

		double actual = bank.retrieveAccount(11111111).getBalance();

		assertEquals(5000, actual);
	}

	@Test
	void depositing_twice_through_bank() {
		createAccounts();
	}

}
