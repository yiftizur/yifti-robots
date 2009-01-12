/*
* Name - Yiftach Tzur
* ID - 043372523
* Group - 89-210-02
*/

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * 
 * General GUI Class for representing all robots,boxes and their interactions
 * in a Graphical interface.
 */
public class GUI {	
	private static List robots,files;
	private static Canvas canvas;
	static boolean simIsRunning=false;
	protected static Programs progs;
	protected static Boxes boxes;
	protected static Simulation sim;
	
	/*
	 * Initializer for the right most group in the display Layout.
	 * Creates the Robots and Files lists, and the assign button.
	 * Add selection listener to the Assign button.
	 */
	private static void initLists(Group g)
	{
		// headlines:
		new Label(g,SWT.NONE).setText("Robots List");
		new Label(g,SWT.NONE);	// blank
		new Label(g,SWT.NONE).setText("Files List");
		
		// list button list
		robots=new List(g,SWT.BORDER | SWT.V_SCROLL);
		robots.setLayoutData(new GridData(150,80));
		Button b=new Button(g,SWT.PUSH);
		b.setText("<-");
		files=new List(g,SWT.BORDER | SWT.V_SCROLL);		
		files.setLayoutData(new GridData(150,80));
		File f=new File(".");
		files.setItems(f.list());
		// Create listener for the assign button.
		b.addSelectionListener(new SelectionListener()
		{
			public void widgetSelected(SelectionEvent event)
			{
				// Check if simulation is running.
				if (!simIsRunning)
				{
					String name=robots.getSelection()[0];
					String prog=files.getSelection()[0];
					new AssignCmd(name,prog, sim, boxes, progs,robots).execute();
				}
			}
			public void widgetDefaultSelected(SelectionEvent arg0){}			
		});
	}
	
	/*
	 * Initializer for the left most group in the display Layout.
	 * Creates the Add robot form with its buttons and fields.
	 * Assigns various listeners to the different buttons.
	 */
	
