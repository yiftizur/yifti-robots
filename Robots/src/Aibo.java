
public class Aibo implements Robot,Comparable<Robot>
{
	protected int speedLimit=10;
	protected String name;
	private Position start,current; 
	private int speed,heading;

	public Aibo() 
	{
		start=new Position(0,0);
		current=new Position(0,0);
		speed=0;
		heading=0;
	}
	
	public Aibo(Position start) 
	{
		this.start = start;
		this.current = start;
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
	
}
