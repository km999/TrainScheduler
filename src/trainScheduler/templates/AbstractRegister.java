package trainScheduler.templates;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is an abstract register class. It's purpose is to provide the general implementations for the
 * individual registers by providing a generic Register to extend.
 * 
 * Funcitonality:
 * Have a HashMap which stores elements based on it's identifier.
 * Offer Functions to register, remove, return elements
 * also: getAllElements and clearRegister
 * 
 * NOTE: we can only accept types that extend AbstractRegisterable (e.g. that have the getIdentifierMethod, etc.)
 */

public abstract class AbstractRegister<T extends AbstractRegisterable> {
	
	private HashMap<String, T> register; // this is the register
		
	public AbstractRegister(){
		this.register = new HashMap<String, T>();
	}
	
	public T registerElement(T t){
		if (t == null){return null;}  // safety, make sure we don't register null elements
		this.register.put(t.getIdentifier(), t);
		return t;
	}
	
	
	public T removeElementByIdentifier(String identifier){
		return this.register.remove(identifier);
	}
	
	public T getElementByIdentifier(String identifier){
		return this.register.get(identifier);
	}
	
	public ArrayList<T> getAllElements(){
		return new ArrayList<T>(this.register.values());
	}
	
	public int getElementCount(){
		return this.register.size();
	}
	
	public void clearRegister(){
		this.register = new HashMap<String, T>();
	}
}
