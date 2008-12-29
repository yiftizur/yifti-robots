import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class RunProgram implements Runnable
{
	Robot rob;
	File program;
	private static int pCounter;
	private int seCounter;
	private RobotActions act;
	private Boxes boxes;
	private BufferedReader br;
	private int time;
	private String line;
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
		program=rob.getProgram();
		act=new RobotActions(rob,boxes);
		RunProgram.pCounter=0;
		// Create new buffer reader.
		br=new BufferedReader(new FileReader(program));
	}
	public void setTime(int time)
	{
		this.time=time;
	}
	public void run()
	{
		try
		{
			if(seCounter==0) line=br.readLine();
			do
			{
				if(act.GetComTime(line)==seCounter)
				{
					act.runCommand(line,seCounter);
					System.out.format("\t%s on %s: %s\n",
							program.getName(),rob.getName(),line);
					setPCounter(getPCounter() + 1);
					line=br.readLine();
				}
				//if(pCounter>1) br.reset();
				if(line==null) seCounter=time;
				rob.act();
				seCounter++;
				if(seCounter!=time) Thread.sleep(1000);
			}
			while(seCounter<=time);
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
