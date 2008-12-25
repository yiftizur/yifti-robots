import java.util.HashMap;


public class RobotActions
{
	private HashMap<String,Command> commandsMap;
	// The simulation object for calling and manipulating robots.
	private Simulation sim;
	// The Boxes object for calling and manipulating boxes.
	private Boxes boxes;
	/**
	 * @param sim
	 * @param boxes Constructor
	 * Description: 
	 */
	public RobotActions(Simulation sim, Boxes boxes)
	{
		this.sim = sim;
		this.boxes = boxes;
		// Initialize the hashmap, with all command classes.
		commandsMap=new HashMap<String, Command>(10);
	}
	
}
