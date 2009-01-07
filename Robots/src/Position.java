
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
	public Position(Position currentPosition)
	{
		this.x=currentPosition.x;
		this.y=currentPosition.y;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Position o)
	{
		if((this.x==o.x) && (this.y==o.y)) return 0;
		else return 1;
	}
	public int equals(Position o)
	{
		if((this.x==o.x) && (this.y==o.y)) return 0;
		else return 1;
	}
	public String toString()
	{
		return String.format("%d,%d",x,y);
	}
}
