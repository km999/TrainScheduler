/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler.queries;

import java.util.ArrayList;

import trainScheduler.stations.Station;
import trainScheduler.tracks.Track;

// see if we can connect two stations over a set of Tracks
public class QueryMinOfTrackWeights extends AbstractQuery {
	// c'tor
	public QueryMinOfTrackWeights(ArrayList<Station> stations, ArrayList<Track> tracks) {
		super(stations);
		this.weighted = true;
		this.edges = new ArrayList<FromToWeightTuple>();
		this.initEdges(tracks);
	}
	
	// make the tracks our edges, weight is their length
	// add 2 edges for each track since the tracks are undirected
	private void initEdges(ArrayList<Track> tracks){
		for (Track t: tracks){
			Station[] trackStations = t.getStations();
			Station a = trackStations[0];
			Station b = trackStations[1];
			
			this.edges.add(new FromToWeightTuple(a, b, t.getLength()));
			this.edges.add(new FromToWeightTuple(b, a, t.getLength()));
		}
	}
}
