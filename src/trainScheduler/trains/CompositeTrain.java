package trainScheduler.trains;

import java.util.ArrayList;
import java.util.List;

/**
 * composite trains consist of multiple trainparts.
 * their maxSpeed is the minimum of the maxSpeed attributes of the atomic parts it comprises
 * the length is the total number of atomic trains
 */
public class CompositeTrain extends Train {
	
	// stores all the parts of the trains
	private List<Train> trainparts;
	
	// c'tor
	public CompositeTrain(String id){
		super(id);
		this.trainparts = new ArrayList<Train>();
	}
	
	// c'tor
	public CompositeTrain(String id, List<Train> tp){
		super(id);
		this.trainparts = tp;
	}
	
	// adds a train to the composite
	public void addTrainPart(Train t){
		this.trainparts.add(t);
	}
	
	// returns all trainparts
	public List<Train> getTrainPartsAsList(){
		return this.trainparts;
	}
	
	@Override
	public int getMaxSpeed() {
		int maxSpeed = -1;
		for (Train t : this.trainparts){
			if (maxSpeed < 0){
				maxSpeed = t.getMaxSpeed();
			} else {
				maxSpeed = (t.getMaxSpeed() < maxSpeed) ? t.getMaxSpeed() : maxSpeed;
			}
		}
		return maxSpeed;
	}
	
}
