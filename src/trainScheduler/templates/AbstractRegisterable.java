/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler.templates;

// parent class for all classes that want to be registered
public class AbstractRegisterable {
	private String identifier; // id for the register-HashMap
	
	// c'tor
	public AbstractRegisterable(String identifier){
		this.identifier = identifier;
	}
	
	public String getIdentifier(){
		return this.identifier;
	}
}
