import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandStorageTest {

	CommandStorage commandStorage;

	@BeforeEach
	void setUp() {
		commandStorage = new CommandStorage();
	}

	@Test
	void storing_invalid_commands() {
		commandStorage.addCommand("Create 1111111 11.5");
		assertEquals(1, commandStorage.getAllCommands().size());
		assertEquals("Create 1111111 11.5", commandStorage.getAllCommands().get(0));
	}

	@Test
	void stores_multiple_invalid_commands() {
		commandStorage.addCommand("Checking 11111111 3.3");
		commandStorage.addCommand("Deposit 1111111");
		commandStorage.addCommand("Create CD 12345678 3.3");

		assertEquals(3, commandStorage.getAllCommands().size());
		assertTrue(commandStorage.getAllCommands().contains("Checking 11111111 3.3"));
		assertTrue(commandStorage.getAllCommands().contains("Deposit 1111111"));
		assertTrue(commandStorage.getAllCommands().contains("Create CD 12345678 3.3"));

	}
}
