/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler.tracks;

import trainScheduler.stations.Station;

// atomoic track part of the composite pattern 
public class AtomicTrack extends Track{
	private int length; // length of the track
	
	// c'tor
	public AtomicTrack(String identifier, Station stationA, Station stationB, int length){
		super(identifier, stationA, stationB);
		this.length = length;
	}
	
	public int getLength(){
		return this.length;
	}
	
}
