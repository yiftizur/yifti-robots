import java.util.Scanner;


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
