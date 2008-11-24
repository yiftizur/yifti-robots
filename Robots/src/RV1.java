
public class RV1 implements Robot,Comparable 
{
	// starting and current positions
	private Position start,current; 
	private int speed,heading;
	// 0..10 forward speed, -10..0 backward speed
	protected int speedLimit=10;
	// 0..20 degrees to the right, -20..0 to the left
	protected int headingLimit=20;
	protected String name;
	public RV1()
	{
		start=new Position(0,0);
		current=new Position(0,0);
		speed=0;
		heading=0;
	}
	public void setStartingPosition(Position p)
	{
		start.x=p.x;
		start.y=p.y;
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
	public int compareTo(Object arg0) 
	{
		return name.compareTo(((Robot)arg0).getName());
	}
}
