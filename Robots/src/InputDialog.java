import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


/**
 * InputDialog Class.
 * Extends the Dialog class.
 * Class for opening a dialog and getting input from user in GUI.
 */

public class InputDialog extends Dialog
{
	// Local variables.
	private String messageName;
	private String messagePos;
	private String name;
	private Position pos;

	/**
	 * InputDialog Constructor
	 * Description: Creates a new object with provided variables. 
	 */
	public InputDialog(Shell parent, int style)
	{
		// invoke base class Ctor.
		super(parent, style);
		// Set dialog texts.
		setText("Add Box Dialog");
		messageName="Please enter a name for the Box:";
		messagePos="Please enter the new Box position:";
	}
	
	/**
	 * Method: open
	 * Returns: Box
	 * Description: Open method for opening dialog.
	 * returns a created Box with input from user.
	 */
	public Box open()
	{
		// Create a new shell.
		Shell shell = new Shell(getParent(), getStyle());
		shell.setText(getText());
		// Create the Contents for the dialog.
		createContents(shell);
		// Resize and open shell.
		shell.pack();
		shell.open();
		// Get display from provided shell.
		Display display = getParent().getDisplay();
		// GUI loop.
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
		// If box name is not empty.
		if(name!="")
		{
			// Create a new box with given name.
			Box box=new Box(name);
			// if given position is not null, update box's position.
			if(pos!=null)
			{
				box.SetPosition(pos);
			}
			// If it is null, set box's position to 0,0.
			else
			{
				pos=new Position(0,0);
			}
			// Return created Box.
			return box;
		}
		// Print error, if box name is empty.
		ErrorPrint.PrintError("Box name cannot be empty");
		return null;
	}

	/**
	 * Method: createContents
	 * Returns: void
	 * @param shell
	 * Description: Creates the contents of the dialog.
	 */
	private void createContents(final Shell shell)
	{
		// Set shell layout to a 2 column grid.
		shell.setLayout(new GridLayout(2, true));
		// Create a new group.
		Group group1=new Group(shell,SWT.SHADOW_IN | SWT.FILL);
	    // makes it to take as much space it can:
		GridData data=new GridData(SWT.FILL,SWT.CENTER,true,false);
		// Set it to span over 2 columns.
		data.horizontalSpan=2;
	    group1.setLayoutData(data);
	    // set the group layout to be GridLayout with 4 columns.
	    group1.setLayout(new GridLayout(2,false));
	    
	    // Create a new label for getting Box name.
	    Label label = new Label(group1, SWT.NONE);
		label.setText(messageName);
		// Set label to span 2 columns.
		data = new GridData();
		data.horizontalSpan = 2;
		label.setLayoutData(data);

		// Create a new Text field for getting Box name.
		final Text text = new Text(group1, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		// Set text to span 2 columns.
		data.horizontalSpan = 2;
		text.setLayoutData(data);
		
		// Create a new group.
	    Group group2=new Group(shell,SWT.SHADOW_IN | SWT.FILL);
	    data=new GridData(SWT.FILL,SWT.CENTER,true,false);
	    // Set group to span 2 columns.
		data.horizontalSpan=2;
		group2.setLayoutData(data);
	    // set the group layout to be 2 cols 
	    group2.setLayout(new GridLayout(2,false));
		
	    // Create a new label for getting Box position.
		Label label1 = new Label(group2, SWT.NONE);
		label1.setText(messagePos);
		GridData data1 = new GridData();
		// Set label to span 2 columns.
		data1.horizontalSpan = 2;
		label1.setLayoutData(data1);
		
		// Create 2 new text fields for box position.
		final Text text1 = new Text(group2, SWT.BORDER);
		final Text text2 = new Text(group2, SWT.BORDER);
		
		// Create the OK Button.
		Button ok = new Button(shell, SWT.PUSH);
		ok.setText("OK");
		data = new GridData(GridData.FILL_HORIZONTAL);
		ok.setLayoutData(data);
		// Add selection listener to the ok button.
		ok.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent event)
			{
				// Get Box name.
				name = text.getText();
				try
				{
					// Try parsing the inputed position coordinates.
					int x = Integer.parseInt(text1.getText());
					int y = Integer.parseInt(text2.getText());
					// Create a new position from input.
					pos = new Position(x, y);
				} catch (NumberFormatException e) // Catch parsing errors.
				{
					// Print error for invalid position input.
					ErrorPrint.PrintError("numbers entered are not Valid!");
					// Set box name to empty string.
					name ="";
					// Close opened shell.
					shell.close();
				}
				// Close opened shell.
				shell.close();
			}
		});
		
		// Create cancel button.
		Button cancel = new Button(shell, SWT.PUSH);
		cancel.setText("Cancel");
		data = new GridData(GridData.FILL_HORIZONTAL);
		cancel.setLayoutData(data);
		// Add selection listener to cancel button.
		cancel.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent event)
			{
				// Close shell.
				shell.close();
			}
		});

		shell.setDefaultButton(ok);
	}

}
