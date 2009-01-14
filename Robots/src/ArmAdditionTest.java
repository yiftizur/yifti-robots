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
	 * testMove - Test the move method of ArmAddition robot.
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
	public void testAct()
	{
		Box box1=new Box("box1");
		box1.SetPosition(new Position(49,47));
		rob.setPosition(new Position(0,0));
		// Make sure Robot cannot pickup box when not in same position.
		assertEquals(false,rob.pickupBox(box1));
		// Test speed and heading limit.
		rob.move(20,50);
		rob.act();
		assertEquals("3,9", rob.getCurrentPosition().toString());
		for(int i=0;i<6;i++)
			rob.act();
		// Test movement to correct position.
		assertEquals("49,47", rob.getCurrentPosition().toString());
		// Test Box pickup.
		assertEquals(true, rob.pickupBox(box1));
		// Test Box is picked.
		assertEquals(true, box1.isPicked);
		rob.move(10, -40);
		for(int i=0;i<6;i++)
			rob.act();
		// Verify movement.
		assertEquals("37,101", rob.getCurrentPosition().toString());
		// Test picking up a box when already holding one.
		Box box2=new Box("box2");
		box2.SetPosition(new Position(37,101));
		assertEquals(false, rob.pickupBox(box2));
		// Test movement and Box put down.
		for(int i=0;i<6;i++)
			rob.act();
		assertEquals(true,rob.putdownBox());
		// Test box location is robots location.
		assertEquals("1,149", box1.getPosition().toString());
	}
}
