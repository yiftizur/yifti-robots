import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ArmAdditionTest extends TestCase 
{
	private ArmAddition rob;
	@Before
	public void setUp() throws Exception
	{
		rob=new ArmAddition(new SmartRobot(new RV1()));
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
		assertEquals(10,rob.getSpeed());
		// Make sure the shortest way is chosen for the underline Robot.
		assertEquals(-20,rob.getHeading());
		// Make sure Smart Robot Decorator saves Positive heading.
		assertEquals(330,((SmartRobot)(rob.rob)).getSHeading());
		// Test above limit speed and positive heading.
		rob.move(30,340);
		// Make sure robot follows speed limitations.
		assertEquals(10,rob.getSpeed());
		// Make sure the shortest way is chosen for the underline Robot.
		assertEquals(-20,rob.getHeading());
	}
}
