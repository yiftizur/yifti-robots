import java.util.Map;


public class Commands
{
	private Simulation sim;
	RobotFactory fact = new RobotFactory();
	public Commands(Simulation s)
	{
		sim=s;
	}
	public void runCommand(String cmd)
	{
		String words[]=cmd.split(" ");// splits the command into single words
		if(words[0].equalsIgnoreCase("move")) cmdMove(words);
		else if(words[0].equalsIgnoreCase("init")) cmdInit(words);
		else if(words[0].equalsIgnoreCase("simulate")) cmdSim(words);
		else if(words[0].equalsIgnoreCase("get")) cmdGet(words);
		else System.out.println("Error: command not found.");
	}
	private void cmdMove(String[] words)
	{
		int index,speed,heading;
		index=Integer.parseInt(words[1]);// get the robot's index
		speed=Integer.parseInt(words[3]);// get the speed
		heading=Integer.parseInt(words[5]);// get the heading
		sim.getRobot(index-1).move(speed, heading);
	}
	private void cmdInit(String[] words)
	{
		Position pos=new Position(words[4],words[5]);
		int index=sim.addRobot(fact.createRobot(words[2]));
		sim.getRobot(index).setStartingPosition(pos);
		System.out.printf("new %s at %d,%d indexed %d\n",words[2],pos.x,pos.y,index+1);
	}
	private void cmdSim(String[] words)
	{
		int index=Integer.parseInt(words[1]);
		int times=Integer.parseInt(words[2]);
		for(int i=0;i<times;i++)
		{
			sim.getRobot(index).act();
		}
	}
	private void cmdGet(String[] words)
	{
		int index=Integer.parseInt(words[1]);
		Position pos=sim.getRobot(index).getCurrentPosition();
		System.out.printf("robot %d is at %d %d\n",index,pos.x,pos.y);
	}
}
