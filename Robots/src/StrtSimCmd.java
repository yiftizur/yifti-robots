/**
 * 
 */

/**
 * @author Administrator
 *
 */
public class StrtSimCmd implements GCommand
{

	private Programs progs;

	/**
	 * @param progs
	 * @param b Constructor
	 * Description: 
	 */
	public StrtSimCmd(Programs progs)
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
		progs.RunAll();
	}

}
