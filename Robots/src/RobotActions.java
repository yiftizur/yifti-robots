import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


/**
 * RobotActions Class.
 * Class for executing commands from a robots assigned program.
 */
public class RobotActions
{
	Robot rob;
	// The Boxes object for calling and manipulating boxes.
	private Boxes boxes;
	private HashMap<String,Command> commandsMap;
	/**
	 * RobotActions Constructor
	 * Description: Creates new object with provided robot and Boxes.
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
	
	/**
	 * Method: add
	 * Returns: void
	 * Description: Add given command to Commands hashmap.
	 */
	
	private void add(Command c)
	{
		 commandsMap.put(c.getKey(), c);
	}
	
	
	/**
	 * Method: runCommand
	 * Returns: void
	 * @param cmd
	 * @param time
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws PrgErrException
	 * Description: Runs the provided command according to command key word.
	 */
	
	public void runCommand(String cmd) throws FileNotFoundException, IOException, PrgErrException
	{
		// Get second word in command.
		String firstword=cmd.split(" ")[1];
		// If command exists in hashmap.
		if(commandsMap.get(firstword)!=null)
		{
			// If command completed successfully.
			if((commandsMap.get(firstword)).execute(cmd));
			// Else throw exception for error.
			else throw new PrgErrException("Error in Commands file");
		}
		// Else throw exception for error.
		else throw new PrgErrException("Error in Commands file");
	}
	
	/**
	 * Method: GetComTime
	 * Returns: int
	 * @param cmd
	 * @return
	 * Description: Returns Command's time from line.
	 */
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
				int speed=Integer.parseInt(scn.match().group(2));
				int heading=Integer.parseInt(scn.match().group(3));
				rob.move(speed, heading);
				return true;
			}
			return false;// wrong format
		}
	}
	
	/**
	 * StopCommand class
	 * Implementation of the Command interface for Stop commands.
	 */
	
	public class StopCommand implements Command
	{
		String format="(\\d+) Stop";
		// return the first word of the command as the key
		public String getKey()
		{ 
			return "Stop";
		}
		
		/*
		 * Execute command - implements method from interface.
		 * Runs appropriate command for current class.
		 */
		
		public boolean execute(String cmd)
		{
			// if the command we got fits the correct format
			if(cmd.matches(format))
			{
				// Stop robot's movement.
				rob.stop();
				return true;
			}
			return false;
		}
		
	}
	
	/**
	 * PickupCommand class
	 * Implementation of the Command interface for Pickup commands.
	 */
	
	public class PickupCommand implements Command
	{
		String format="(\\d+) Pickup (\\w+)";
		// return the first word of the command as the key
		public String getKey()
		{ 
			return "Pickup";
		}
		
		/*
		 * Execute command - implements method from interface.
		 * Runs appropriate command for current class.
		 */
		
		public boolean execute(String cmd) throws PrgErrException
		{
			// if the command we got fits the correct format
			if(cmd.matches(format))
			{
				Scanner scn=new Scanner(cmd);
				scn.findInLine(format);// scans according to the format
				// Get variables.
				String box=scn.match().group(2);
				// get Box.
				Box b=boxes.getBox(box);
				// Check box exists.
				if(b==null)
				{
					// If not, throw exception with appropriate message.
					throw new PrgErrException(String.format("\t%s on %s: Error: no box by that name.\n",
							rob.getProgram().toString(),rob.getName()));
				}
				// Check pickup was successful.
				if(rob.pickupBox(b));
				// If not, throw exception with appropriate message.
				else throw new PrgErrException(String.format("\t%s on %s: %s is unable to pickup %s\n",
							rob.getProgram().toString(),rob.getName(),rob.getName(),b.getName()));
				return true;
			}
			return false;
		}
	}
	
	/**
	 * PutDownCommand class
	 * Implementation of the Command interface for Putdown commands.
	 */
	
	public class PutDownCommand implements Command
	{
		String format="(\\d+) PutBoxDown";
		// return the first word of the command as the key
		public String getKey()
		{ 
			return "PutBoxDown";
		}
		
		/*
		 * Execute command - implements method from interface.
		 * Runs appropriate command for current class.
		 */
		
		public boolean execute(String cmd)
		{
			// if the command we got fits the correct format
			if(cmd.matches(format))
			{
				// Check if put down was not successful.
				if(!rob.putdownBox()) ErrorPrint.PrintError(String.format
						("\t%s on %s: Error: %s has no box.\n",rob.getProgram().
								toString(),rob.getName(),rob.getName()));
				return true;
			}
			return false;
		}
		
	}
}



