
public class Position 
{
	public int x;
	public int y;
	public Position(int posx, int posy)
	{
		x=posx;
		y=posy;
	}
	public Position(String string, String string2) 
	{
		// TODO Auto-generated constructor stub
		x=Integer.parseInt(string);
		y=Integer.parseInt(string2);
	}
}
