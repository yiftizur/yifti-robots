import java.util.ArrayList;

/**
 * Programs Class.
 * Used for holding all instances of the RunProgram object.
 * provides control over running and stopping the RunProgram objects
 * in seperate threads.
 */
public class Programs
{
	// Lists of RunProgram objects and Threads.
	private ArrayList<RunProgram> programs;
	private ArrayList<Thread> threads;
	/**
	 * Programs Constructor
	 * Description:  Initialize both arraylists.
	 */
	public Programs()
	{
		programs=new ArrayList<RunProgram>();
		threads=new ArrayList<Thread>();
	}
	/**
	 * Method: addProgram
	 * Returns: void
	 * @param run
	 * Description: Adds a RunProgram object to the local list.
	 */
	public void addProgram(RunProgram run)
	{
		// Add the RunProgram object, and creates a new thread for it.
		programs.add(run);
		threads.add(new Thread());
	}
	/**
	 * Method: RunAll
	 * Returns: void
	 * @param time
	 * Description: Runs all program for provided amount of time - deprecated.
	 */
	public void RunAll(int time)
	{
		// Run over all programs in list.
		for(int i=0;i<programs.size();i++)
		{
			// Set time limit.
			programs.get(i).setTime(time);
			// get thread for program.
			Thread t=threads.get(i);
			// Associate thread with program.
			t=new Thread(programs.get(i));
			// Start thread.
			t.start();
		}
		// Delay for given time.
		try
		{
			Thread.sleep(time*1000);
		} catch (InterruptedException e)
		{
			ErrorPrint.PrintError(e.getMessage());
		}
	}
	/**
	 * Method: RunAll
	 * Returns: void
	 * Description: Runs all programs for as long as not stopped.
	 */
	public void RunAll()
	{
		// Go over all programs.
		for(int i=0;i<programs.size();i++)
		{
			// Get and associate thread with program.
			Thread t=threads.get(i);
			t=new Thread(programs.get(i));
			// Start thread.
			t.start();
		}
	}

	/**
	 * Method: size
	 * Returns: int
	 * @return
	 * Description: Returns number of programs.
	 */
	public int size()
	{
		return programs.size();
	}
	/**
	 * Method: shutDown
	 * Returns: void
	 * Description: Stops all Progras running.
	 */
	public void shutDown()
	{
		// Go over all programs.
		for(int i=0;i<programs.size();i++)
		{
			// Set KeepAlive to false for the run method to stop.
			programs.get(i).setKeepAlive(false);
		}
	}
}
