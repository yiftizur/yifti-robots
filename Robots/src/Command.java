import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Command interface for the command factory.
 */
public interface Command
{
	// Execute command provided by string.
	public boolean execute(String cmd) throws FileNotFoundException, IOException;
	// Get command name for use as key in hashmap.
	public String getKey();
}
