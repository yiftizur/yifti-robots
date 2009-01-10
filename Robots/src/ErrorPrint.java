import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;


/**
 * ErrorPrint Class.
 * Class for printing errors in messagebox.
 * Class is singleton.
 */
public class ErrorPrint
{
	// Static instance of class.
	private static ErrorPrint instance = null;
	// Static shell for displaying messages.
	private static Shell shell;
	
	/**
	 * Protected Constructor
	 * Description: Constructor cannot be called from outside class.
	 * Ctor assigns provided shell to local variable. 
	 */
	protected ErrorPrint(Shell parent)
	{
		shell=new Shell(parent,parent.getStyle());
	}
	
	/**
	 * Method: getInstance
	 * Returns: ErrorPrint
	 * Description: Method returns the single instance of the class.
	 */
	public static ErrorPrint getInstance(Shell shell)
	{
		if (instance == null)
		{
			instance = new ErrorPrint(shell);
		}
		return instance;
	}
	/**
	 * Method: PrintError
	 * Returns: void
	 * @param message
	 * Description: Method prints out the given message in message box.
	 */
	public static void PrintError(final String message)
	{
		// Create a new thread,
		Thread t=new Thread()
		{
			// Implement the run method.
			public void run()
			{
				// Create new MsgBox.
				MessageBox box = new MessageBox(shell, SWT.OK | SWT.ERROR | SWT.APPLICATION_MODAL);
				box.setText("Error!");
				box.setMessage(message);
				// Open MsgBox.
				box.open();
			}
		};
		// Sync thread execution with display.
		shell.getDisplay().asyncExec(t);
	}
}
