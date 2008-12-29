import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


public class RobotActions
{
	Robot rob;
	// The Boxes object for calling and manipulating boxes.
	private Boxes boxes;
	private int recTime;
	private HashMap<String,Command> commandsMap;
	/**
	 * @param sim
	 * @param boxes Constructor
	 * Description: 
	 */
	public RobotActions(Robot rob,Boxes b)
	{
		this.rob=rob;
		this.boxes=b;
		// Initialize the hashmap, with all command classes.
		commandsMap=new HashMap<String, Command>(10);
		add(new MoveCommand());
		add(new StopCommand());
		add(new PickupCommand());
		add(new PutDownCommand());
	}
	private void add(Command c)
	{
		 commandsMap.put(c.getKey(), c);
	}
	public void runCommand(String cmd,int time) throws FileNotFoundException, IOException
	{
		
		String firstword=cmd.split(" ")[1];
		this.recTime=time;
		if(commandsMap.get(firstword)!=null)
		{
			if((commandsMap.get(firstword)).execute(cmd));
			else System.out.println("Error: command not found.");
		}
		else System.out.println("Error: command not found.");
	}
	public int GetComTime(String cmd)
	{
		return Integer.parseInt(cmd.split(" ")[0]);
	}
	/**
	 * MoveCommand class
	 * Implementation of the Command interface for Move commands.
	 */
	
	public class MoveCommand implements Command
	{
		String format="(\\d+) Move speed (-?\\d+) heading (-?\\d+)";

		// return the first word of the command as the key
		public String getKey()
		{ 
			return "Move";
		}

		// Execute method from interface.
		public boolean execute(String cmd)
		{
			// if the command we got fits the correct format
			if(cmd.matches(format))
			{
				Scanner scn=new Scanner(cmd);
				scn.findInLine(format);// scans according to the format

				// get the variables
				int secs=Integer.parseInt(scn.match().group(1));
				int speed=Integer.parseInt(scn.match().group(2));
				int heading=Integer.parseInt(scn.match().group(3));
				if(secs==recTime)
				{
					rob.move(speed, heading);
				}
				return true;
			}
			return false;// wrong format
		}

	}
	public class StopCommand implements Command
	{
		String format="(\\d+) Stop";
		// return the first word of the command as the key
		public String getKey()
		{ 
			return "Stop";
		}
		/* (non-Javadoc)
		 * @see Command#execute(java.lang.String)
		 */
		@Override
		public boolean execute(String cmd)
		{
			if(cmd.matches(format))
			{
				Scanner scn=new Scanner(cmd);
				scn.findInLine(format);// scans according to the format
				int secs=Integer.parseInt(scn.match().group(1));
				if(secs==recTime)
				{
					rob.stop();
				}
				return true;
			}
			return false;
		}
		
	}
	public class PickupCommand implements Command
	{
		String format="(\\d+) Pickup (\\w+)";
		// return the first word of the command as the key
		public String getKey()
		{ 
			return "Pickup";
		}
		/* (non-Javadoc)
		 * @see Command#execute(java.lang.String)
		 */
		@Override
		public boolean execute(String cmd)
		{
			if(cmd.matches(format))
			{
				Scanner scn=new Scanner(cmd);
				scn.findInLine(format);// scans according to the format
				int secs=Integer.parseInt(scn.match().group(1));
				String box=scn.match().group(2);
				Box b=boxes.getBox(box);
				if(b==null)
				{
					System.out.format("\t%s on %s: Error: no box by that name.\n",
							rob.getProgram().toString(),rob.getName());
					return true;
				}
				if(secs==recTime)
				{
					if(rob.pickupBox(b));
					else System.out.format("\t%s on %s: %s is unable to pickup %s\n",
							rob.getProgram().toString(),rob.getName(),rob.getName(),b.getName());
				}
				return true;
			}
			return false;
		}
		
	}
	public class PutDownCommand implements Command
	{
		String format="(\\d+) PutBoxDown";
		// return the first word of the command as the key
		public String getKey()
		{ 
			return "PutBoxDown";
		}
		/* (non-Javadoc)
		 * @see Command#execute(java.lang.String)
		 */
		@Override
		public boolean execute(String cmd)
		{
			if(cmd.matches(format))
			{
				Scanner scn=new Scanner(cmd);
				scn.findInLine(format);// scans according to the format
				int secs=Integer.parseInt(scn.match().group(1));
				if(secs==recTime)
				{
					if(!rob.putdownBox()) 
						System.out.format("\t%s on %s: Error: %s has no box.\n",
								rob.getProgram().toString(),rob.getName(),rob.getName());
				}
				return true;
			}
			return false;
		}
		
	}
}



