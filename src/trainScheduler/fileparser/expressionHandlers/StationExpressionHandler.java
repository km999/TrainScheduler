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

//input is in the form: identifier
//register the station
public class StationExpressionHandler implements IExpressionHandler {
	private String symbol = "Station";
	
	public void handleInput(String[] inputParts){
		String input = inputParts[0];
		if (input == null || input.equals("")){
			return;
		}
		StationRegister.getInstance().registerElement(new Station(input.trim()));
	}
	
	public String getSymbol(){
		return this.symbol;
	}
}
