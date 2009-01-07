import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Canvas;

/**
 * ArmAddition Class
 * A Decorator class for a Robot with an Arm.
 */
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
	public synchronized boolean pickupBox(Box b)
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
	public synchronized boolean putdownBox()
	{
		if(myBox!=null)
		{
			myBox.isPicked=false;
			myBox=null;
			return true;
		}
		return false;
	}
	/* (non-Javadoc)
	 * @see Robot#SetProgram(java.lang.String)
	 */
	@Override
	public void SetProgram(String filename)
	{
		rob.SetProgram(filename);
	}
	/* (non-Javadoc)
	 * @see Robot#getProgram()
	 */
	@Override
	public String getProgram()
	{
		return rob.getProgram();
	}
	/* (non-Javadoc)
	 * @see Robot#paint(org.eclipse.swt.events.PaintEvent)
	 */
	@Override
	public void paint(PaintEvent e)
	{
		Canvas c=(Canvas)e.widget;	// get the canvas
		int maxX = c.getSize().x;	// max size
		int maxY = c.getSize().y;
		int mx=maxX/2,my=maxY/2;	// mid point as (0,0)
		int myX=mx+rob.getCurrentPosition().x;	// set the current position
		int myY=my-rob.getCurrentPosition().y;	// set the current position
		e.gc.drawPolyline(new int[] {myX-7,myY-7,myX-7,myY+7,myX+7,myY+7,myX+7,myY-7});
		rob.paint(e);
	}
	/* (non-Javadoc)
	 * @see Robot#GetLine(int)
	 */
	@Override
	public String GetLine(int index)
	{
		return rob.GetLine(index);
	}
	/* (non-Javadoc)
	 * @see Robot#setPosition(Position)
	 */
	@Override
	public void setPosition(Position pos)
	{
		rob.setPosition(pos);
	}

}
