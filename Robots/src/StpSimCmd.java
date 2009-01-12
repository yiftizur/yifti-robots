/**
 * StpSimCmd Class.
 * Implementation of the GCommand interface - the Stop Simulation command for 
 * Stopping the Simulation for all robots.
 */

public class StpSimCmd implements GCommand
{
	// Local programs variable.
	private Programs progs;

	/**
	 * StpSimCmd Constructor
	 * Description: Create new instance with given parameter.
	 */
	public StpSimCmd(Programs progs)
	{
		this.progs=progs;
	}

	/**
	 * Method: execute
	 * Overrides: @see GCommand#execute()
	 * Returns: None.
	 * Description: Stops all running programs.
	 */

	public void execute()
	{
		progs.shutDown();
	}

}
