/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler.fileparser.expressionHandlers;

import trainScheduler.fileparser.IExpressionHandler;
import trainScheduler.stations.Station;
import trainScheduler.stations.StationRegister;
import trainScheduler.tracks.TrackRegister;

//input is in the form: identifier,stationAidentifier,stationBidentifier,length
//register the track
public class AtomicTrackExpressionHandler implements IExpressionHandler{
	private final String symbol = "AtomicTrack";
	
	public void handleInput(String[] inputParts) throws Exception {
		
		String identifier = inputParts[0].trim();
		Station a = StationRegister.getInstance().getElementByIdentifier(inputParts[1].trim());
		Station b = StationRegister.getInstance().getElementByIdentifier(inputParts[2].trim());
		int length = Integer.parseInt(inputParts[3].trim());
		
		if(a == null || b == null || length <= 0){
			throw new Exception("Either one of the stations is not registered, or the length is <= 0");
		}
		
		
		TrackRegister.getInstance().createAtomicTrack(identifier, a, b, length);
	}

	@Override
	public String getSymbol() {
		return this.symbol;
	}

}
