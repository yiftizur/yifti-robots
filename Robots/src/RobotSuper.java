
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
	/* (non-Javadoc)
	 * @see Robot#setStartingPosition(Position)
	 */
	public void setStartingPosition(Position p)
	{
		start.x=p.x;
		start.y=p.y;
		current.x=p.x;
		current.y=p.y;
	}
	/* (non-Javadoc)
	 * @see Robot#setName(java.lang.String)
	 */
	public void setName(String t_name)
	{
		this.name=t_name;
	}
	/* (non-Javadoc)
	 * @see Robot#move(int, int)
	 */
	public void move(int speed, int heading)
	{
		this.speed=speed;
		this.heading+=heading;
		if(headingLimit>0)
			if(Math.abs(heading-this.heading)>headingLimit) this.heading+=headingLimit;
		if(speedLimit>0)
		{
			if(Math.abs(speed)>speedLimit)
			{
				if(speed<0) this.speed=-(speedLimit);
				else this.speed=speedLimit;
			}
		}
	}
	/* (non-Javadoc)
	 * @see Robot#stop()
	 */
	public void stop() 
	{
		speed=0;
	}
	/* (non-Javadoc)
	 * @see Robot#getCurrentPosition()
	 */
	public Position getCurrentPosition()
	{
		return current;
	}
	/* (non-Javadoc)
	 * @see Robot#act()
	 */
	public void act()
	{
		double deg=Math.toRadians(GetDeg(heading));
		current.x=(int) (current.x+(speed*Math.cos(deg)));
		current.y=(int) (current.y+(speed*Math.sin(deg)));
	}
	/**
	 * Method: GetDeg
	 * Returns: int
	 * @param heading
	 * @return
	 * Description:
	 */
	private int GetDeg(int heading)
	{
		return (90-heading)%360;
	}
	/* (non-Javadoc)
	 * @see Robot#getName()
	 */
	public String getName() 
	{
		return this.name;
	}
	/* (non-Javadoc)
	 * @see Robot#compareTo(Robot)
	 */
	public int compareTo(Robot rob) 
	{
		return name.compareTo(rob.getName());
	}
	/* (non-Javadoc)
	 * @see Robot#getDistance()
	 */
	public double getDistance()
	{
		double distX=current.x-start.x;
		double distY=current.y-start.y;
		return (Math.sqrt(Math.pow(distX, 2)+Math.pow(distY, 2)));
	}
}
