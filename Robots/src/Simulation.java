import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
public class Simulation
{
	// Hashmap holding name and robot as pairs.
	private HashMap<String, Robot> nameMap;
	public ArrayList<Robot> robList;
	/**
	 * Default Constructor for simulation class.
	 *  
	 */
	public Simulation()
	{
		nameMap=new HashMap<String, Robot>();
		robList=new ArrayList<Robot>();
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
		// Adding provided robot object to both ArrayList & Hashmap.
		nameMap.put(rob.getName(),rob);
		robList.add(rob);
	}
	
	/**
	 * Method: getNameMap
	 * Returns: Collection<Robot>
	 * Description: Return all values of the names hashmap.
	 */
	public Collection<Robot> getNameMap()
	{
		return nameMap.values();
	}
}
