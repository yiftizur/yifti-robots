import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;


public class Commands
{
	// The simulation object for calling and manipulating robots.
	private Simulation sim;
	// The Boxes object for calling and manipulating boxes.
	private Boxes boxes;
	// Hashmap for holding command name,command pairs.
	private HashMap<String,Command> commandsMap;
	// Robot factory object for creating robots by string.
	RobotFactory fact = new RobotFactory();
	File fl=new File(".");
	/**
	 * Commands Constructor
	 * Description: Constructs the commands object with the provided simulation
	 * Adds all command factory classes into hashmap for execute command. 
	 */
	public Commands(Simulation s,Boxes b)
	{
		this.sim=s;
		this.boxes=b;
		// Initialize the hashmap, with all command classes.
		commandsMap=new HashMap<String, Command>(10);
		add(new MoveCommand());
		add(new GetCommand());
		add(new InitCommand());
		add(new SimCommand());
		add(new ListCommand());
		add(new FileCommand());
		add(new LoadCommand());
		add(new putBoxCommand());
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
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void runCommand(String cmd) throws FileNotFoundException, IOException
	{
		Scanner scn=new Scanner(cmd);
		String firstword=scn.next();
		if(commandsMap.get(firstword)!=null)
		{
			if((commandsMap.get(firstword)).execute(cmd));
			else System.out.println("Error: command not found.");
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
				Robot rob=sim.getRobot(name);
				if(rob!=null)
				{
					// do the required action
					sim.getRobot(name).move(speed, heading);
					return true;// correct format
				}
				else System.out.println("Error: no such robot with this name.");
				return true;
			}
			return false;// wrong format
		}

	}
	/**
	 * InitCommand class
	 * Implementation of the Command interface for Init commands.
	 */
	public class InitCommand implements Command
	{
		String format="Init type (\\w+) at (-?\\d+) (-?\\d+) named (\\w+)";
		String formatwith="Init type (\\w+) at (-?\\d+) (-?\\d+) named (\\w+) with (\\w+)";
		// return the first word of the command as the key
		public String getKey()
		{ 
			return "Init";
		}
		// Execute command from string.
		public boolean execute(String cmd)
		{
			if(cmd.matches(formatwith))
			{
				Scanner scn=new Scanner(cmd);
				scn.findInLine(format);// scans according to the format

				// get the variables
				String type=scn.match().group(1);
				Position pos=new Position(scn.match().group(2),scn.match().group(3));
				String name=scn.match().group(4);
				String addition=scn.match().group(5);
				// do the required action
				Robot rob=fact.createRobot(type);
				if(rob!=null)
				{
					rob.setName(name);
					rob.setStartingPosition(pos);
					if(addition.equals("arm"))
					{
						ArmAddition robby=new ArmAddition(rob);
						sim.addRobot(robby);
						System.out.printf("new Robot with %s of %s at %d,%d named %s\n",addition,type,pos.x,pos.y,name);
					}
					else if(addition.equals("smartKit"))
					{
						SmartRobot robby=new SmartRobot(rob);
						sim.addRobot(robby);
						System.out.printf("new Smart Robot of %s at %d,%d named %s\n",type,pos.x,pos.y,name);
					}
					else if(addition.equals("all"))
					{
						ArmAddition robby=new ArmAddition(new SmartRobot(rob));
						sim.addRobot(robby);
						System.out.printf("new Robot with arm os SmartRobot of %s at %d,%d named %s\n",type,pos.x,pos.y,name);
					}
					else System.out.println("Error: no such addition.");
					return true;// correct format
				}
				// If the provided robot type is invalid.
				else System.out.println("Error: no such robot type.");
				return true;// correct format
			}
			else if(cmd.matches(format))
			{// if the command we got fits the correct format
				Scanner scn=new Scanner(cmd);
				scn.findInLine(format);// scans according to the format

				// get the variables
				String type=scn.match().group(1);
				Position pos=new Position(scn.match().group(2),scn.match().group(3));
				String name=scn.match().group(4);
				// do the required action
				Robot rob=fact.createRobot(type);
				// If the provided type is valid.
				if(rob!=null)
				{
					rob.setName(name);
					rob.setStartingPosition(pos);
					sim.addRobot(rob);
					System.out.printf("new %s at %d,%d named %s\n",type,pos.x,pos.y,name);
				}
				// If the provided robot type is invalid.
				else System.out.println("Error: no such robot type.");
				return true;// correct format
			}
			return false;// wrong format
		}
	}
	/**
	 * GetCommand class
	 * Implementation of the Command interface for Get commands.
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
				Box box=boxes.getBox(name);
				// If the provided type is valid.
				if(rob!=null)
				{
					Position pos=rob.getCurrentPosition();
					System.out.printf("robot %s is at %d,%d\n",name,pos.x,pos.y);
				}
				else if(box!=null)
				{
					Position pos=box.getPosition();
					System.out.printf("box %s is at %d,%d\n",name,pos.x,pos.y);
				}
				// If the provided robot type is invalid.
				else System.out.println("Error: no such robot or box with this name.");
				return true;// correct format
			}
			return false;// wrong format
		}
	}
	
	/**
	 * SimCommand class
	 * Implementation of the Command interface for Simulate commands.
	 */
	
