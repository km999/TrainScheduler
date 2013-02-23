package trainScheduler.tracks;

import java.util.ArrayList;
import java.util.List;

import trainScheduler.stations.Station;
import trainScheduler.tracks.Track;

// the composition class for several tracks
public class Route extends Track {

	private List<Track> trackparts; // list of all trackparts
	
	// c'tor
	public Route(String identifier, Station stationA, Station stationB) {
		super(identifier, stationA, stationB);
		this.trackparts = new ArrayList<Track>();
	}
	
	// c'tor
	public Route(String identifier, Station stationA, Station stationB, Track t) {
		super(identifier, stationA, stationB);
		this.trackparts = new ArrayList<Track>();
		this.trackparts.add(t);
	}
	
	// c'tor
	public Route(String identifier, Station stationA, Station stationB, List<Track> trackparts) {
		super(identifier, stationA, stationB);
		this.trackparts = trackparts;
	}
	
	// return all tracks of this route
	public List<Track> getAllTracks(){
		return this.trackparts;
	}

	// add a new trackpart to this route
	public void addTrackPart(Track t){
		if(t != null){
			this.trackparts.add(t);
		}
	}
	
	// gets the length of the route by adding up the length of its parts
	@Override
	public int getLength() {
		int length = 0;
		for(Track t: this.trackparts){
			length += t.getLength();
		}
		
		return length;
	}

}
