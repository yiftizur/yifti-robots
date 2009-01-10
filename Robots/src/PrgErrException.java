/**
 * 
 */

/**
 * @author Administrator
 * Exception for program errors when simulation is running.
 * Describes the error message gotten from the RunProgram object 
 * during program run.
 */
public class PrgErrException extends Exception
{
	// Error message.
	private String message;

	/**
	 * PrgErrException Constructor
	 * Description: Constructs the exception with the error message given.
	 */
	public PrgErrException(String format)
	{
		this.message=format;
	}
	/**
	 * 
	 * Method: GetMessage
	 * Returns: String
	 * @return
	 * Description: Returns the Exception error Message.
	 */
	public String GetMessage()
	{
		return message;
	}
	/**
	 * long serialVersion ID.
	 */
	private static final long serialVersionUID = -6150011578805520878L;
}
