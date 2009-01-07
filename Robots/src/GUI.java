import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

public class GUI {	
	private static List robots,files;
	private static Canvas canvas;

	private static void initLists(Group g, final Commands com){
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
		b.addSelectionListener(new SelectionListener(){
			public void widgetSelected(SelectionEvent event)
			{
				StringBuilder cmd=new StringBuilder();
				int index=robots.getSelectionIndex();
				String name = null;
				if(index>=0) name=robots.getItem(index);
				String prog=files.getItem(files.getSelectionIndex());
				cmd.append(String.format("Assign %s with %s",name,prog));
				try
				{
					if(name!=null && prog!=null)
					{
						com.runCommand(cmd.toString());
						if(robots.getItem(index).matches("(\\w+)"))
						{
							robots.remove(name);
							robots.add(name+" " +prog);
						}
						else ErrorPrint.PrintError("another program is assigned to this robot.");
					}
				} catch (FileNotFoundException e)
				{
					System.out.print("Error: file not found.\n");
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}
			public void widgetDefaultSelected(SelectionEvent arg0){}			
		});
	}
	private static void initGroup(Group g, final Commands com)
	{
		// first row
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
		addButton.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0){}
			public void widgetSelected(SelectionEvent arg0)
			{
				String add=null;
				StringBuilder cmd=new StringBuilder();
				cmd.append(String.format("Init type %s at 10 10 named %s",
						comb.getText(),robName.getText()));
				if(armButton.getSelection() && smartButton.getSelection())
					add="all";
				else if(armButton.getSelection())
					add="arm";
				else if(armButton.getSelection())
					add="smartKit";
				if(add!=null) cmd.append(String.format(" with %s",add));
				try
				{
					if(robName.getText()!=null && )
					{
						com.runCommand(cmd.toString());
						robots.add(robName.getText());
					}
					
				} catch (FileNotFoundException e)
				{
					System.out.print("Error: file not found.\n");
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	    });
	}
	public static void main(String[] args) {
	    final Display display = new Display();
	    final Shell shell = new Shell(display);
	    @SuppressWarnings("unused")
		ErrorPrint error=ErrorPrint.getInstance(shell);
	    final Simulation sim=new Simulation();
		final Boxes boxes=new Boxes();
		final Programs programs=new Programs();
		// Robots command object.
		Commands com=new Commands(sim, boxes, programs);
	    shell.setLayout(new GridLayout(2,false));
	    shell.setText("Advanced Programming exercise 1");
	    shell.setSize(700, 600);

	    // -------- Main window parts------------
	    // ==== Menu Bar
	    
	 // Menu Item
	    Menu menuBar, fileMenu, boxMenu;
	    MenuItem fileMenuHeader, boxMenuHeader;
	    final MenuItem fileExitItem;
		final MenuItem startSimItem;
		final MenuItem stopSimItem;
		final MenuItem boxAddItem;
	    menuBar = new Menu(shell, SWT.BAR);
	    fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
	    fileMenuHeader.setText("&File");
	    fileMenu = new Menu(shell, SWT.DROP_DOWN);
	    fileMenuHeader.setMenu(fileMenu);
	    startSimItem = new MenuItem(fileMenu, SWT.PUSH);
	    startSimItem.setText("&Start Simulation");
	    startSimItem.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent e){}
			public void widgetSelected(SelectionEvent event)
			{
				if(event.widget==startSimItem)
				{
					try
					{
						programs.RunAll();
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
	    });
	    stopSimItem = new MenuItem(fileMenu, SWT.PUSH);
	    stopSimItem.setText("&Stop Simulation");
	    stopSimItem.addSelectionListener(new SelectionListener(){
			public void widgetDefaultSelected(SelectionEvent e){}
			public void widgetSelected(SelectionEvent event)
			{
				if(event.widget==stopSimItem)
				{
					for(int i=0;i<programs.size();i++)
						programs.shutDown();
				}
			}
	    });
	    fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileExitItem.setText("E&xit");
	    boxMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
	    boxMenuHeader.setText("&Box");
	    boxMenu = new Menu(shell, SWT.DROP_DOWN);
	    boxMenuHeader.setMenu(boxMenu);
	    boxAddItem = new MenuItem(boxMenu, SWT.PUSH);
	    boxAddItem.setText("&Add Box");
	    shell.setMenuBar(menuBar);
	    SelectionListener listener= new SelectionListener()
	    {
			public void widgetDefaultSelected(SelectionEvent arg0){}
			public void widgetSelected(SelectionEvent event)
			{
				if(event.widget==fileExitItem)
				{
					shell.close();
					display.dispose();
				}
				else if(event.widget==boxAddItem)
				{
					InputDialog dlg = new InputDialog(shell,SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
				    Box tempbox = dlg.open();
				    if(tempbox!=null)
				    {
				    	boxes.AddBox(tempbox);
				    };
				}
			}	    	
	    };
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
	    initGroup(group1,com);
	    
	    // ==== Group 2
	    //set the group of the lists
	    Group group2=new Group(shell,SWT.SHADOW_IN);
	    // set the group layout to be 3 cols 
	    group2.setLayout(new GridLayout(3,false));
	    // init the group components
	    initLists(group2,com);	// init the lists into group2 
	    
	    // ==== the big drawing Canvas
	    canvas=new Canvas(shell,SWT.BORDER);
	    // set the canvas to take 2 cells
	    canvas.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,true,2,1));
	    canvas.setBackground(display.getSystemColor(1));
	    canvas.addPaintListener(new DrawEnvironment(sim, boxes));
	    