	public class SimCommand implements Command
	{
		String format="Simulate (\\w+) (\\d+) steps";

		// return the first word of the command as the key
		public String getKey()
		{ 
			return "Simulate";
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
	
	/**
	 * DistanceComparator class
	 * Implementation of the Comparator interface for Comparing robot distances.
	 */
	
	private class DistanceComparator implements Comparator<Robot>
	{
		// Implementation of the compare function.
		public int compare(Robot rob1, Robot rob2)
		{
			// Return result according to comparison in distances.
			if (rob1.getDistance() > rob2.getDistance())
				return 1;
			if (rob1.getDistance() < rob2.getDistance())
				return -1;
			else
				return 0;
		}
	}
	
	/**
	 * ListCommand class
	 * Implementation of the Command interface for List commands.
	 */
	
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
				// If list type is distance.
				if(type.equalsIgnoreCase("distance"))
				{
					// Sort the Arraylist in simulation by distance.
					Collections.sort(sim.robList, new DistanceComparator());
					Iterator<Robot> it=sim.robList.iterator();
					// Go over list with iterator.
					while (it.hasNext())
					{
						// Calculate and print distance for each robot in list.
						Robot robot=it.next();
						double distance=robot.getDistance();
						int index=sim.robList.indexOf(robot);
						System.out.format("%d. %s distance %.0f\n",index+1,robot.getName(),distance);
					}
				}
				// If list type is name.
				else if(type.equalsIgnoreCase("name"))
				{
					// Create new ArrayList from Simulation hashmap values.
					ArrayList<Robot> roblist=new ArrayList<Robot>();
					roblist.addAll(sim.getNameMap());
					// Sort list with implemented comparable.
					Collections.sort(roblist);
					Iterator<Robot> it=roblist.iterator();
					// Go over list with iterator.
					while (it.hasNext())
					{
						// Get and print robot's name.
						Robot robot=it.next();
						int index=roblist.indexOf(robot);
						System.out.printf("%d. %s\n",index+1,robot.getName());
					}
				}
				else System.out.println("Error: command not found.");// wrong format
				return true;// correct format
			}
			return false;// wrong format
		}
	}
	
	/**
	 * FileCommand class
	 * Implementation of the Command interface for File commands.
	 */
	
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
				// Get list of files from curremt dir.
				File[] names=fl.listFiles();
				// Go over list of files.
				for(int i=0;i<names.length;i++)
				{
					// Print d for dir, f for file.
					if(names[i].isDirectory()) System.out.printf("\t[d] %s\n",names[i].getName());
					else System.out.printf("\t[f] %s\n",names[i].getName());
				}
				return true;
			}
			return false;
		}
	}
	
	/**
	 * LoadCommand class
	 * Implementation of the Command interface for Load commands.
	 */
	
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
				scn.findInLine(format);// scans according to the format
				// get the variables
				String filename=scn.match().group(1);
				String line;
				// Try statement for buffer reading.
				try
				{
					// Create new buffer reader.
					BufferedReader br=new BufferedReader(
							new FileReader(filename));
					// Read lines from file while not EOF.
					while((line=br.readLine())!=null)
					{
						// Print read line, and run it as command.
						System.out.printf(">> %s\n",line);
						runCommand(line);
					}
				} catch (FileNotFoundException e)
				{
					// Catch File not found exceptions.
					System.out.print("Error: file not found.\n");
				}
				return true;
			}
			return false;
		}
	}
	public class putBoxCommand implements Command
	{
		String format="PutBox at (-?\\d+) (-?\\d+) named (\\w+)";
		/* (non-Javadoc)
		 * @see Command#execute(java.lang.String)
		 */
		@Override
		public boolean execute(String cmd)
		{
			if(cmd.matches(format)){// if the command we got fits the correct format
				Scanner scn=new Scanner(cmd);
				scn.findInLine(format);// scans according to the format
				// get the variables
				Position pos=new Position(scn.match().group(1),scn.match().group(2));
				String name=scn.match().group(3);
				// do the required action
				Box box=new Box(name);
				boxes.AddBox(box);
				box.SetPosition(pos);
				return true;// correct format
			}
			return false;// wrong format
		}

		/* (non-Javadoc)
		 * @see Command#getKey()
		 */
		@Override
		public String getKey()
		{
			return "PutBox";
		}
		
	}
}
