/*
 * Student Name: Yiftach Tzur
 * I.D.: 043372523
 */
import java.io.IOException;

public class Main {

	/**
	 * Main function.
	 * Runs the main program body.
	 */
	public static void main(String[] args) 
	{
		// Input string.
		String input;
		// Robots simulation object.
		Simulation sim=new Simulation();
		Boxes boxes=new Boxes();
		Programs programs=new Programs();
		// Robots command object.
		Commands com=new Commands(sim, boxes, programs);
		// Try statement.
		try 
		{
			// Read line from user.
			input = readLine();
			// While input is not exit.
			while(!input.equals("Exit"))
			{
				// Run given input as command.
				com.runCommand(input);
				// Read line from user.
				input=readLine();
			}
			// If input was exit, print bye.
			System.out.println("bye");
		} 
		// Catch any IOException.
		catch (IOException e)
		{
			// Print error caught stack trace.
			e.printStackTrace();
		}
	}
	
	/**
	 * Method: readLine
	 * Returns: String
	 * @throws IOException
	 * Description: Reads line from input stream, by reading each character 
	 * and appending it to string.
	 */
	private static String readLine() throws IOException
	{
		int ch=0;
		StringBuilder string = new StringBuilder();
		// Read first char.
		ch = System.in.read();
		// While char is not RC.
		while(ch!=10)
		{
			// If char is /r, do not append.
			if(ch!=13) string.append((char)ch);
			// Read next char from input stream.
			ch=System.in.read();
		}
		// Return read string.
		return string.toString();
	}
}
