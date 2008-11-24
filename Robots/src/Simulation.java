import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
public class Simulation
{
	private ArrayList<Robot> robarray;
	private HashMap<String, Robot> robarray2;
	public Simulation()
	{
		robarray=new ArrayList<Robot>();
		robarray2=new HashMap<String, Robot>();
	}
	public Robot getRobot(int index)
	{
		if(!robarray.isEmpty()) return robarray.get(index);
		else return null;		
	}
	public Robot getRobot(String name)
	{
		if(!robarray2.isEmpty()) return robarray2.get(name);
		else return null;
	}
	public int addRobot(Robot rob)
	{
		robarray.add(rob);
		return robarray.indexOf(rob);		
	}
}
