
public class Commands
{
	private Simulation sim;
	public Commands(Simulation s)
	{
		sim=s;
	}
	public void runCommand(String cmd)
	{
		int index,speed,heading;
		String words[]=cmd.split(" ");// splits the command into single words
		if(words[0].equalsIgnoreCase("move")){// move
			index=Integer.parseInt(words[1]);// get the robot's index
			if(words[2].equalsIgnoreCase("speed")){
				speed=Integer.parseInt(words[3]);// get the speed
				if(words[4].equalsIgnoreCase("heading")){
					heading=Integer.parseInt(words[5]);// get the heading
					sim.getRobot(index-1).move(speed, heading);// get the robot to do that
				}
			}
		}
	}
}
