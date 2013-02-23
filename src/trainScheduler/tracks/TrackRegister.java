/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler.tracks;

import java.util.ArrayList;
import java.util.List;


import trainScheduler.stations.Station;

// this class delegates the single calls to the AtomicTrackRegister and RouteRegister respectively
// Singleton
public class TrackRegister{
	// singleton instance
	private static TrackRegister instance;
	
	// c'tor
	private TrackRegister(){
	}
	// singleton getInstance
	public static TrackRegister getInstance(){
		if (instance == null){
			instance = new TrackRegister();
		}
		
		return instance;
	}
	// register an atomic track
	public void registerTrack(AtomicTrack at){
		AtomicTrackRegister.getInstance().registerElement(at);
	}
	// register a route
	public void registerTrack(Route r){
		RouteRegister.getInstance().registerElement(r);
	}
	
	// create an atomic track (delegate)
	public Track createAtomicTrack(String identifier, Station stationA, Station stationB, int length){
		return AtomicTrackRegister.getInstance().createAtomicTrack(identifier, stationA, stationB, length);
	}
	
	// create an atomic track (delegate)
	public Track createAtomicTrack(String identifier, String stationAid, String stationBid, int length){
		return AtomicTrackRegister.getInstance().createAtomicTrack(identifier, stationAid, stationBid, length);
	}
	
	// create a composite track (delegate)
	public Track createCompositeTrack(String identifier, Station stationA, Station stationB, List<String> trackparts){
		return RouteRegister.getInstance().createRoute(identifier, stationA, stationB, trackparts);
	}
	
	// return all the atomic tracks (delegate)
	public ArrayList<Track> getAllAtomicTracks(){
		return new ArrayList<Track>(AtomicTrackRegister.getInstance().getAllElements());
	}
	
	// return all the routes (delegate)
	public ArrayList<Route> getAllRoutes(){
		return RouteRegister.getInstance().getAllElements();
	}
	
	// return all atomicTracks and routes as a list
	public ArrayList<Track> getAllElements(){
		ArrayList<Track> allTracks = new ArrayList<Track>(this.getAllAtomicTracks());
		allTracks.addAll(new ArrayList<Track>(this.getAllRoutes()));
		return allTracks;
	}
	
	// search for an atomicTrack or a route for this identifier (route first)
	public Track getElementByIdentifier(String identifier){
		Track t = RouteRegister.getInstance().getElementByIdentifier(identifier);
		if (t == null){
			t = AtomicTrackRegister.getInstance().getElementByIdentifier(identifier);
		}
		return t;
	}
	
	
}
