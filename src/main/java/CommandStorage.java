import java.util.ArrayList;
import java.util.List;

public class CommandStorage {

	private final List<String> storedCommands;

	public CommandStorage() {
		this.storedCommands = new ArrayList<>();
	}

	public void addCommand(String command) {
		storedCommands.add(command);
	}

	public List<String> getAllCommands() {
		return new ArrayList<>(storedCommands);
	}
}
