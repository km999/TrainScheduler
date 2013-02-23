/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler.tracks;

import trainScheduler.stations.Station;
import trainScheduler.templates.AbstractRegisterable;

// parent class of the composite pattern
public abstract class Track extends AbstractRegisterable{

	private Station stationA; // this are the connected stations
	private Station stationB;
	
	// c'tor
	public Track(String identifier, Station stationA, Station stationB){
		super(identifier);
		this.stationA = stationA;
		this.stationB = stationB;
	}
	
	// get the connected stations
	public Station[] getStations(){
		Station[] stations = new Station[2];
		stations[0] = this.stationA;
		stations[1] = this.stationB;
		return stations;
	}
	
	// method stub
	public abstract int getLength();
}
