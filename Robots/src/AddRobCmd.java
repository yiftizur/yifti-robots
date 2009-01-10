import org.eclipse.swt.widgets.List;

/**
 * AddRobCmd Class.
 * Implementation of the GCommand interface - the Init Robot command for creating a 
 * new robot.
 */
public class AddRobCmd implements GCommand
{
	// Local variables.
	private Simulation sim;
	// Robot name.
	private String name;
	// Robot type.
	private String type;
	// Robot Factory object.
	private RobotFactory fact;
	// Robot addition.
	private String add;
	private List robots;
	/**
	 * AddRobCmd Constructor
	 * Description: Ctor creates new object with given variables.
	 */
	public AddRobCmd(Simulation sim, String name , String type, String add, List robots)
	{
		this.sim = sim;
		this.name=name;
		this.type=type;
		this.add=add;
		this.robots=robots;
		fact=new RobotFactory();
	}
	
	/**
	 * Method: execute
	 * Overrides: @see GCommand#execute()
	 * Returns: None.
	 * Description: Run the implementation of adding a new robot.
	 */

	public void execute()
	{
		// Check robot name is valid and does not already exists.
		if(name!="" && sim.getRobot(name)==null) 
		{
			// Create new robot with Robot Factory.
			Robot rob=fact.createRobot(type);
			// Set robot name.
			rob.setName(name);
			// Add robot to simulation according to addition.
			MakeRob(rob);
			// Update robors list.
			robots.add(name);
		}
		// Print error if name is already used.
		else if(sim.getRobot(name)!=null)
			ErrorPrint.PrintError("Robot with same name already exists!");
		// Print error if no name was given.
		else ErrorPrint.PrintError("Robot name cannot be empty!");
	}
	/**
	 * Method: MakeRob
	 * Returns: None
	 * @param rob
	 * Description: Adds robot to simulation according to its addition.
	 */
	private void MakeRob(Robot rob)
	{
		// Check addition string, and create decorator accordingly.
		if(add.equals("arm"))
		{
			ArmAddition robby=new ArmAddition(rob);
			sim.addRobot(robby);
		}
		else if(add.equals("smartKit"))
		{
			SmartRobot robby=new SmartRobot(rob);
			sim.addRobot(robby);
		}
		else if(add.equals("all"))
		{
			ArmAddition robby=new ArmAddition(new SmartRobot(rob));
			sim.addRobot(robby);
		}
		else sim.addRobot(rob);
	}
}
