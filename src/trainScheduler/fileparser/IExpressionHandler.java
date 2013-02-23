/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler.fileparser;

// every expression handler needs to be able to handle input and to give information about its symbol (operator/flag/identifier)
public interface IExpressionHandler {
	final String symbol = "";
		
	public String getSymbol();

	public void handleInput(String[] tailParts) throws Exception;
}
