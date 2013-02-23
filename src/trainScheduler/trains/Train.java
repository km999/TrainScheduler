package trainScheduler.trains;

import trainScheduler.templates.AbstractRegisterable;


/**
 * abstract supertype of AtomicTrains and CompositeTrains. Supertype of the the composite pattern
 * templates that every train has to have a getMaxSpeed() property
 */
public abstract class Train  extends AbstractRegisterable{
	//c'tor
	public Train(String id){
		super(id);
	}
	// method stub
	public abstract int getMaxSpeed();
}