	    // --------- end of main window part --------
	    Listener listener1 = new Listener() 
	    {
	        public void handleEvent(Event event) 
	        {
	            int style =
	               SWT.OK | SWT.CANCEL | SWT.ICON_QUESTION | SWT.APPLICATION_MODAL;
	            MessageBox box = new MessageBox(shell, style);
	            box.setMessage("Exit the application?");
	            event.doit = box.open() == SWT.OK;
	        }
	    };
	    display.addListener(SWT.Close, listener1);
	    display.addListener(SWT.Dispose, new Listener() 
	    {
	        public void handleEvent(Event event) {}
	    });
	    shell.addListener(SWT.Close, listener1);
	    shell.addListener(SWT.Dispose, new Listener() 
	    {
	        public void handleEvent(Event event){}
	    });
	    shell.open();
	    Timer timer=new Timer();
	    timer.schedule(new TimerTask(){
	    	public void run() {
	    		if(!shell.isDisposed()){
	    			display.asyncExec(new Runnable(){
						public void run()
						{
							if(!canvas.isDisposed())
							{
								canvas.redraw();
								canvas.update();
							}
						}
		    		});
	    		}
	    	}
	    },1000,1000);
	    Listener mouselistener = new Listener () {
			private boolean isDown;
			private Robot myRob;

			public void handleEvent (Event event) {
				int maxX = canvas.getSize().x;	// max size
				int maxY = canvas.getSize().y;
				int mx=maxX/2,my=maxY/2;	// mid point as (0,0)
				switch (event.type) {
					case SWT.MouseDown:
						isDown=true;
						canvas.redraw();
						for(int i=0;i<sim.GetSize();i++)
						{
							Position pos=sim.robList.get(i).getCurrentPosition();
							pos.x+=mx;
							pos.y=my-pos.y;
							if(isInArea(event.x,event.y,pos.y,pos.x))
							{
								myRob=sim.robList.get(i);
							}
						}
						canvas.redraw();
						canvas.update();
						break;
					case SWT.MouseMove:
						if(isDown && myRob!=null)
						{
							myRob.setPosition(new Position(event.x-mx,event.y+my));
							canvas.redraw();
							canvas.update();
						}
						break;
					case SWT.MouseUp:
						isDown=false;
						break;
				}
			}

			private boolean isInArea(int x, int y, int curry, int currx)
			{
				if(x>=currx-10 && x<=currx+10 && y>=curry-10 && y<=curry+10)
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
	    display.dispose();
	    timer.cancel();
	}
}
