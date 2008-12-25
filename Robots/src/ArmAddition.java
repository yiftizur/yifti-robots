
public class ArmAddition implements Robot
{
	protected Robot rob;
	private Box myBox;
	public ArmAddition(Robot robby) 
	{
		rob=robby;
		myBox=null;
	}
	public void act() 
	{
		rob.act();
		if(myBox!=null) myBox.SetPosition(rob.getCurrentPosition());
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
	public boolean pickupBox(Box b)
	{
		if(b.getPosition().compareTo((this.getCurrentPosition()))==0)
		{
			if(!b.isPicked && myBox==null)
			{
				myBox=b;
				b.isPicked=true;
				return true;
			}
			return false;
		}
		return false;
	}
	public boolean putdownBox()
	{
		if(myBox!=null)
		{
			myBox.isPicked=false;
			myBox=null;
			return true;
		}
		return false;
	}
}
