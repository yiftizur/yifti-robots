import java.util.Comparator;


public interface Robot extends Comparable<Robot>
{
	public void setStartingPosition(Position p);
	public void move(int speed,int heading);
	public void stop();
	public Position getCurrentPosition();
	public void act();
	public String getName();
	public void setName(String str);
	public double getDistance();
	public int compareTo(Robot r);
}
