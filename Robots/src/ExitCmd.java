import org.eclipse.swt.widgets.Shell;

public class ExitCmd implements GCommand
{

	private Shell shell;

	/**
	 * @param shell Constructor
	 * Description: 
	 */
	public ExitCmd(Shell shell)
	{
		this.shell=shell;
	}

	/**
	 * Method: execute
	 * Overrides: @see GCommand#execute()
	 * Returns: 
	 * Description:
	 */
	@Override
	public void execute()
	{
		// Close shell.
		shell.close();
	}

}
