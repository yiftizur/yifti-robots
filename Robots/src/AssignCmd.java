import java.io.File;

import org.eclipse.swt.widgets.List;

/**
 * @author Administrator
 * AssignCmd Class
 * Implementation of the GCommand interface - the Assign command for assigning a 
 * Program to a robot.
 */
public class AssignCmd implements GCommand
{
	// Local variables.
	// Robot name.
	private String name;
	// Selected program.
	private String program;
	// Global variables needed.
	private Boxes boxes;
	private Simulation sim;
	private Programs progs;
	private List robots;
	/**
	 * AssignCmd Constructor
	 * Description: Creates the object with given parameters. 
	 */
	public AssignCmd(String name, String prog, Simulation sim, Boxes boxes,
			Programs progs, List robots)
	{
		this.name=name;
		this.program=prog;
		this.sim=sim;
		this.boxes=boxes;
		this.progs=progs;
		this.robots=robots;
	}

	/**
	 * Method: execute
	 * Overrides: @see GCommand#execute()
	 * Returns: None.
	 * Description: Runs the command. Assigns given robot with given program.
	 */
	@Override
	public void execute()
	{
		// Get robot by name.
		Robot rob=sim.getRobot(name);
		// Check robot does not have a program assigned.
		if(rob.getProgram()==null)
		{
			// Check program is not a Directory
			File file=new File(program);
			if(!file.isDirectory())
			{
				// Set program to robot.
				rob.SetProgram(program);
				// Create a new RunProgram object for robot.
				progs.addProgram(new RunProgram(rob,boxes));
				// Update robots list entry.
				int ind=robots.getSelectionIndex();
				robots.remove(ind);
				robots.add(String.format("%s %s",name,program), ind);
			}
			// Print error for directory as program.
			else ErrorPrint.PrintError("Cannot Assign Directory to robot!");			
		}
		// Print error for already assigned robot.
		else ErrorPrint.PrintError("another program is assigned to this robot.");
	}
}
