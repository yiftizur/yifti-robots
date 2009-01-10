import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

/**
 * AddBoxCmd Class.
 * Implementation of the GCommand interface - the AddBoxt command for adding a 
 * new box.
 */

public class AddBoxCmd implements GCommand
{
	// Local variables.
	private Boxes boxes;
	private Shell shell;

	/**
	 * @param boxes
	 * AddBoxCmd Constructor
	 * Description: Creates object with given parameters.
	 */
	public AddBoxCmd(Boxes boxes, Shell shell)
	{
		this.boxes=boxes;
		this.shell=shell;
	}

	/**
	 * Method: execute
	 * Overrides: @see GCommand#execute()
	 * Returns: None.
	 * Description: Runs the command, adding a new box to the Boxes object.
	 */
	@Override
	public void execute()
	{
		// Create new input dialog.
		InputDialog dlg = new InputDialog(shell,
				SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		// Get result from input dialog - should be a created Box.
		Box tempbox = dlg.open();
		// If a box was created, add to Boxes class.
		if (tempbox != null)
		{
			boxes.AddBox(tempbox);
		}
	}

}
