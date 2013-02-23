/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler.connections;

import trainScheduler.stations.Station;
import trainScheduler.stations.StationRegister;
import trainScheduler.templates.AbstractRegister;

// factory and singleton for registering connections
public class ConnectionRegister extends AbstractRegister<Connection>{
	private static ConnectionRegister instance; // singleton instance
	private ConnectionRegister(){} // c'tor
	// singleton getInstance
	public static ConnectionRegister getInstance(){
		if (instance == null){
			instance = new ConnectionRegister();
		}
		return instance;
	}
	
	// create a connection with stations
	public Connection createConnection(String identifier, Station from, Station to, String track_identifier){
		return this.registerElement(new Connection(identifier, from, to, track_identifier));
	}
	
	// create a connection with Station identifiers
	public Connection createConnection(String identifier, String from_identifier, String to_identifier, String track_identifier){
		Station from = StationRegister.getInstance().getElementByIdentifier(from_identifier);
		Station to = StationRegister.getInstance().getElementByIdentifier(to_identifier);
		
		if (from == null || to == null){ return null; }
		
		return this.registerElement(new Connection(identifier, from, to, track_identifier));
	}
}
