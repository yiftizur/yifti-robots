/**
 * StrtSimCmd Class.
 * Implementation of the GCommand interface - the Start Simulation command for 
 * Starting the Simulation for all robots.
 */

public class StrtSimCmd implements GCommand
{

	private Programs progs;

	/**
	 * @param progs
	 * StrtSimCmd Constructor
	 * Description: Creates a new instance with provided parameter.
	 */
	public StrtSimCmd(Programs progs)
	{
		this.progs=progs;
	}

	/**
	 * Method: execute
	 * Overrides: @see GCommand#execute()
	 * Returns: None.
	 * Description: Starts all programs for all assigned robots.
	 */

	public void execute()
	{
		progs.RunAll();
	}

}
