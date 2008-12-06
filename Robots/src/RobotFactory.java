import java.util.HashMap;

/**
 * RobotFactory class.
 * Class for the Factory Pattern, for getting new robot type by name.
 */

public class RobotFactory
{
	/* 
	 * RobotFac interface, contains the create methods for all 
	 * other factories to implement.
	 */
	private interface RobotFac
	{
		public Robot create();
	}

	/*
	 * RV1 Factory for RV1 robots.
	 */
	
	private class RV1Fac implements RobotFac
	{
		public Robot create()
		{
			return new RV1();
		}
	}
	
	/*
	 * RV2 Factory for RV2 robots.
	 */
	
	private class RV2Fac implements RobotFac
	{
		public Robot create()
		{
			return new RV2();
		}
	}
	
	/*
	 * Aibo Factory for Aibo robots.
	 */
	
	
	private class AiboFac implements RobotFac
	{
		public Robot create()
		{
			return new Aibo();
		}
	}
	// Hashmap for holding type name and factory value.
	private HashMap<String, RobotFac> robotFactory;

	
	/**
	 * RobotFactory Constructor
	 * Description: Creates an instance of the robot factory.
	 * inserts all factory types into hashmap.
	 */
	public RobotFactory()
	{
		// Initialize hasmap, and insert all types by name.
		robotFactory = new HashMap<String, RobotFac>();
		robotFactory.put("RV1", new RV1Fac());
		robotFactory.put("RV2", new RV2Fac());
		robotFactory.put("Aibo", new AiboFac());
	}

	/**
	 * Method: createRobot
	 * Returns: Robot
	 * Description: Creates a robot by the provided type name string.
	 */
	public Robot createRobot(String type)
	{
		// If type does not exist, return null.
		if(robotFactory.get(type)==null) return null;
		else return ((RobotFac) robotFactory.get(type)).create();
	}
}
