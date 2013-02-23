/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler.trains;

/**
 * This class is one form of AtomicTrainParts
 * We have different sub-types so we could in theory model trains that are not driveable because they don't have enough power
 * and also have maximum seats counts of trains
 */
public class Locomotive extends AtomicTrainPart {
	// c'tor
	public Locomotive(String id, int power, int maxSpeed){
		super(id, 0, maxSpeed, power);
	}
}
