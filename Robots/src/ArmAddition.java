import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Canvas;

/**
 * ArmAddition Class
 * A Decorator class for a Robot with an Arm.
 */
public class ArmAddition implements Robot
{
	// Private robot object received in Ctor.
	protected Robot rob;
	// Box held (or not) by robot.
	private Box myBox;
	
	/**
	 * ArmAddition Constructor
	 * Description: constructs the Decorator object with the received Robot. 
	 */
	
	public ArmAddition(Robot robby) 
	{
		rob=robby;
		myBox=null;
	}
	
	/**
	 * Act method.
	 * Implements act for Robot with arm, to include Box position updates.
	 */
	
	public void act() 
	{
		rob.act();
		if(myBox!=null) myBox.SetPosition(rob.getCurrentPosition());
	}

	/**
	 * Method: compareTo
	 * Overrides: Robot#compareTo(Robot)
	 * Returns: integer.
	 * Description: implementation of the CompareTo method for a Robot with Arm.
	 */
	public int compareTo(Robot r)
	{
		return rob.compareTo(r);
	}
	
	
	/**
	 * Method: getCurrentPosition
	 * Overrides:  Robot#getCurrentPosition()
	 * Returns: Position.
	 * Description: Returns robots current position.
	 */
	
	public Position getCurrentPosition()
	{
		return rob.getCurrentPosition();
	}

	/**
	 * Method: getDistance
	 * Overrides: @see Robot#getDistance()
	 * Returns: double
	 * @return
	 * Description: Returns robot's distance from his starting point.
	 */
	public double getDistance() 
	{
		return rob.getDistance();
	}

	/**
	 * Method: getName
	 * Overrides: @see Robot#getName()
	 * Returns: String
	 * Description: Returns robt's name.
	 */
	@Override
	public String getName()
	{
		return rob.getName();
	}

	/**
	 * Method: move
	 * Overrides: @see Robot#move(int, int)
	 * Returns: None.
	 * @param speed
	 * @param heading
	 * Description: Performs a Move command on the inline robot.
	 */
	@Override
	public void move(int speed, int heading) 
	{
		rob.move(speed, heading);
	}

	/**
	 * Method: setName
	 * Overrides: @see Robot#setName(java.lang.String)
	 * Returns: None/
	 * @param str
	 * Description: Sets robots name to given string.
	 */
	@Override
	public void setName(String str)
	{
		rob.setName(str);
	}

	/**
	 * Method: setStartingPosition
	 * Overrides: @see Robot#setStartingPosition(Position)
	 * Returns: None.
	 * Description: Sets robots Starting position.
	 */
	@Override
	public void setStartingPosition(Position p)
	{
		rob.setStartingPosition(p);
	}

	/**
	 * Method: stop
	 * Overrides: @see Robot#stop()
	 * Returns: None.
	 * Description: Stops robots movement - set speed to 0.
	 */
	@Override
	public void stop() 
	{
		rob.stop();
	}
	/**
	 * Method: getHeading
	 * Overrides: @see Robot#getHeading()
	 * Returns: int
	 * Description: Returns robot's heading.
	 */
	@Override
	public int getHeading() 
	{
		return rob.getHeading();
	}
	/**
	 * Method: getSpeed
	 * Overrides: @see Robot#getSpeed()
	 * Returns: int
	 * Description: Returns robots speed.
	 */
	@Override
	public int getSpeed() 
	{
		return rob.getSpeed();
	}
	/**
	 * Method: pickupBox
	 * Overrides: @see Robot#pickupBox(Box)
	 * Returns: Boolean
	 * Description: Method for robot to pickup a box in its current location.
	 * Returns true for a successful pickup, false for else.
	 */
	public synchronized boolean pickupBox(Box b)
	{
		// Check robot and box at the same place.
		if(b.getPosition().compareTo((this.getCurrentPosition()))==0)
		{
			// Check box is not already picked up, and robot has no Box.
			if(!b.isPicked && myBox==null)
			{
				// Assign local variable.
				myBox=b;
				// Make box picked.
				b.isPicked=true;
				return true;
			}
			return false;
		}
		return false;
	}
	/**
	 * Method: putdownBox
	 * Overrides: @see Robot#putdownBox()
	 * Returns: Boolean
	 * Description: Method for robot to putdown its box.
	 */
	public synchronized boolean putdownBox()
	{
		// Check robot has a box.
		if(myBox!=null)
		{
			// Make box not picked.
			myBox.isPicked=false;
			// Make local variable null.
			myBox=null;
			return true;
		}
		return false;
	}

	/**
	 * Method: SetProgram
	 * Overrides: @see Robot#SetProgram(java.lang.String)
	 * Returns: None.
	 * Description: Set inline robot's program.
	 */
	public void SetProgram(String filename)
	{
		rob.SetProgram(filename);
	}

	/**
	 * Method: getProgram
	 * Overrides: @see Robot#getProgram()
	 * Returns: String
	 * Description: Returns robots assigned program name.
	 */
	public String getProgram()
	{
		return rob.getProgram();
	}

	/**
	 * Method: paint
	 * Overrides: @see Robot#paint(org.eclipse.swt.events.PaintEvent)
	 * Returns: None.
	 * Description: Paint the Robot on given canvas.
	 */
	public void paint(PaintEvent e)
	{
		Canvas c=(Canvas)e.widget;	// get the canvas
		int maxX = c.getSize().x;	// max size
		int maxY = c.getSize().y;
		int mx=maxX/2,my=maxY/2;	// mid point as (0,0)
		int myX=mx+rob.getCurrentPosition().x;	// set the current position
		int myY=my-rob.getCurrentPosition().y;	// set the current position
		// Draw holding place for Box :)
		e.gc.drawPolyline(new int[] {myX-7,myY-7,myX-7,myY+7,myX+7,myY+7,myX+7,myY-7});
		// Invoke inline robot's paint method.
		rob.paint(e);
	}

	/**
	 * Method: GetLine
	 * Overrides: @see Robot#GetLine(int)
	 * Returns: String.
	 * Description: Returns robot assigned program's line by index.
	 */
	public String GetLine(int index)
	{
		return rob.GetLine(index);
	}
	
	/**
	 * Method: setPosition
	 * Overrides: @see Robot#setPosition(Position)
	 * Returns: None.
	 * Description: Sets robot's position.
	 */
	public void setPosition(Position pos)
	{
		rob.setPosition(pos);
	}

}
