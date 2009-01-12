import org.eclipse.swt.widgets.Shell;

/**
 * ExitCmd Class.
 * Implementation of the GCommand interface - the Exit command for exiting program.
 */

public class ExitCmd implements GCommand
{
	// Local shell.
	private Shell shell;

	/**
	 * ExitCmd Constructor
	 * Description: creates new object with provided shell.
	 */
	public ExitCmd(Shell shell)
	{
		this.shell=shell;
	}

	/**
	 * Method: execute
	 * Overrides: @see GCommand#execute()
	 * Returns: None
	 * Description: Runs the Exit command - closes the shell.
	 */
	@Override
	public void execute()
	{
		// Close shell.
		shell.close();
	}

}
