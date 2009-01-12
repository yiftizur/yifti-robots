import java.util.Collection;
import java.util.HashMap;

/**
 * Boxes Class.
 * Class for holding and managing all boxes in simulation.
 */
public class Boxes
{
	// Hashmap for getting and storing boxes.
	private HashMap<String, Box> nameMap;
	
	/**
	 * Boxes Constructor
	 * Description: Create instance and initialize hashmap. 
	 */
	
	public Boxes()
	{
		nameMap=new HashMap<String, Box>();
	}
	
	
	/**
	 * Method: AddBox
	 * Returns: void
	 * @param box
	 * Description: Add given box to hashmap with its name.
	 */
	
	public void AddBox(Box box)
	{
		nameMap.put(box.getName(), box);
	}
	
	/**
	 * Method: getBox
	 * Returns: Box
	 * @param bname
	 * @return
	 * Description: Returns hashmap value for given name.
	 */
	public Box getBox(String bname)
	{
		// Check if hashmap is empty.
		if(!nameMap.isEmpty()) return nameMap.get(bname);
		else return null;
	}
	
	/**
	 * Method: getNames
	 * Returns: Collection<Box>
	 * @return
	 * Description: Returns all values in hashmap.
	 */
	public Collection<Box> getNames()
	{
		return nameMap.values();
	}
}
