
/**
 * Command interface for the command factory.
 */
public interface Command
{
	// Execute command provided by string.
	public boolean execute(String cmd);
	// Get command name for use as key in hashmap.
	public String getKey();
}