	private static void initGroup(Group g)
	{
		// first row
		// Label - Text - Check Button - Button
		new Label(g,SWT.CENTER).setText("Name");
		
		final Text robName=new Text(g,SWT.SINGLE);
		robName.setLayoutData(new GridData(SWT.CENTER,SWT.CENTER,false,true));
		
		final Button armButton=new Button(g, SWT.CHECK | SWT.CENTER);
		armButton.setText("Add arm");
		GridData grid=new GridData();
		grid.grabExcessHorizontalSpace=true;
		grid.horizontalIndent=21;
		grid.verticalAlignment=SWT.CENTER;
		armButton.setLayoutData(grid);
		
		Button addButton=new Button(g,SWT.PUSH | SWT.CENTER);
		addButton.setText("Add");
		grid=new GridData();
		grid.grabExcessHorizontalSpace=true;
		grid.grabExcessVerticalSpace=true;
		grid.verticalSpan=2;
	    addButton.setLayoutData(grid);
	    
	    //Second Row.
	    // Label - Drop Down List - Check Button
		new Label(g,SWT.CENTER).setText("Type");
		
		final Combo comb=new Combo(g,SWT.DROP_DOWN | SWT.READ_ONLY | SWT.CENTER);
		String[] items = "RV1 RV2 Aibo".split(" ");
		comb.setItems(items);
		comb.select(0);
		comb.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,true));
		
		final Button smartButton=new Button(g,SWT.CHECK | SWT.CENTER);
		smartButton.setText("Smart robot");
		grid=new GridData();
		grid.grabExcessHorizontalSpace=true;
		grid.horizontalIndent=21;
		grid.verticalAlignment=SWT.CENTER;
		grid.verticalAlignment=SWT.CENTER;
		smartButton.setLayoutData(grid);
		// Add selection listener to the Add robot button.
		addButton.addSelectionListener(new SelectionListener()
		{
			public void widgetDefaultSelected(SelectionEvent arg0){}
			public void widgetSelected(SelectionEvent arg0)
			{
				// Check if simulation is running.
				if (!simIsRunning)
				{
					String name=robName.getText();
					String add="";
					String type=comb.getItem(comb.getSelectionIndex());
					if(armButton.getSelection() && smartButton.getSelection())
						add="all";
					else if(armButton.getSelection())
						add="arm";
					else if(smartButton.getSelection())
						add="smart";
					new AddRobCmd(sim,name,type,add,robots).execute();
				}
			}
		});
	}
	
	/**
	 * Method: main
	 * Returns: void
	 * @param args
	 * Description: Main function for GUI section, creates shell and display, and displays
	 * the different components of the program on screen.
	 * Shell Layout is created as a 2 column grid.
	 */
	public static void main(String[] args)
	{
		// Create all needed variables.
	    final Display display = new Display();
	    final Shell shell = new Shell(display);
	    // Init Static Error Printing class.
	    ErrorPrint.getInstance(shell);
	    sim=new Simulation();
		boxes=new Boxes();
		progs=new Programs();
		// Robots command object.
		// Set Shell Layout.
	    shell.setLayout(new GridLayout(2,false));
	    shell.setText("Advanced Programming exercise 1");
	    shell.setSize(700, 600);

	    // -------- Main window parts------------
	    // ==== Menu Bar
	    
	 // Menu Item
	    // Declare Menu objects, and MenuItem objects.
	    Menu menuBar, fileMenu, boxMenu;
	    MenuItem fileMenuHeader, boxMenuHeader;
	    final MenuItem fileExitItem;
		final MenuItem startSimItem;
		final MenuItem stopSimItem;
		final MenuItem boxAddItem;
		// Create new Menu Bar for shell.
	    menuBar = new Menu(shell, SWT.BAR);
	    // Create new Menu item in new menu bar.
	    fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
	    fileMenuHeader.setText("&File");
	    fileMenu = new Menu(shell, SWT.DROP_DOWN);
	    fileMenuHeader.setMenu(fileMenu);
	    // Create Start Simulation menu option.
	    startSimItem = new MenuItem(fileMenu, SWT.PUSH);
	    startSimItem.setText("&Start Simulation");
	    // Add selection listener to menu option.
	    SelectionListener startsimlist=new SelectionListener()
	    {
			public void widgetSelected(SelectionEvent event)
			{
				// check widget is selected.
				if(event.widget==startSimItem)
				{
					// Start all assigned programs and robots.
					new StrtSimCmd(progs).execute();
					simIsRunning=true;
				}
			}
			public void widgetDefaultSelected(SelectionEvent e){}
	    };
	    startSimItem.addSelectionListener(startsimlist);
	    // Create Stop Simulation menu option.
	    stopSimItem = new MenuItem(fileMenu, SWT.PUSH);
	    stopSimItem.setText("&Stop Simulation");
	    // Add selection listenr to menu option.
	    stopSimItem.addSelectionListener(new SelectionListener(){
			public void widgetDefaultSelected(SelectionEvent e){}
			public void widgetSelected(SelectionEvent event)
			{
				if(event.widget==stopSimItem)
				{
					// Stop all running programs and robots.
					new StpSimCmd(progs).execute();
					simIsRunning=false;
				}
			}
	    });
	    // Create Exit menu option.
	    fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileExitItem.setText("E&xit");
	    // Create Box menu.
	    boxMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
	    boxMenuHeader.setText("&Box");
	    boxMenu = new Menu(shell, SWT.DROP_DOWN);
	    boxMenuHeader.setMenu(boxMenu);
	    // Create Add Box Menu item.
	    boxAddItem = new MenuItem(boxMenu, SWT.PUSH);
	    boxAddItem.setText("&Add Box");
	    shell.setMenuBar(menuBar);
	    // Create Selection Listener.
	    SelectionListener listener= new SelectionListener()
	    {
			public void widgetDefaultSelected(SelectionEvent arg0){}
			public void widgetSelected(SelectionEvent event)
			{
				// If exit menu option is chosen.
				if(event.widget==fileExitItem)
				{
					// Check simulation is not running.
					if (!simIsRunning) 
					{
						new ExitCmd(shell).execute();
					}
					// If simulation is running, print error message.
					else ErrorPrint.PrintError("Please Stop Simulation before exiting.");
				}
				// Check if Add Box menu option is selected.
				else if(event.widget==boxAddItem)
				{
					// Check simulation is not running.
					if (!simIsRunning)
					{
						new AddBoxCmd(boxes,shell).execute();
						
					}
					// If simulation still running, print error message.
					else ErrorPrint.PrintError("Please stop simulation before adding a Box.");
				}
			}	    	
	    };
	    // Add created listener to both Exit and Add Box menu items.
		fileExitItem.addSelectionListener(listener);
		boxAddItem.addSelectionListener(listener);
	    // ==== Group 1
	    //set the group of create robot
	    Group group1=new Group(shell,SWT.SHADOW_IN);
	    group1.setText("Create Robot");
	    // makes it to take as much space it can:
	    group1.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,true,false));
	    // set the group layout to be GridLayout with 4 columns.
	    group1.setLayout(new GridLayout(4,false));
	    /* call an "init" method to insert components into group1,
	     * see what happens in group2, to get a hint on how to do it.
	     */
	    initGroup(group1);
	    
	    // ==== Group 2
	    //set the group of the lists
	    Group group2=new Group(shell,SWT.SHADOW_IN);
	    // set the group layout to be 3 cols 
	    group2.setLayout(new GridLayout(3,false));
	    // init the group components
	    initLists(group2);	// init the lists into group2 
	    
	    // ==== the big drawing Canvas
	    canvas=new Canvas(shell,SWT.BORDER);
	    // set the canvas to take 2 cells
	    canvas.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,true,2,1));
	    canvas.setBackground(display.getSystemColor(1));
	    canvas.addPaintListener(new DrawEnvironment(sim, boxes));
	    
	    // --------- end of main window part --------
	    // Create a new listener for Closing the Shell event.
	    Listener listener1 = new Listener() 
	    {
	        public void handleEvent(Event event) 
	        {
	        	// Check simulation is not running.
	            if (!simIsRunning) 
	            {
	            	// Pop message box for verification.
					int style = SWT.OK | SWT.CANCEL | SWT.ICON_QUESTION
							| SWT.APPLICATION_MODAL;
					MessageBox box = new MessageBox(shell, style);
					box.setMessage("Exit the application?");
					// Handle event if OK pressed.
					event.doit = box.open() == SWT.OK;
				}
	            else
	            {
	            	// Print error message if simulation is still running.
	            	ErrorPrint.PrintError("Please stop simulation before exiting.");
	            	event.doit=false;
	            }
	        }
	    };
	    // Add created listener to Display.
	    display.addListener(SWT.Close, listener1);
	    // Add dispose listener to display.
	    display.addListener(SWT.Dispose, new Listener() 
	    {
	        public void handleEvent(Event event) {}
	    });
	    // Add created listener to Shell.
	    shell.addListener(SWT.Close, listener1);
	    // Add dispose listener to shell.
	    shell.addListener(SWT.Dispose, new Listener() 
	    {
	        public void handleEvent(Event event){}
	    });
	    // Open Shell.
	    shell.open();
	    // Create and run a new TimerTask to redraw canvas every 1 second.
	    Timer timer=new Timer();
	    timer.schedule(new TimerTask()
		{
	    	// Implement run method.
			public void run()
			{
				// Check shell is not disposed.
				if (!shell.isDisposed())
				{
					// Create new Runnable - Synchronized with display.
					display.asyncExec(new Runnable()
					{
						public void run()
						{
							// Check Canvas is not disposed, and redraw it.
							if (!canvas.isDisposed())
							{
								canvas.redraw();
								canvas.update();
							}
						}
					});
				}
			}
		}, 1000, 1000);
	    
	    // Create new Mouse Listener.
	    Listener mouselistener = new Listener ()
	    {
	    	// local Robot pointer.
			private Robot myRob;
			
			/**
			 * Method: handleEvent
			 * Overrides: @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
			 * Returns: None
			 * Description: Handles sent event to listener.
			 */
			public void handleEvent(Event event)
			{
				int maxX = canvas.getSize().x; // max size
				int maxY = canvas.getSize().y;
				int mx = maxX / 2, my = maxY / 2; // mid point as (0,0)
				// Switch case on event type.
				switch (event.type)
				{
				// If event is mouse down.
				case SWT.MouseDown:
					// Go over all robots in simulation.
					for (int i = 0; i < sim.GetSize(); i++)
					{
						// Get current robot position.
						Position pos = new Position(sim.robList.get(i)
								.getCurrentPosition());
						// Check if mouse is clicked in Robots circle area.
						if (isInArea(event.x, event.y, my - pos.y, pos.x + mx)
								&& !simIsRunning)
						{
							// Set robot pointer to found robot.
							myRob = sim.robList.get(i);
						}
					}
					break;
				// Case event is mouseMove.
				case SWT.MouseMove:
					// Check a robot was chosen.
					if (myRob != null)
					{
						// Set Robot's location according to the mouse location.
						myRob.setPosition(new Position(event.x-mx, my-event.y));
						// Redraw and update the canvas.
						canvas.redraw();
						canvas.update();
					}
					break;
				// Case event is mouseup
				case SWT.MouseUp:
					// Set robot pointer to null.
					myRob = null;
					break;
				}
			}

			/**
			 * Method: isInArea
			 * Returns: boolean
			 * Description: Returns true if distance between given coordinates is 8.
			 */
			
			private boolean isInArea(int x, int y, int curry, int currx)
			{
				// Get deficits between current coordinates, and starting ones.
				double distX = x - currx;
				double distY = y - curry;
				// Calculate distance moved.
				if (Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2)) <= 8)
					return true;
				return false;
			}
		};
		canvas.addListener (SWT.MouseDown, mouselistener);
		canvas.addListener (SWT.MouseUp, mouselistener);
		canvas.addListener (SWT.MouseMove, mouselistener);
	    // Set up the event loop.
	    while (!shell.isDisposed()) {
	      if (!display.readAndDispatch()) {
	        // If no more entries in the event queue
	        display.sleep();
	      }
	    }
	 // Make sure all threads of program are stopped.
	    progs.shutDown();
	    // Stop the canvas redraw timer.
	    timer.cancel();
	    // dispose of display.
	    display.dispose();
	}
}
