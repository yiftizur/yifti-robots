import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Canvas;


/**
 * Class: RobotSuper.
 * Description: Class for all robot methods and members.
 * All robot types inherit from this class for code reuse.
 */
public class RobotSuper implements Robot
{
	private Position start,current; 
	private int speed,heading;
	// Speed limit imposed on movement.
	protected int speedLimit=0;
	// Heading limit imposed on the angle.
	protected int headingLimit=0;
	// Robot name.
	protected String name;
	protected String fName;
	private HashMap<Integer, String> lines;
	/**
	 * @return the program
	 */
	public String getProgram()
	{
		return fName;
	}
	/**
	 * RobotSuper Constructor
	 * Description: Constructs a RobotSuper object with default values. 
	 */
	public RobotSuper()
	{
		start=new Position(0,0);
		current=new Position(0,0);
		heading=0;
		speed=0;
		lines=new HashMap<Integer, String>();
		fName=null;
	}
	/**
	 * RobotSuper Constructor
	 * Description: Constructs an object with given Coordinates 
	 */
	public RobotSuper(int x, int y)
	{
		start=new Position(x,y);
		current=new Position(x,y);
		heading=0;
		speed=0;
		lines=new HashMap<Integer, String>();
		fName=null;
	}

	/**
	 * Method: setStartingPosition
	 * Returns: void
	 * Description: Sets robot starting position by provided position.
	 */
	
	public void setStartingPosition(Position p)
	{
		start.x=p.x;
		start.y=p.y;
		current.x=p.x;
		current.y=p.y;
	}

	/**
	 * Method: setName
	 * Returns: void
	 * Description: Sets robot's name with provided string.
	 */
	
	public void setName(String t_name)
	{
		this.name=t_name;
	}
	
	/**
	 * Method: move
	 * Returns: void
	 * Description: Changes Robot's direction and speed according to provided
	 * input.
	 */
	
	public void move(int speed, int heading)
	{
		// Check provided heading is under limits.
		if(headingLimit>0)
		{
			if((Math.abs(heading-this.getHeading()))>headingLimit)
			{
				if(heading<this.getHeading()) this.heading=this.getHeading() - headingLimit;
				else this.heading=(this.getHeading() + headingLimit);
			}
			else this.heading=(heading);
		}
		else this.heading=(heading);
		// Check provided speed is under limits.
		if(speedLimit>0)
		{
			if(Math.abs(speed)>speedLimit)
			{
				if(speed<0) this.speed=((-1)*(speedLimit));
				else this.speed=(speedLimit);
			}
			else this.speed=(speed);
		}
		else this.speed=(speed);
	}

	/**
	 * Method: stop
	 * Returns: void
	 * Description: Changes Robot's speed to 0.
	 */
	
	public void stop() 
	{
		this.speed=0;
	}
	
	/**
	 * Method: getCurrentPosition
	 * Returns: Position
	 * Description: Return robot's current position.
	 */
	
	public Position getCurrentPosition()
	{
		return current;
	}

	/**
	 * Method: act
	 * Returns: void
	 * Description: Move's the robot one step in set direction with set speed.
	 */
	
	public void act()
	{
		// Calculate and set new robot coordinates,
		double deg=GetDeg(heading);
		current.x+=Math.round((float)((speed*(Math.cos(deg)))));
		current.y+=Math.round((float)((speed*(Math.sin(deg)))));
	}
	/**
	 * Method: GetDeg
	 * Returns: double.
	 * Description: Returns provided robot direction converted into real angles.
	 */
	private double GetDeg(int heading)
	{
		
		return Math.toRadians(90-heading);
	}

	/**
	 * @return the speed
	 */
	public int getSpeed()
	{
		return speed;
	}
	/**
	 * @return the heading
	 */
	public int getHeading()
	{
		return heading;
	}
	/**
	 * Method: getName
	 * Returns: String
	 * Description: Returns robot's name.
	 */
	
	public String getName() 
	{
		return this.name;
	}

	/**
	 * Method: compareTo
	 * Returns: int
	 * Description: Implements comparison by name for robot.
	 */
	
	public int compareTo(Robot rob) 
	{
		return name.compareTo(rob.getName());
	}

	/**
	 * Method: getDistance
	 * Returns: double
	 * Description: Returns robot's distance from starting position.
	 */
	
	public double getDistance()
	{
		// Get deficits between current coordinates, and starting ones.
		double distX=current.x-start.x;
		double distY=current.y-start.y;
		// Calculate distance moved.
		return (Math.sqrt(Math.pow(distX, 2)+Math.pow(distY, 2)));
	}
	/* (non-Javadoc)
	 * @see Robot#pickupBox()
	 */
	@Override
	public boolean pickupBox(Box b)
	{
		return false;
	}
	/* (non-Javadoc)
	 * @see Robot#putdownBox()
	 */
	@Override
	public boolean putdownBox()
	{
		return false;
	}
	public void SetProgram(String filename)
	{
		ReadFile(filename);
		fName=filename;
	}
	public void paint(PaintEvent e)
	{
		double deg=GetDeg(heading);
		Canvas c=(Canvas)e.widget;	// get the canvas
		int maxX = c.getSize().x;	// max size
		int maxY = c.getSize().y;
		int mx=maxX/2,my=maxY/2;	// mid point as (0,0)
		int myX=mx+current.x;	// set the current position
		int myY=my-current.y;	// set the current position
			
		e.gc.drawOval(myX-10, myY-10, 20, 20);	// the circle
			
		/*
		 * draw the heading indicator here
		 * use e.gc.drawLine  
		 */
		e.gc.drawLine(myX, myY,
				myX+Math.round((float)((30*(Math.cos(deg))))), myY-Math.round((float)((30*(Math.sin(deg))))));

		// the info
		e.gc.drawString(name,myX+13, myY-13,true);
		e.gc.drawString(this.getClass().toString().split(" ")[1],myX+13, myY,true);
		e.gc.drawString("("+current.x+","+current.y+")",myX+13, myY+13,true);
	}
	public void ReadFile(String program)
	{
		String format="(\\d+) (\\w+)";
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(
					program));
			String line = br.readLine();
			while (line != null)
			{
				lines.put(Integer.parseInt(line.split(" ")[0]), line);
				line = br.readLine();
			}
			br.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public String GetLine(int index)
	{
		return lines.get(index);		
	}
	/* (non-Javadoc)
	 * @see Robot#setPosition(Position)
	 */
	@Override
	public void setPosition(Position pos)
	{
		current=pos;		
	}
}
