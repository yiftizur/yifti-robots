
public class RV1 extends RobotSuper 
{
	/**
	 * RV1 Constructor
	 * Description: Creates an RV1 object with limits. 
	 */
	public RV1()
	{
		super.headingLimit=20;
		super.speedLimit=10;
	}
	
	/**
	 * RV1 Constructor
	 * Description: Creates an RV1 object at x,y position. 
	 */
	public RV1(int x,int y)
	{
		super(x,y);
		super.headingLimit=20;
		super.speedLimit=10;
	}
}
