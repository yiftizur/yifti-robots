import org.eclipse.swt.events.PaintEvent;

/**
 * Robot interface.
 * Contains all basic robot methods for implementation by different robots.
 **/
public interface Robot extends Comparable<Robot>
{
	public void setStartingPosition(Position p);
	public void setPosition(Position pos);
	public void move(int speed,int heading);
	public void stop();
	public Position getCurrentPosition();
	public void act();
	public String getName();
	public void setName(String str);
	public double getDistance();
	public int compareTo(Robot r);
	public int getSpeed();
	public int getHeading();
	public boolean pickupBox(Box b);
	public boolean putdownBox();
	public void SetProgram(String filename);
	public String getProgram();
	public String GetLine(int index);
	public void paint(PaintEvent e);
}
