public class SmartRobot implements Robot
{
	protected RobotSuper rob;
	private int sHeading;
	/**
	 * @param robby Constructor
	 * Description: 
	 */
	public SmartRobot(RobotSuper robby)
	{
		rob=robby;
		sHeading=0;
	}
	public int compareTo(Robot r)
	{
		return rob.compareTo(r);
	}

	public Position getCurrentPosition()
	{
		return rob.getCurrentPosition();
	}

	public double getDistance()
	{
		return rob.getDistance();
	}

	public String getName()
	{
		return rob.getName();
	}

	public void move(int speed, int heading)
	{
		sHeading=(heading%360);
		if(sHeading<0) sHeading+=360;
		if(heading>0) heading=(heading%360)-360;
		if(Math.abs(rob.getHeading()-sHeading)>Math.abs(rob.getHeading()-heading))
			rob.move(speed,heading);
		else rob.move(speed,sHeading);
	}
	public void act()
	{
		rob.act();
		this.move(rob.getSpeed(), sHeading);
	}

	public void setName(String str)
	{
		rob.setName(str);		
	}

	public void setStartingPosition(Position p)
	{
		rob.setStartingPosition(p);
	}
	public void stop()
	{
		rob.stop();
	}
	/**
	 * @return the sHeading
	 */
	public int getSHeading()
	{
		return sHeading;
	}	
}
