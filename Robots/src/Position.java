
/**
 * Position class.
 * Holds x,y coordinates.
 */
public class Position implements Comparable<Position>
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
	
	
	/**
	 * Position Constructor
	 * Description: Creates position object with given position object.
	 */
	public Position(Position currentPosition)
	{
		this.x=currentPosition.x;
		this.y=currentPosition.y;
	}
	
	
	/**
	 * Method: compareTo
	 * Overrides: @see java.lang.Comparable#compareTo(java.lang.Object)
	 * Returns: int.
	 * Description: Implementation of the compareTo method for this class.
	 */
	
	public int compareTo(Position o)
	{
		// Check that both x and y are equal to those of provided position.
		if((this.x==o.x) && (this.y==o.y)) return 0;
		else return 1;
	}
	
	/**
	 * Method: equals
	 * Overrides: @see java.lang.Comparable#compareTo(java.lang.Object)
	 * Returns: int.
	 * Description: Implementation of the equals method for this class.
	 */
	
	public int equals(Position o)
	{
		// Check that both x and y are equal to those of provided position.
		if((this.x==o.x) && (this.y==o.y)) return 0;
		else return 1;
	}
	
	
	/**
	 * Method: toString
	 * Overrides: @see java.lang.Object#toString()
	 * Returns: String
	 * Description: Implementation of the toString method for this class.
	 */
	public String toString()
	{
		// format string with position x and y coordinates.
		return String.format("%d,%d",x,y);
	}
}
