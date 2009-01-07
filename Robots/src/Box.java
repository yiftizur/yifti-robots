import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;


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
	public void paint(PaintEvent e)
	{
		Canvas c=(Canvas)e.widget;	// get the canvas
		int maxX = c.getSize().x;	// max size
		int maxY = c.getSize().y;
		int mx=maxX/2,my=maxY/2;	// mid point as (0,0)
		int myX=mx+location.x;	// set the current position
		int myY=my-location.y;	// set the current position
			
		e.gc.drawRectangle(new Rectangle(myX-7, myY-7,14,14));
		e.gc.drawText("B", myX-2, myY-6,SWT.DRAW_TRANSPARENT);
			
		// the info
		if(!isPicked)
		{
			e.gc.drawString(name,myX+13, myY-15,true);
			e.gc.drawString("("+location.x+","+location.y+")",myX+13, myY+1,true);
		}
		else
		{
			e.gc.drawString(name,myX+13, myY+26,true);
		}
	}

}
