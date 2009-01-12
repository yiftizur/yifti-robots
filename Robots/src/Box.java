import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;


/**
 * Box Class
 * Class representing a Box with a name and a position.
 * Box can be picked up and put down by robots with arm.
 */

public class Box
{
	// Box name.
	private String name;
	// Box location.
	private Position location;
	// Box i picked up.
	public boolean isPicked;
	
	/**
	 * Box Constructor
	 * Description: Constructs a box object with given name. 
	 */
	
	public Box(String bname)
	{
		this.setName(bname);
		isPicked=false;
	}
	
	/**
	 * Method: SetPosition
	 * Returns: void
	 * @param pos
	 * Description: Sets box position with given parameter.
	 */
	
	public void SetPosition(Position pos)
	{
		location=new Position(pos);
	}
	
	
	/**
	 * Method: getPosition
	 * Returns: Position
	 * @return
	 * Description: Return Box's current location.
	 */
	
	public Position getPosition()
	{
		return location;
	}

		
	/**
	 * Method: setName
	 * Returns: void
	 * @param name
	 * Description: Set Box's name.
	 */
	
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Method: getName
	 * Returns: String
	 * @return
	 * Description: Return Box's name.
	 */
	
	public String getName()
	{
		return name;
	}
	
	
	/**
	 * Method: paint
	 * Returns: void
	 * @param e
	 * Description: Implements the paint command for a Box.
	 * Paint the box on given canvas.
	 */
	
	public void paint(PaintEvent e)
	{
		Canvas c=(Canvas)e.widget;	// get the canvas
		int maxX = c.getSize().x;	// max size
		int maxY = c.getSize().y;
		int mx=maxX/2,my=maxY/2;	// mid point as (0,0)
		int myX=mx+location.x;	// set the current position
		int myY=my-location.y;	// set the current position
		// Draw the rectangle of the box.
		e.gc.drawRectangle(new Rectangle(myX-7, myY-7,14,14));
		// Draw a capital B in the rectangle middle.
		e.gc.drawText("B", myX-2, myY-6,SWT.DRAW_TRANSPARENT);
		// the info
		// Check if box is picked.
		if(!isPicked)
		{
			// Draw box name and location.
			e.gc.drawString(name,myX+13, myY-15,true);
			e.gc.drawString("("+location.x+","+location.y+")",myX+13, myY+1,true);
		}
		else
		{
			// Draw only box name.
			e.gc.drawString(name,myX+13, myY+26,true);
		}
	}

}
