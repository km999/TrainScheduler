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
