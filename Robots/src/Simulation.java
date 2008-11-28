import java.util.HashMap;
public class Simulation
{
	// Hashmap holding name and robot as pairs.
	private HashMap<String, Robot> nameMap;
	
	/**
	 * Default Constructor for simulation class.
	 *  
	 */
	public Simulation()
	{
		nameMap=new HashMap<String, Robot>();
	}

	/**
	 * Method: getRobot
	 * Returns: Robot
	 * @param name
	 * @return
	 * Description: Returns robot by name from hashmap.
	 */
	
	public Robot getRobot(String name)
	{
		if(!nameMap.isEmpty()) return nameMap.get(name);
		else return null;
	}
	
	/**
	 * Method: addRobot
	 * Returns: void
	 * @param rob
	 * @return
	 * Description: Adds provided Robot to Hashmap by name.
	 */
	
	public void addRobot(Robot rob)
	{
		nameMap.put(rob.getName(),rob);		
	}
}
