/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler.connections;

import trainScheduler.templates.AbstractRegisterable;
import trainScheduler.templates.Iterator;
import trainScheduler.trains.Train;

public class Schedule extends AbstractRegisterable{
	
	private Train train; // what train should run this schedule
	private Iterator<Connection> firstConnection;
	
	// c'tor
	public Schedule(String identifier, Train train, Iterator<Connection> firstConn){
		super(identifier);
		this.train = train;
		this.firstConnection = firstConn;
	}
	// c'tor
	public Schedule(String identifier, Train train, Connection firstConn){
		super(identifier);
		this.train = train;
		this.firstConnection = new Iterator<Connection>(firstConn);
	}
	
	public Iterator<Connection> getIterator(){
		return this.firstConnection;
	}
	
	// append a new connection
	public void appendToConnection(Iterator<Connection> next){
		Iterator<Connection> conn = this.getLastConnection();
		conn.setNext(next);
	}
	
	// append a new connection
	public void appendToConnection(Connection next){
		Iterator<Connection> conn = this.getLastConnection();
		conn.setNext(next);
	}
	
	// for appending, we need the last connection
	private Iterator<Connection> getLastConnection(){
		Iterator<Connection> conn = firstConnection;
		while (conn.hasNext()){
			conn = conn.next();
		}
		return conn;
	}
	
	public Train getTrain(){
		return this.train;
	}
}
