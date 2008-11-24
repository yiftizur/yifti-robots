import java.io.IOException;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String input;
		try 
		{
			input = readLine();
			while(input!="exit")
			{
				Simulation sim=new Simulation();
				Commands com=new Commands(sim);
				com.runCommand(input);
				input=readLine();
			}
			System.out.println("bye");
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private static String readLine() throws IOException
	{
		int ch=0;
		StringBuilder string = new StringBuilder();
		ch = System.in.read();
		while(ch!=10)
		{
			if(ch!=13) string.append((char)ch);
			ch=System.in.read();
		}
		return string.toString();
	}
}
