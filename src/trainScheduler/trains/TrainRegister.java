package trainScheduler.trains;

import java.util.List;

import trainScheduler.templates.AbstractRegister;

public class TrainRegister extends AbstractRegister<Train>{
	// singleton instance storage
	private static TrainRegister instance;
	
	//c'tor
	private TrainRegister(){
	}
	
	// singleton return
	public static TrainRegister getInstance(){
		if (instance == null){
			instance = new TrainRegister();
		}
		return instance;
	}
	

	// creates a locomotive (and returns it)
	public Train createLocomotive(String id, int power, int maxSpeed){
		return this.registerElement(new Locomotive(id, power, maxSpeed));
	}
	
	// creates a wagon (and returns it)
	public Train createWagon(String id, int seats, int maxSpeed){
		return this.registerElement(new Wagon(id, seats, maxSpeed));
	}
	
	// creates a composite (and returns it)
	public CompositeTrain createCompositeTrainByIdentifier(String id){
		return (CompositeTrain) this.registerElement(new CompositeTrain(id));
	}
	
	
	// Adds a train to an existing train and removes the added part from the registry
	// @param identifier - the identifier of the existing composition
	// @param add - the train that should be added 
	public void addTrainToComposite(String identifier, Train add){
		CompositeTrain comp = (CompositeTrain) this.getElementByIdentifier(identifier);
		comp.addTrainPart(add);
		this.removeElementByIdentifier(add.getIdentifier());
	}
	
	
	//registers a composite train and adds all the identifiers in the list (and removes them from registry 
	public Train createCompositeTrainByIdentifier(String id, List<String> identifiers){
		CompositeTrain ct = this.createCompositeTrainByIdentifier(id);
		for (String s: identifiers){
			ct.addTrainPart(this.getElementByIdentifier(s));
			this.removeElementByIdentifier(s);
		}
		return ct;
	}
	

}
