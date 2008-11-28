import java.util.HashMap;
import java.util.Scanner;


public class Commands
{
	// The simulation object for calling and manipulating robots.
	private Simulation sim;
	// Hashmap for holding command name,command pairs.
	private HashMap<String,Command> commandsMap;
	// Robot factory object for creating robots by string.
	RobotFactory fact = new RobotFactory();
	
	/**
	 * @param s Constructor
	 * Description: 
	 */
	public Commands(Simulation s)
	{
		this.sim=s;
		commandsMap=new HashMap<String, Command>();
		add(new MoveCommand());
	}
	/**
	 * Method: add
	 * Returns: void
	 * @param c
	 * Description:
	 */
	private void add(Command c)
	{
		 commandsMap.put(c.getKey(), c);
	}
	/**
	 * Method: runCommand
	 * Returns: void
	 * @param cmd
	 * Description:
	 */
	public void runCommand(String cmd)
	{
		Scanner scn=new Scanner(cmd);
		if(((Command)commandsMap.get(scn.next())).execute(cmd));
		else System.out.print("Error: command not found.");
	}
	/**
	 * @author Administrator
	 *
	 */
	public class MoveCommand implements Command
	{
		String format="Move (\\w+) speed (\\d+) heading (\\d+)";

		// return the first word of the command as the key
		/* (non-Javadoc)
		 * @see Command#getKey()
		 */
		public String getKey()
		{ 
			return "Move";
		}

		/* (non-Javadoc)
		 * @see Command#execute(java.lang.String)
		 */
		public boolean execute(String cmd)
		{
			// if the command we got fits the correct format
			if(cmd.matches(format))
			{
				Scanner scn=new Scanner(cmd);
				scn.findInLine(format);// scans according to the format

				// get the variables
				String name=scn.match().group(1);
				int speed=Integer.parseInt(scn.match().group(2));
				int heading=Integer.parseInt(scn.match().group(3));

				// do the required action
				sim.getRobot(name).move(speed, heading);

				return true;// correct format
			}
			return false;// wrong format
		}

	}
	/**
	 * @author Administrator
	 *
	 */
	public class InitCommand implements Command
	{
		String format="Init type (\\w+) at (\\d+) (\\d+) named (\\w+)";

		// return the first word of the command as the key
		public String getKey()
		{ 
			return "Init";
		}
		// Execute command from string.
		public boolean execute(String cmd)
		{
			if(cmd.matches(format)){// if the command we got fits the correct format
				Scanner scn=new Scanner(cmd);
				scn.findInLine(format);// scans according to the format

				// get the variables
				String type=scn.match().group(1);
				Position pos=new Position(scn.match().group(2),scn.match().group(3));
				String name=scn.match().group(4);
				// do the required action
				Robot rob=fact.createRobot(type);
				if(rob!=null)
				{
					sim.addRobot(rob);
					sim.getRobot(name).setStartingPosition(pos);
					System.out.printf("new %s at %d,%d named %s",type,pos.x,pos.y,name);
				}
				else System.out.print("Error: no such robot type");
				return true;// correct format
			}
			return false;// wrong format
		}
	}
	/**
	 * @author Administrator
	 *
	 */
	public class GetCommand implements Command
	{
		String format="Get (\\w+) position";

		// return the first word of the command as the key
		public String getKey()
		{ 
			return "Get";
		}
		// Execute command from string.
		public boolean execute(String cmd)
		{
			if(cmd.matches(format)){// if the command we got fits the correct format
				Scanner scn=new Scanner(cmd);
				scn.findInLine(format);// scans according to the format
				// get the variables
				String name=scn.match().group(1);
				// do the required action
				Robot rob=sim.getRobot(name);
				if(rob!=null)
				{
					Position pos=rob.getCurrentPosition();
					System.out.printf("robot %s is at %d,%d",name,pos.x,pos.y);
				}
				else System.out.print("Error: no such robot with this name.");
				return true;// correct format
			}
			return false;// wrong format
		}
	}
}
