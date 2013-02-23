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
