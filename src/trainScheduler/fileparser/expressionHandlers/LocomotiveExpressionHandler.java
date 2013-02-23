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

//input is in the form: identifier,power,maxSpeed
//register the locomotive
public class LocomotiveExpressionHandler implements IExpressionHandler {
	private String symbol = "Locomotive";
	
	@Override
	public void handleInput(String[] inputParts) {
		String id = inputParts[0].trim();
		int power = Integer.parseInt(inputParts[1].trim());
		int maxSpeed = Integer.parseInt(inputParts[2].trim());
		
		TrainRegister.getInstance().createLocomotive(id, power, maxSpeed);
	}

	@Override
	public String getSymbol() {
		return this.symbol;
	}

}
