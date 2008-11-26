import java.util.HashMap;
import java.util.Scanner;


public class Commands
{
	private Simulation sim;
	private HashMap<String,Command> commandsMap;
	RobotFactory fact = new RobotFactory();
	public Commands(Simulation s)
	{
		this.sim=s;
		commandsMap=new HashMap<String, Command>();
		add(new MoveCommand());
	}
	private void add(Command c)
	{
		 commandsMap.put(c.getKey(), c);
	}
	public void runCommand(String cmd)
	{
		Scanner scn=new Scanner(cmd);
		((Command)commandsMap.get(scn.next())).execute(cmd);
	}
	public class MoveCommand implements Command
	{
		String format="Move (\\w+) speed (\\d+) heading (\\d+)";

		// return the first word of the command as the key
		public String getKey()
		{ 
			return "Move";
		}

		public boolean execute(String cmd)
		{
			if(cmd.matches(format)){// if the command we got fits the correct format
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
	public class InitCommand implements Command
	{
		String format="Init type (\\w+) at (\\d+) (\\d+) named (\\w+)";

		// return the first word of the command as the key
		public String getKey()
		{ 
			return "Init";
		}

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
				sim.addRobot(fact.createRobot(type));
				sim.getRobot(name).setStartingPosition(pos);
				System.out.printf("new %s at %d,%d named %s",type,pos.x,pos.y,name);
				return true;// correct format
			}
			return false;// wrong format
		}

	}
}
