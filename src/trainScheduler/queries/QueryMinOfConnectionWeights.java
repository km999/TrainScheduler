package trainScheduler.queries;

import java.util.ArrayList;
import java.util.List;

import trainScheduler.connections.Connection;
import trainScheduler.connections.Schedule;
import trainScheduler.stations.Station;
import trainScheduler.templates.Iterator;

// this is for when we want to get the minimum of connection weights (time for a connection)
// we don't care about number of train switches
public class QueryMinOfConnectionWeights extends AbstractQuery{

	// c'tor
	public QueryMinOfConnectionWeights(ArrayList<Station> stations, List<Schedule> schedules) {
		super(stations);
		this.weighted = true;
		this.edges = new ArrayList<FromToWeightTuple>();
		this.initEdges(schedules);
	}

	private void initEdges(List<Schedule> schedules){
		for (Schedule s: schedules){
			// for this algorithm to work properly we need to take a schedule and 
			// model the connections as edges
			// weight is the time needed for the connection
			Iterator<Connection> connIt = s.getIterator();
			while (connIt != null){
				Station from = connIt.getObject().getFrom();
				Station to = connIt.getObject().getTo();
				Double weight = (double) connIt.getObject().getLength()/(double) s.getTrain().getMaxSpeed();
				this.edges.add(new FromToWeightTuple(from,to,weight));
				connIt = connIt.next();
			}
		}
	}
}
