import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;


public class ErrorPrint
{
	private static ErrorPrint instance = null;
	private static Shell shell;

	protected ErrorPrint(Shell parent)
	{
		shell=new Shell(parent,parent.getStyle());
	}

	public static ErrorPrint getInstance(Shell shell)
	{
		if (instance == null)
		{
			instance = new ErrorPrint(shell);
		}
		return instance;
	}
	public static void PrintError(String message)
	{
		MessageBox box = new MessageBox(shell, SWT.OK | SWT.APPLICATION_MODAL);
	    box.setMessage(message);
	    int rc = box.open();
	}
}
