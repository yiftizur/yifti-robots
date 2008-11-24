import java.util.ArrayList;
import java.util.Iterator;
public class Simulation
{
	private ArrayList<Robot> robarray;
	public Simulation()
	{
		robarray=new ArrayList<Robot>();
	}
	public Simulation(Robot rob)
	{
		robarray=new ArrayList<Robot>();
		robarray.add(rob);
	}
	public Simulation(ArrayList<Robot> arr)
	{
		robarray=new ArrayList<Robot>();
		for(int i=0;i<arr.size();i++)
		{
			robarray.add(arr.get(i));
		}
	}
	public Robot getRobot(int index)
	{
		if(!robarray.isEmpty()) return (Robot) robarray.get(index);
		else return null;		
	}
	public Robot getRobot(String name)
	{
		Iterator<Robot> it=robarray.iterator();
		
	}
	public int addRobot(Robot rob)
	{
		robarray.add(rob);
		return robarray.indexOf(rob);		
	}
}
