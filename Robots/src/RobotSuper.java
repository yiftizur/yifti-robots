import java.util.Comparator;


public class RobotSuper implements Robot, Comparable<Robot>, Comparator<Robot>
{
	private Position start,current; 
	private int speed,heading;
	// Speed limit imposed on movement.
	protected int speedLimit;
	// Heading limit imposed on the angle.
	protected int headingLimit;
	protected String name;
	public RobotSuper()
	{
		start=new Position(0,0);
		current=new Position(0,0);
		speed=0;
		heading=0;
	}
	public RobotSuper(int x, int y)
	{
		start=new Position(x,y);
		current=new Position(x,y);
		speed=0;
		heading=0;
	}
	public void setStartingPosition(Position p)
	{
		start.x=p.x;
		start.y=p.y;
		current.x=p.x;
		current.y=p.y;
	}
	public void setName(String t_name)
	{
		this.name=t_name;
	}
	public void move(int speed, int heading)
	{
		this.speed=speed;
		this.heading=heading;
		if(speed>speedLimit) this.speed=speedLimit;
		if(Math.abs(heading)>headingLimit) this.heading=headingLimit;
	}
	public void stop() 
	{
		speed=0;
	}
	public Position getCurrentPosition()
	{
		return current;
	}
	public void act()
	{
		double deg=Math.toRadians(GetDeg(heading));
		current.x=(int) (current.x+(speed*Math.cos(deg)));
		current.y=(int) (current.y+(speed*Math.sin(deg)));
	}
	private int GetDeg(int heading)
	{
		return (90-heading)%360;
	}
	public String getName() 
	{
		return this.name;
	}
	public int compareTo(Robot rob) 
	{
		return name.compareTo(rob.getName());
	}
	public int compare(Robot rob1, Robot rob2)
	{
		if(rob1.getDistance()>rob2.getDistance()) return -1;
		if(rob1.getDistance()<rob2.getDistance()) return 1;
		else return 0;
	}
	public double getDistance()
	{
		double distX=current.x-start.x;
		double distY=current.y-start.y;
		return (Math.sqrt(Math.pow(distX, 2)+Math.pow(distY, 2)));
	}
}
