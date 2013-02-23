/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler.trains;

/**
 * This class is a general type for atomic parts of a train
 * AtomicTrains have a maxSpeed, a number of seats and power (capability of moving itself and other trains)
 * the length is 1
 */
public abstract class AtomicTrainPart extends Train {
	protected int seats; // number of seats in the atomic train
	private int maxSpeed; // maximum speed this trainpart can go
	protected int power; // the number of atomic trainparts this train can pull (including itself)
	protected int length; // the length of the train, will be 1 for all atomic parts
	
	// c'tor
	public AtomicTrainPart(String id, int seats, int maxSpeed, int power){
		super(id);
		this.maxSpeed = maxSpeed;
		this.power = power;
		this.seats = seats;
		this.length = 1;
	}
	
	@Override
	public int getMaxSpeed(){
		return this.maxSpeed;
	}
}
