/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler.fileparser.expressionHandlers;

import trainScheduler.fileparser.IExpressionHandler;
import trainScheduler.trains.TrainRegister;

// input is in the form: identifier,seats,maxSpeed
// register the wagon
public class WagonExpressionHandler implements IExpressionHandler {
	private String symbol = "Wagon";
	
	@Override
	public void handleInput(String[] inputParts) {
		String id = inputParts[0].trim();
		int seats = Integer.parseInt(inputParts[1].trim());
		int maxSpeed = Integer.parseInt(inputParts[2].trim());
		
		TrainRegister.getInstance().createWagon(id, seats, maxSpeed);
	}

	@Override
	public String getSymbol() {
		return this.symbol;
	}

}
