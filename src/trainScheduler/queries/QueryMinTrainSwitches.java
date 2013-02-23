package trainScheduler.queries;

import java.util.ArrayList;
import java.util.List;

import trainScheduler.connections.Connection;
import trainScheduler.connections.Schedule;
import trainScheduler.stations.Station;
import trainScheduler.templates.Iterator;

public class QueryMinTrainSwitches extends AbstractQuery{
	// c'tor
	public QueryMinTrainSwitches(ArrayList<Station> stations, List<Schedule> schedules) {
		super(stations);
		this.weighted = false;
		this.edges = new ArrayList<FromToWeightTuple>();
		this.initEdges(schedules);
	}
	
	private void initEdges(List<Schedule> schedules){
		for (Schedule s: schedules){
			// for this algorithm to work properly we need to take a schedule and 
			// connect each station of the schedule with all the stations that will be visited later by this train
			Iterator<Connection> connItFrom = s.getIterator();
			while (connItFrom != null){
				Station from = connItFrom.getObject().getFrom();
				
				Iterator<Connection> connItTo = connItFrom;
				while (connItTo != null){
					Station to = connItTo.getObject().getTo();
					this.edges.add(new FromToWeightTuple(from,to,1.0));
					connItTo = connItTo.next();
				}
				connItFrom = connItFrom.next();
			}
		}
	}

}
