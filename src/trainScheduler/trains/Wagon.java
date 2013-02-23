package trainScheduler.trains;


/**
 * This class is one form of AtomicTrainParts
 * We have different sub-types so we could in theory model trains that are not driveable because they don't have enough power
 * and also have maximum seats counts of trains
 */
public class Wagon extends AtomicTrainPart {
	// c'tor
	public Wagon(String id, int seats, int maxSpeed){
		super(id, seats, maxSpeed, 0);
	}
}
