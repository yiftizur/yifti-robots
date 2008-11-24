
public interface Robot
{
	public void setStartingPosition(Position p);
	public void move(int speed,int heading);
	public void stop();
	public Position getCurrentPosition();
	public void act();
	public String getName();
}