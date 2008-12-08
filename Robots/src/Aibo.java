
public class Aibo extends RobotSuper
{
	/**
	 * Aibo Constructor
	 * Description: Creates an Aibo object with limits.
	 */
	public Aibo() 
	{
		super.speedLimit=50;
	}
	/**
	 * Aibo Constructor
	 * Description: Creates an RV1 object at x,y position.
	 */
	public Aibo(int x,int y)
	{
		super(x,y);
		super.speedLimit=50;
	}
		
}
