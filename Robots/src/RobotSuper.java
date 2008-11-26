
public class RobotSuper implements Robot 
{
	private Position start,current; 
	private int speed,heading;
	// 0..10 forward speed, -10..0 backward speed
	protected int speedLimit;
	// 0..20 degrees to the right, -20..0 to the left
	protected int headingLimit;
	protected String name;
	@Override
	public void act() {
		// TODO Auto-generated method stub

	}

	@Override
	public Position getCurrentPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void move(int speed, int heading) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStartingPosition(Position p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

}
