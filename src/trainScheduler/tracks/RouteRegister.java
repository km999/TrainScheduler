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
import trainScheduler.templates.AbstractRegister;

// Factory for routes
// Singleton
public class RouteRegister extends AbstractRegister<Route>{
	private static RouteRegister instance; // singleton instance
	
	// c'tor
	private RouteRegister(){
	}
	// singleton getInstance
	public static RouteRegister getInstance(){
		if (instance == null){
			instance = new RouteRegister();
		}
		
		return instance;
	}
	
	// create a route based on an array of track parts
	public Route createRoute(String identifier, Station stationA, Station stationB, ArrayList<Track> trackparts){
		Route r = new Route(identifier, stationA, stationB, trackparts);
		this.registerElement(r);
		return r;
	}
	
	// create a route based on a list of registered trackpart names
	public Route createRoute(String identifier, Station stationA, Station stationB, List<String> trackparts){
		TrackRegister tr = TrackRegister.getInstance();
		Route r = new Route(identifier, stationA, stationB); // create a route without any trackparts
		for(String s : trackparts){ 						// iterate through list and append all 
			r.addTrackPart(tr.getElementByIdentifier(s));	// (null-check happens in r.addTrackPart())
		}
		this.registerElement(r);
		return r;
	}
}
