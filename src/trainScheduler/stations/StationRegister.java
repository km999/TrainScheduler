/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler.stations;

import trainScheduler.templates.AbstractRegister;

// Factory and singleton for station registry
public class StationRegister extends AbstractRegister<Station>{
	private static StationRegister instance; // singleton instance
	// c'tor
	private StationRegister(){
	}
	// singleton getInstance
	public static StationRegister getInstance(){
		if (StationRegister.instance == null){
			StationRegister.instance = new StationRegister();
		}
		
		return StationRegister.instance;
	}
	
	public Station createStation(String identifier){
		return this.registerElement(new Station(identifier));
	}

}
