import java.util.ArrayList;


public class Programs
{
	private ArrayList<RunProgram> programs;
	private ArrayList<Thread> threads;
	/**
	 *  Constructor
	 * Description: 
	 */
	public Programs()
	{
		programs=new ArrayList<RunProgram>();
		threads=new ArrayList<Thread>();
	}
	public void addProgram(RunProgram run)
	{
		programs.add(run);
		threads.add(new Thread());
	}
	public void RunAll(int time) throws InterruptedException
	{
		for(int i=0;i<programs.size();i++)
		{
			programs.get(i).setTime(time);
			Thread t=threads.get(i);
			t=new Thread(programs.get(i));
			t.start();
		}
		Delay(time);
	}
	public void RunAll() throws InterruptedException
	{
		for(int i=0;i<programs.size();i++)
		{
			Thread t=threads.get(i);
			t=new Thread(programs.get(i));
			t.start();
		}
		/*for (int i = 0; i < threads.size(); i++)
		{
			Thread t=threads.get(i);
			if(t.isAlive()) t.join();
		}*/
	}
	public void Delay(int sec)
	{
		try
		{
			Thread.sleep(sec*1000);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int size()
	{
		return programs.size();
	}
	/**
	 * Method: shutDown
	 * Returns: void
	 * Description:
	 */
	public void shutDown()
	{
		for(int i=0;i<programs.size();i++)
		{
			programs.get(i).setKeepAlive(false);
		}
	}
}
