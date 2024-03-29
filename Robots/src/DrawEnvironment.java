import java.util.ArrayList;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Canvas;

/**
 * DrawEnvironment Class
 * Class for drawing environment on canvas.
 * Drawing each object in current simulation.
 */
public class DrawEnvironment implements PaintListener{
	Simulation sim;
	Boxes boxes;
	public DrawEnvironment(Simulation sim, Boxes boxes)
	{
		this.sim=sim;
		this.boxes=boxes;
	}
	
	// the drawing implemented method
	public void paintControl(PaintEvent e) {		
		Canvas c=(Canvas)e.widget;	// get the canvas
		int maxX = c.getSize().x;	// max size
		int maxY = c.getSize().y;
		int mx=maxX/2,my=maxY/2;	// mid point as (0,0)

		e.gc.drawOval(mx-2, my-2, 4, 4);	// point at the middle.	
		// Get a list of all boxes.
		ArrayList<Box> list=new ArrayList<Box>();
		list.addAll(boxes.getNames());
		// Go over lists of robots and boxes and paint each one of them.
		for(int i=0;i<sim.GetSize();i++)
		{
			sim.robList.get(i).paint(e);
		}
		for(int i=0;i<list.size();i++)
		{
			list.get(i).paint(e);
		}
	}
}
