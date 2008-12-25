public class SmartRobot implements Robot
{
	protected Robot rob;
	private int sHeading;
	/**
	 * @param robby Constructor
	 * Description: 
	 */
	public SmartRobot(Robot rob)
	{
		this.rob=rob;
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
	/* (non-Javadoc)
	 * @see Robot#pickupBox(Box)
	 */
	@Override
	public boolean pickupBox(Box b)
	{
		return rob.pickupBox(b);
	}
	/* (non-Javadoc)
	 * @see Robot#putdownBox()
	 */
	@Override
	public boolean putdownBox()
	{
		return rob.putdownBox();
	}	
}
