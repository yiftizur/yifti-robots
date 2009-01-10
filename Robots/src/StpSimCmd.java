/**
 * 
 */

/**
 * @author Administrator
 *
 */
public class StpSimCmd implements GCommand
{

	private Programs progs;

	/**
	 * @param progs Constructor
	 * Description: 
	 */
	public StpSimCmd(Programs progs)
	{
		this.progs=progs;
	}

	/**
	 * Method: execute
	 * Overrides: @see GCommand#execute()
	 * Returns: 
	 * Description:
	 */
	@Override
	public void execute()
	{
		progs.shutDown();
	}

}
