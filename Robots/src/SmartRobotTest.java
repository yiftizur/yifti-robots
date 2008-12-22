import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;


public class SmartRobotTest extends TestCase
{
	private SmartRobot rob;
	private Position pos;
	@Before
	public void setUp() throws Exception
	{
		rob=new SmartRobot(new RV1());
		pos=new Position(0,0);
	}

	@After
	public void tearDown() throws Exception
	{
		rob=null;
	}
	@Test
	/**
	 * testMove - Test the move method of SmartRobot.
	 * Test that the headings are updated in the Robot according to the
	 * shortest way to go.
	 */
	public void testMove()
	{
		// Test above limit speed and negative heading.
		rob.move(20, -30);
		// Make sure robot follows speed limitations.
		assertEquals(10,rob.rob.getSpeed());
		// Make sure Smart Robot Decorator saves Positive heading.
		assertEquals(330,rob.getSHeading());
		// Make sure the shortest way is chosen for the underline Robot.
		assertEquals(-20,rob.rob.getHeading());
		// Test above limit speed and positive heading.
		rob.move(30,340);
		// Make sure robot follows speed limitations.
		assertEquals(10,rob.rob.getSpeed());
		// Make sure Smart Robot Decorator saves Positive heading.
		assertEquals(340,rob.getSHeading());
		// Make sure the shortest way is chosen for the underline Robot.
		assertEquals(-20,rob.rob.getHeading());
	}

	@Test
	public void testAct()
	{
		fail("Not yet implemented");
	}

}
