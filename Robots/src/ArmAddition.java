
public class ArmAddition implements Robot
{
	protected Robot rob;
	public ArmAddition(Robot robby) 
	{
		rob=robby;
	}
	public void act() 
	{
		rob.act();
	}

	@Override
	public int compareTo(Robot r)
	{
		return rob.compareTo(r);
	}

	@Override
	public Position getCurrentPosition()
	{
		return rob.getCurrentPosition();
	}

	@Override
	public double getDistance() 
	{
		return rob.getDistance();
	}

	@Override
	public String getName()
	{
		return rob.getName();
	}

	@Override
	public void move(int speed, int heading) 
	{
		rob.move(speed, heading);
	}

	@Override
	public void setName(String str)
	{
		rob.setName(str);
	}

	@Override
	public void setStartingPosition(Position p)
	{
		rob.setStartingPosition(p);
	}

	@Override
	public void stop() 
	{
		rob.stop();
	}
	@Override
	public int getHeading() 
	{
		return rob.getHeading();
	}
	@Override
	public int getSpeed() 
	{
		return rob.getSpeed();
	}

}
