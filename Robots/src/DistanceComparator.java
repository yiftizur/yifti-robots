import java.util.Comparator;

public class DistanceComparator implements Comparator<Robot>
{

	public int compare(Robot rob1, Robot rob2)
	{
		if (rob1.getDistance() > rob2.getDistance())
			return -1;
		if (rob1.getDistance() < rob2.getDistance())
			return 1;
		else
			return 0;
	}
}
