
/**
 * Position class.
 * Holds x,y coordinates.
 */
public class Position 
{
	public int x;
	public int y;
	/**
	 * Position Constructor
	 * Description: Creates position object with x,y as coordinates. 
	 */
	public Position(int posx, int posy)
	{
		x=posx;
		y=posy;
	}
	/**
	 * Position Constructor
	 * Description: Creates position object, parse the string into integers
	 * and uses as coordinates. 
	 */
	public Position(String string, String string2) 
	{
		x=Integer.parseInt(string);
		y=Integer.parseInt(string2);
	}
}
