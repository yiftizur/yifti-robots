
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
	
	/**
	 * RobotSuper Constructor
	 * Description: Constructs a RobotSuper object with default values. 
	 */
	public RobotSuper()
	{
		start=new Position(0,0);
		current=new Position(0,0);
		speed=0;
		heading=0;
	}
	/**
	 * RobotSuper Constructor
	 * Description: Constructs an object with given Coordinates 
	 */
	public RobotSuper(int x, int y)
	{
		start=new Position(x,y);
		current=new Position(x,y);
		speed=0;
		heading=0;
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
			if((Math.abs(heading))>headingLimit)
			{
				if(heading<0) this.heading-=headingLimit;
				else this.heading+=headingLimit;
			}
			else this.heading+=heading;
		}
		else this.heading=heading;
		// Check provided speed is under limits.
		if(speedLimit>0)
		{
			if(Math.abs(speed)>speedLimit)
			{
				if(speed<0) this.speed=(-1)*(speedLimit);
				else this.speed=speedLimit;
			}
			else this.speed=speed;
		}
		else this.speed=speed;
	}

	/**
	 * Method: stop
	 * Returns: void
	 * Description: Changes Robot's speed to 0.
	 */
	
	public void stop() 
	{
		speed=0;
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
		double deg=Math.toRadians(GetDeg(heading));
		current.x=(int) (current.x+(speed*(Math.cos(deg))));
		current.y=(int) (current.y+(speed*(Math.sin(deg))));
	}
	/**
	 * Method: GetDeg
	 * Returns: int
	 * Description: Returns provided robot direction converted into real angles.
	 */
	private int GetDeg(int heading)
	{
		
		return (90-heading);
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
}
