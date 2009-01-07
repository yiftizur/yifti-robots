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

public class InputDialog extends Dialog
{
	private String messageName;
	private String messagePos;
	private String name;
	private Position pos;

	/**
	 * @param parent
	 * @param style
	 *            Constructor Description:
	 */
	public InputDialog(Shell parent, int style)
	{
		super(parent, style);
		setText("Add Box Dialog");
		messageName="Please enter a name for the Box:";
		messagePos="Please enter the new Box position:";
	}
	public Box open()
	{
		Shell shell = new Shell(getParent(), getStyle());
		shell.setText(getText());
		createContents(shell);
		shell.pack();
		shell.open();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
		
		if(name!=null)
		{
			Box box=new Box(name);
			if(pos!=null)
			{
				box.SetPosition(pos);
			}
			else
			{
				pos=new Position(0,0);
			}
			return box;
		}
		return null;
	}

	private void createContents(final Shell shell)
	{
		shell.setLayout(new GridLayout(2, true));
		Group group1=new Group(shell,SWT.SHADOW_IN | SWT.FILL);
	    // makes it to take as much space it can:
		GridData data=new GridData(SWT.FILL,SWT.CENTER,true,false);
		data.horizontalSpan=2;
	    group1.setLayoutData(data);
	    // set the group layout to be GridLayout with 4 columns.
	    group1.setLayout(new GridLayout(2,false));

	    Label label = new Label(group1, SWT.NONE);
		label.setText(messageName);
		data = new GridData();
		data.horizontalSpan = 2;
		label.setLayoutData(data);

		final Text text = new Text(group1, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		text.setLayoutData(data);
		
	    Group group2=new Group(shell,SWT.SHADOW_IN | SWT.FILL);
	    data=new GridData(SWT.FILL,SWT.CENTER,true,false);
		data.horizontalSpan=2;
		group2.setLayoutData(data);
	    // set the group layout to be 3 cols 
	    group2.setLayout(new GridLayout(2,false));
		
		Label label1 = new Label(group2, SWT.NONE);
		label1.setText(messagePos);
		GridData data1 = new GridData();
		data1.horizontalSpan = 2;
		label1.setLayoutData(data1);
		
		final Text text1 = new Text(group2, SWT.BORDER);
		final Text text2 = new Text(group2, SWT.BORDER);

		Button ok = new Button(shell, SWT.PUSH);
		ok.setText("OK");
		data = new GridData(GridData.FILL_HORIZONTAL);
		ok.setLayoutData(data);
		ok.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent event)
			{
				name = text.getText();
				pos=new Position(text1.getText(), text2.getText());
				shell.close();
			}
		});

		Button cancel = new Button(shell, SWT.PUSH);
		cancel.setText("Cancel");
		data = new GridData(GridData.FILL_HORIZONTAL);
		cancel.setLayoutData(data);
		cancel.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent event)
			{
				shell.close();
			}
		});

		shell.setDefaultButton(ok);
	}

}
