package trainScheduler.tracks;

import trainScheduler.stations.Station;
import trainScheduler.stations.StationRegister;
import trainScheduler.templates.AbstractRegister;

// Factory for atomic register class 
// Singleton
public class AtomicTrackRegister extends AbstractRegister<AtomicTrack>{
	private static AtomicTrackRegister instance; // singleton instance
	
	// c'tor
	private AtomicTrackRegister(){
	}
	// singleton getInstance
	public static AtomicTrackRegister getInstance(){
		if (instance == null){
			instance = new AtomicTrackRegister();
		}
		return instance;
	}
	
	// create an atomic track
	public AtomicTrack createAtomicTrack(String identifier, Station stationA, Station stationB, int length){
		AtomicTrack t = new AtomicTrack(identifier, stationA, stationB, length);
		this.registerElement(t);
		return t;
	}
	
	// create an atomic track
	public AtomicTrack createAtomicTrack(String identifier, String stationAid, String stationBid, int length){
		Station stationA = StationRegister.getInstance().getElementByIdentifier(stationAid);
		Station stationB = StationRegister.getInstance().getElementByIdentifier(stationBid);
		AtomicTrack t = new AtomicTrack(identifier, stationA, stationB, length);
		this.registerElement(t);
		return t;
	}
	
	

}
