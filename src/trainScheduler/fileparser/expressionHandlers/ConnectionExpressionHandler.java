/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler.fileparser.expressionHandlers;

import trainScheduler.connections.ConnectionRegister;
import trainScheduler.fileparser.IExpressionHandler;
import trainScheduler.stations.Station;
import trainScheduler.stations.StationRegister;

//input is in the form: identifier,stationFromIdentifier,stationToIdentifier,trackIdentifier
//register the connection
public class ConnectionExpressionHandler implements IExpressionHandler {

	private final String symbol = "Connection";
	
	public void handleInput(String[] inputParts) throws Exception {
		
		String identifier = inputParts[0];
		Station from = StationRegister.getInstance().getElementByIdentifier(inputParts[1].trim());
		Station to = StationRegister.getInstance().getElementByIdentifier(inputParts[2].trim());
		String track_identifier = inputParts[3].trim();
		
		if(from == null || to == null || track_identifier.equals("")){
			throw new Exception("Either one of the stations is null or the trackIdentifier is empty");
		}
		
		ConnectionRegister.getInstance().createConnection(identifier, from, to, track_identifier);
	}

	@Override
	public String getSymbol() {
		return this.symbol;
	}

}
