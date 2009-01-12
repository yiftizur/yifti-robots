import org.eclipse.swt.events.PaintEvent;

/**
 * SmartRobot Class
 * Decorator class for the Robot interface.
 * Add the ability for smart adjustments of headings.
 */

public class SmartRobot implements Robot
{
	// inline robot.
	protected Robot rob;
	// Smart heading.
	private int sHeading;

	/**
	 * SmartRobot Constructor
	 * Description: Creates a new instance with given robot.
	 */
	public SmartRobot(Robot rob)
	{
		this.rob=rob;
		sHeading=0;
	}
	
	/**
	 * Method: compareTo
	 * Overrides: @see Robot#compareTo(Robot)
	 * Returns: int
	 * Description: Implementation of the compareTo method for SmartRobot.
	 */
	public int compareTo(Robot r)
	{
		// Returns the inline robot compareTo.
		return rob.compareTo(r);
	}

	/**
	 * Method: getCurrentPosition
	 * Overrides: @see Robot#getCurrentPosition()
	 * Returns: Position
	 * Description: Returns inline robot's current position.
	 */
	public Position getCurrentPosition()
	{
		return rob.getCurrentPosition();
	}

	/**
	 * Method: getDistance
	 * Overrides: @see Robot#getDistance()
	 * Returns: double
	 * Description: Returns inline robot's distance from starting point.
	 */
	public double getDistance()
	{
		return rob.getDistance();
	}

	/**
	 * Method: getName
	 * Overrides: @see Robot#getName()
	 * Returns: String
	 * Description: Returns inline robot's name.
	 */
	public String getName()
	{
		return rob.getName();
	}

	/**
	 * Method: move
	 * Overrides: @see Robot#move(int, int)
	 * Returns: None.
	 * Description: Invokes inline robot's move command with adjusted headings.
	 */
	public void move(int speed, int heading)
	{
		// Set Smart heading to opposite of provided heading.
		sHeading=(heading%360);
		if(sHeading<0) sHeading+=360;
		if(heading>0) heading=(heading%360)-360;
		// Check which direction is shorter to turn to. Turn robot to that direction.
		if(Math.abs(rob.getHeading()-sHeading)>Math.abs(rob.getHeading()-heading))
			rob.move(speed,heading);
		else rob.move(speed,sHeading);
	}
	/**
	 * Method: act
	 * Overrides: @see Robot#act()
	 * Returns: None.
	 * Description: Invokes inline robot's act. makes sure heading is changed until
	 * in desired direction.
	 */
	public void act()
	{
		rob.act();
		// Update Robots heading after each movement.
		this.move(rob.getSpeed(), sHeading);
	}

	/**
	 * Method: setName
	 * Overrides: @see Robot#setName(java.lang.String)
	 * Returns: None
	 * Description: Sets inline robot's name.
	 */
	public void setName(String str)
	{
		rob.setName(str);		
	}

	/**
	 * Method: setStartingPosition
	 * Overrides: @see Robot#setStartingPosition(Position)
	 * Returns: None
	 * Description: Sets inline robot's Starting Position.
	 */
	public void setStartingPosition(Position p)
	{
		rob.setStartingPosition(p);
	}
	/**
	 * Method: stop
	 * Overrides: @see Robot#stop()
	 * Returns: None
	 * Description: Invokes inline robot's stop method.
	 */
	public void stop()
	{
		rob.stop();
	}
	
	/**
	 * Method: getSHeading
	 * Returns: int
	 * @return
	 * Description: Returns robot's Smart Heading.
	 */
	
	public int getSHeading()
	{
		return sHeading;
	}
	
	/**
	 * Method: getHeading
	 * Overrides: @see Robot#getHeading()
	 * Returns: int
	 * Description: Returns inline robot's heading.
	 */
	
	public int getHeading()
	{
		return rob.getHeading();
	}

	/**
	 * Method: getSpeed
	 * Overrides: @see Robot#getSpeed()
	 * Returns: int
	 * Description: Returns inline robot's speed.
	 */
	
	public int getSpeed() 
	{
		return rob.getSpeed();
	}
	
	/**
	 * Method: pickupBox
	 * Overrides: @see Robot#pickupBox(Box)
	 * Returns: Boolean
	 * Description: Invoke inline robot's pickupBox method.
	 */
	public boolean pickupBox(Box b)
	{
		return rob.pickupBox(b);
	}
	
	/**
	 * Method: putdownBox
	 * Overrides: @see Robot#putdownBox()
	 * Returns: Boolean
	 * Description: Invoke inline robot's putdownBox method.
	 */
	public boolean putdownBox()
	{
		return rob.putdownBox();
	}
	
	/**
	 * Method: SetProgram
	 * Overrides: @see Robot#SetProgram(java.lang.String)
	 * Returns: None
	 * Description: Invoke inline robot's SetProgram method.
	 */
	public void SetProgram(String filename)
	{
		rob.SetProgram(filename);
	}
	
	/**
	 * Method: getProgram
	 * Overrides: @see Robot#getProgram()
	 * Returns: String
	 * Description: Returns inline robot's assigend program name.
	 */
	public String getProgram()
	{
		return rob.getProgram();
	}
	
	/**
	 * Method: paint
	 * Overrides: @see Robot#paint(org.eclipse.swt.events.PaintEvent)
	 * Returns: None
	 * Description: Invokes inline robot's paint method.
	 */
	public void paint(PaintEvent e)
	{
		rob.paint(e);
	}

	/**
	 * Method: GetLine
	 * Overrides: @see Robot#GetLine(int)
	 * Returns: String.
	 * Description: Returns inline robot's program line by index.
	 */
	public String GetLine(int index)
	{
		return rob.GetLine(index);
	}
	
	/**
	 * Method: setPosition
	 * Overrides: @see Robot#setPosition(Position)
	 * Returns: None.
	 * Description: Set inline robot's current position.
	 */
	public void setPosition(Position pos)
	{
		rob.setPosition(pos);
	}	
}
