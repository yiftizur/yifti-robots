import java.util.Collection;
import java.util.HashMap;

public class Boxes
{
	private HashMap<String, Box> nameMap;
	public Boxes()
	{
		nameMap=new HashMap<String, Box>();
	}
	public void AddBox(Box box)
	{
		nameMap.put(box.getName(), box);
	}
	public Box getBox(String bname)
	{
		if(!nameMap.isEmpty()) return nameMap.get(bname);
		else return null;
	}
	public Collection<Box> getNames()
	{
		return nameMap.values();
	}
}
