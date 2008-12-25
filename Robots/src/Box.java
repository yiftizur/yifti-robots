
public class Box
{
	private String name;
	private Position location;
	public boolean isPicked;
	/**
	 *  Constructor
	 * Description: 
	 */
	public Box(String bname)
	{
		this.setName(bname);
		isPicked=false;
	}
	public void SetPosition(Position pos)
	{
		location=pos;
	}
	public Position getPosition()
	{
		return location;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
}
