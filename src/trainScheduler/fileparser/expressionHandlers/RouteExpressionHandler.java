/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler.fileparser.expressionHandlers;

import java.util.List;
import java.util.Arrays;

import trainScheduler.fileparser.IExpressionHandler;
import trainScheduler.stations.Station;
import trainScheduler.stations.StationRegister;
import trainScheduler.tracks.TrackRegister;

//input is in the form: identifier,stationAidentifier,stationBidentifier,(trackIdentifier)*
//register the route
public class RouteExpressionHandler implements IExpressionHandler {
	private String symbol = "Route";
	
	@Override
	public void handleInput(String[] inputParts) {
		
		String identifier = inputParts[0].trim();
		Station stationA = StationRegister.getInstance().getElementByIdentifier(inputParts[1].trim());
		Station stationB = StationRegister.getInstance().getElementByIdentifier(inputParts[2].trim());
		List<String> trackparts = Arrays.asList(Arrays.copyOfRange(inputParts, 3, inputParts.length));
		
		if(stationA == null || stationB == null || trackparts.size() <= 0){
			// something's wrong, but we don't really care
			return;
		}
		
		TrackRegister.getInstance().createCompositeTrack(identifier, stationA, stationB, trackparts);
	}

	@Override
	public String getSymbol() {
		return this.symbol;
	}

}
