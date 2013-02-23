/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler.templates;

// this class is a generic Iterator wrapper around objects 
public class Iterator<T> {

	private Iterator<T> next = null; // pointer to next
	private T object;				 // container for the object
	
	// c'tor
	public Iterator(T obj){
		this.object = obj;
	}
	// c'tor
	public Iterator(T obj, Iterator<T> next){
		this.object = obj;
		this.next = next;
	}
	// set this object
	public void setObject(T obj){
		this.object = obj;
	}
	
	// get this object
	public T getObject(){
		return this.object;
	}
	
	public void setNext(Iterator<T> next){
		this.next = next;
	}
	
	public Iterator<T> setNext(T next){
		this.next = new Iterator<T>(next);
		return this.next;
	}
	
	// is there a next iterator?
	public boolean hasNext(){
		return (this.next != null);
	}
	
	// get next iterator
	public Iterator<T> next(){
		return this.next;
	}
}
