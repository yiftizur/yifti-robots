import java.util.HashMap;

public class RobotFactory 
{
	 private interface RobotFac
	 {
		 public Robot create();
	 }
	 private class RV1Fac implements RobotFac
	 {
		 public Robot create()
		 { 
			 return new RV1();
		 }
	 }
	 private class RV2Fac implements RobotFac
	 {
		 public Robot create()
		 {
			 return new RV2();
		 }
	 }
	 private HashMap<String, RobotFac> robotFactory;
	 public RobotFactory()
	 {
		 robotFactory=new HashMap<String, RobotFac>();
		 robotFactory.put("RV1", new RV1Fac());
		 robotFactory.put("RV2", new RV2Fac());
	  }
	  public Robot createRobot(String type)
	  {
		  return ((RobotFac)robotFactory.get(type)).create();
	  }
}
