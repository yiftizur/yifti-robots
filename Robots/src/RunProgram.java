import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * RunProgram Class.
 * Class for running a program assigned to a specific robot.
 * Class is an implementation of Runnable, for running in seperate threads.
 */
public class RunProgram implements Runnable
{
	// Local Variables for robot.
	Robot rob;
	private static int pCounter;
	private int seCounter;
	// A RobotActions object for handling the action commands in program.
	private RobotActions act;
	private Boxes boxes;
	private String line;
	// KeepAlive boolean for thread.
	private boolean keepAlive;
	/**
	 * @return the keepAlive
	 */
	public boolean isKeepAlive()
	{
		return keepAlive;
	}
	/**
	 * @param set the keepAlive variable.
	 */
	public void setKeepAlive(boolean keepAlive)
	{
		this.keepAlive = keepAlive;
	}
	/**
	 * RunProgram Constructor
	 * Description: Builds the runProgram object with given robot and box.
	 * Program is assigned to robot, and linked in robot object.
	 * @throws FileNotFoundException 
	 */
	public RunProgram(Robot rob,Boxes box)
	{
		// Update local variables.
		this.rob=rob;
		this.boxes=box;
		this.seCounter=0;
		this.line=null;
		// Create new RobotActions object.
		this.act=new RobotActions(rob, boxes);
		// Init program counter.
		RunProgram.pCounter=0;
		keepAlive=true;
	}
	/**
	 * Method: run
	 * Overrides: @see java.lang.Runnable#run()
	 * Returns: None.
	 * Description: Implementation of the run method.
	 * Runs the associated program of the robot, line by line.
	 */
	public void run()
	{
		do
		{
			// Read the program line with the current time stamp.
			line=rob.GetLine(seCounter);
			// If such line exists.
			if(line!=null)
			{
				try
				{
					// Run the command in line through the RobotActions object.
					act.runCommand(line);
				} catch (PrgErrException e)// Catch Program error Exception.
				{
					// Print error message.
					ErrorPrint.PrintError(e.GetMessage());
				}catch (FileNotFoundException e)
				{
					// Print error message.
					ErrorPrint.PrintError(e.getMessage());
				} catch (IOException e)
				{
					// Print error message.
					ErrorPrint.PrintError(e.getMessage());
				}
				// Advance program counter.
				setPCounter(getPCounter() + 1);
			}
			// Run robot's act method.
			rob.act();
			// Advance Seconds counter.
			seCounter++;
			try
			{
				// Sleep for 1 second.
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{}
		}
		while(keepAlive); // While keepAlive is still true, run loop.
	}
	/**
	 * @param pCounter the pCounter to set
	 */
	public static void setPCounter(int pCounter)
	{
		RunProgram.pCounter = pCounter;
	}
	/**
	 * @return the pCounter
	 */
	public static int getPCounter()
	{
		return pCounter;
	}
	/**
	 * Method: setTime
	 * Returns: void
	 * @param time
	 * Description: Depreciated method from previous version.
	 */
	public void setTime(int time)
	{}
}
