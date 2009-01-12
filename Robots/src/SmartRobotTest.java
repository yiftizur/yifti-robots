import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;


/**
 * SmartRobotTest Class
 * Junit Class for testing the SmartRobot decorator.
 * tests for new smarter heading adjustments.
 */
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
		rob.move(20,-30);
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
		// Move robot back to starting point.
		rob.setStartingPosition(pos);
		// Setup robot direction.
		rob.move(10,40);
		// Make sure robot followed heading limitation.
		rob.move(10,40);
		// Turn robot more than limitation.
		rob.move(10, -20);
		// Move robot one step.
		rob.act();
		// Verify robot took one step in correct direction.
		assertEquals("3,9", rob.getCurrentPosition().toString());
		// Move robot several steps:
		
		rob.act();
		// Verify Robot adjusts direction with each movement.
		assertEquals("3,19", rob.getCurrentPosition().toString());
		rob.act();
		// Verify Robot adjusts direction with each movement.
		assertEquals("0,28", rob.getCurrentPosition().toString());
		rob.act();
		// Verify Robot adjusts direction with each movement.
		assertEquals("-3,37", rob.getCurrentPosition().toString());
				
	}

}
