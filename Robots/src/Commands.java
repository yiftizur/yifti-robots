import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;


public class Commands
{
	// The simulation object for calling and manipulating robots.
	private Simulation sim;
	// Hashmap for holding command name,command pairs.
	private HashMap<String,Command> commandsMap;
	// Robot factory object for creating robots by string.
	RobotFactory fact = new RobotFactory();
	File fl=new File(".");
	/**
	 * @param s Constructor
	 * Description: 
	 */
	public Commands(Simulation s)
	{
		this.sim=s;
		commandsMap=new HashMap<String, Command>(10);
		add(new MoveCommand());
		add(new GetCommand());
		add(new InitCommand());
		add(new SimCommand());
		add(new ListCommand());
		add(new FileCommand());
		add(new LoadCommand());
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
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void runCommand(String cmd) throws FileNotFoundException, IOException
	{
		Scanner scn=new Scanner(cmd);
		String firstword=scn.next();
		if(commandsMap.get(firstword)!=null)
		{
			((Command)commandsMap.get(firstword)).execute(cmd);
		}
		else System.out.println("Error: command not found.");
	}
	/**
	 * MoveCommand class
	 * Implementation of the Command interface for Move commands.
	 */
	public class MoveCommand implements Command
	{
		String format="Move (\\w+) speed (-?\\d+) heading (-?\\d+)";

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
					rob.setName(name);
					rob.setStartingPosition(pos);
					sim.addRobot(rob);
					System.out.printf("new %s at %d,%d named %s\n",type,pos.x,pos.y,name);
				}
				else System.out.println("Error: no such robot type");
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
			if(cmd.matches(format))// if the command we got fits the correct format
			{
				Scanner scn=new Scanner(cmd);
				scn.findInLine(format);// scans according to the format
				// get the variables
				String name=scn.match().group(1);
				// do the required action
				Robot rob=sim.getRobot(name);
				if(rob!=null)
				{
					Position pos=rob.getCurrentPosition();
					System.out.printf("robot %s is at %d,%d\n",name,pos.x,pos.y);
				}
				else System.out.println("Error: no such robot with this name.");
				return true;// correct format
			}
			return false;// wrong format
		}
	}
	public class SimCommand implements Command
	{
		String format="Simulate (\\w+) (\\d+) steps";

		// return the first word of the command as the key
		public String getKey()
		{ 
			return "List";
		}
		public boolean execute(String cmd)
		{
			if(cmd.matches(format))// if the command we got fits the correct format
			{
				Scanner scn=new Scanner(cmd);
				scn.findInLine(format);// scans according to the format
				// get the variables
				String name=scn.match().group(1);
				int steps=Integer.parseInt(scn.match().group(2));
				// do the required action
				for(int i=0;i<steps;i++)
					sim.getRobot(name).act();
				return true;// correct format
			}			
			return false;// wrong format
		}		
	}
	public class ListCommand implements Command
	{

		String format="List robots by (\\w+)";

		// return the first word of the command as the key
		public String getKey()
		{ 
			return "List";
		}
		// Execute command from string.
		public boolean execute(String cmd)
		{
			if(cmd.matches(format)){// if the command we got fits the correct format
				Scanner scn=new Scanner(cmd);
				scn.findInLine(format);// scans according to the format
				// get the variables
				String type=scn.match().group(1);
				// do the required action
				if(type.equalsIgnoreCase("distance"))
				{
					Collections.sort(sim.robList, new DistanceComparator());
					Iterator<Robot> it=sim.robList.iterator();
					while (it.hasNext())
					{
						Robot robot=(Robot) it.next();
						Double distance=robot.getDistance();
						int index=sim.robList.indexOf(robot);
						System.out.printf("%d. %s distance %lf\n",index,robot.getName(),distance);
					}
				}
				else if(type.equalsIgnoreCase("name"))
				{
					ArrayList<Robot> roblist=new ArrayList<Robot>();
					roblist.addAll(sim.getNameMap());
					Collections.sort(roblist);
					Iterator<Robot> it=roblist.iterator();
					while (it.hasNext())
					{
						Robot robot=(Robot) it.next();
						int index=roblist.indexOf(robot);
						System.out.printf("%d. %s\n",index,robot.getName());
					}
				}
				else return false;// wrong format
				return true;// correct format
			}
			return false;// wrong format
		}
	}
	
	public class FileCommand implements Command
	{
		String format="Files";

		// return the first word of the command as the key
		public String getKey()
		{ 
			return "Files";
		}
		public boolean execute(String cmd)
		{
			if(cmd.matches(format))// if the command we got fits the correct format
			{
				File[] names=fl.listFiles();
				for(int i=0;i<names.length;i++)
				{
					if(names[i].isDirectory()) System.out.printf("\t[d] %s\n",names[i].getName());
					else System.out.printf("\t[f] %s\n",names[i].getName());
				}
				return true;
			}
			return false;
		}
	}
	public class LoadCommand implements Command
	{
		String format="Load (\\S+)";
		// return the first word of the command as the key
		public String getKey()
		{ 
			return "Load";
		}
		public boolean execute(String cmd) throws IOException
		{
			if(cmd.matches(format))// if the command we got fits the correct format
			{
				Scanner scn=new Scanner(cmd);
				boolean found=false;
				scn.findInLine(format);// scans according to the format
				// get the variables
				String filename=scn.match().group(1);
				File[] names=fl.listFiles();
				for(int i=0;i<names.length;i++)
				{
					if(names[i].getName().equals(filename))
					{
						found=true;
						String line;
						BufferedReader br=new BufferedReader(new FileReader(names[i]));
						while((line=br.readLine())!=null)
						{
							System.out.printf(">> %s",line);
							runCommand(line);
						}
						i=names.length;
					}
				}
				if(found==false) System.out.print("Error: file not found.");
				return true;
			}
			return false;
		}
	}
}
