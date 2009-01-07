import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
public class RunProgram implements Runnable
{
	Robot rob;
	private static int pCounter;
	private int seCounter;
	private RobotActions act;
	private Boxes boxes;
	private int time;
	private String line;
	private boolean keepAlive;
	/**
	 * @return the keepAlive
	 */
	public boolean isKeepAlive()
	{
		return keepAlive;
	}
	/**
	 * @param keepAlive the keepAlive to set
	 */
	public void setKeepAlive(boolean keepAlive)
	{
		this.keepAlive = keepAlive;
	}
	/**
	 *  Constructor
	 * Description: 
	 * @throws FileNotFoundException 
	 */
	public RunProgram(Robot rob,Boxes box) throws FileNotFoundException
	{
		this.rob=rob;
		this.boxes=box;
		this.seCounter=0;
		this.line=null;
		this.act=new RobotActions(rob, boxes);
		RunProgram.pCounter=0;
		keepAlive=true;
	}
	public void setTime(int time)
	{
		this.time=time;
	}
	public void run()
	{
		try
		{
			do
			{
				line=rob.GetLine(seCounter);
				if(line!=null)
				{
					act.runCommand(line,seCounter);
					setPCounter(getPCounter() + 1);
				}
				rob.act();
				seCounter++;
				if(seCounter!=time) Thread.sleep(1000);
			}
			while(keepAlive);
		}
		catch (FileNotFoundException e)
		{
			// Catch File not found exceptions.
			System.out.print("Error: file not found.\n");
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
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
}
