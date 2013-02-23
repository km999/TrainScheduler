/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler.connections;

import java.util.List;

import trainScheduler.templates.AbstractRegister;
import trainScheduler.templates.Iterator;
import trainScheduler.trains.Train;

// factory and singleton for registering schedules
public class ScheduleRegister extends AbstractRegister<Schedule> {
	private static ScheduleRegister instance; // singleton instance
	
	private ScheduleRegister(){} // c'tor
	// singleton getInstance
	public static ScheduleRegister getInstance(){
		if (instance == null){
			instance = new ScheduleRegister();
		}
		return instance;
	}
	
	// create a schedule with only one connection
	public Schedule createSchedule(String identifier, Train train, String connection){
		ConnectionRegister cr = ConnectionRegister.getInstance();
		Connection conn = cr.getElementByIdentifier(connection);
		return this.registerElement(new Schedule(identifier, train, conn));
	}
	
	// create a schedule with a list of connection identifiers
	public Schedule createSchedule(String identifier, Train train, List<String> connection_ids){
		ConnectionRegister cr = ConnectionRegister.getInstance();
		
		Connection conn = cr.getElementByIdentifier(connection_ids.get(0));
		if (conn == null){return null;}
		Iterator<Connection> connectionIterator = new Iterator<Connection>(conn);
		Schedule s = this.registerElement(new Schedule(identifier, train, connectionIterator));
		for (int i = 1; i < connection_ids.size(); i++){
			Connection c = cr.getElementByIdentifier(connection_ids.get(i));
			if (c != null){
				s.appendToConnection(c);
			}
		}
		return s;
	}
	
}
