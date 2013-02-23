/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler.queries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import trainScheduler.stations.Station;

// this is an implementation of the dijkstra algorithm for finding the shortest path between two vertices
public class DijkstraAlgorithm {

	private ArrayList<Station> stations; // all the stations in the graph
	private HashMap<String, HashMap<String, Double>> adjacencyMapping; // the entire adjacencyMapping, defining the distances of the connections
	
	// c'tor
	public DijkstraAlgorithm(ArrayList<Station> stations, HashMap<String, HashMap<String, Double>> adjacencyMapping){
		this.stations = stations;
		this.adjacencyMapping = adjacencyMapping;
	}
	
	// we store the distance to the start node in these nodes, and the predecessor
	private HashMap<String, DistancePredecessor> nodeConnections; 
	
	// this class stores a nodes' min distance from the start and the predecessor on the shortest path from start
	private class DistancePredecessor{
		private Double length;
		private String predecessor;
		// c'tor
		protected DistancePredecessor(Double d, String pred){
			this.length = d;
			this.predecessor = pred;
		}
		
		protected Double getDistance(){
			return this.length;
		}
		
		protected void setDistance(Double alternative){
			this.length = alternative;
		}
		
		@SuppressWarnings("unused") // we don't use it, but this makes it possible to 'backtrace' the cheapest path
		protected String getPredecessor(){
			return this.predecessor;
		}
		
		protected void setPredecessor(String pred){
			this.predecessor = pred;
		}
	}
	
	// using Dijkstra to do check whether we hit the node
	// variable names are base on the algorithm described on
	// http://de.wikipedia.org/wiki/Dijkstra-Algorithmus
	public Double executeQuery(Station start, Station end) throws Exception{
		if (start == null || end == null || !this.stations.contains(start) || !this.stations.contains(end)){ 
			throw new Exception("One (or both of) start and endnode are either null or not in the graph");
		}
		
		// make a copy of all the stations in the graph, so we can run on this instance more than once
		// this list contains all the unvisited nodes
		ArrayList<Station> graphStations = new ArrayList<Station>(this.stations); 
		
		// initialize nodeConnections (all are predecessors are null, distances are null, except start with a distance of 0)
		this.nodeConnections = new HashMap<String, DistancePredecessor>();
		for (Station s: graphStations){
			this.nodeConnections.put(s.getIdentifier(), new DistancePredecessor(null,null));
		}
		this.nodeConnections.put(start.getIdentifier(), new DistancePredecessor(0.0,null));
		
		// as long as there are stations we haven't visited
		while (graphStations.size() > 0){
			// get an unvisited node with smallest distance to start
			Station u = findStationWithLeastDistance(graphStations);
			if (u == null){ 
				/** if we are here, this means we reached all the nodes that are reachable from start
				  * break the loop
				  * 
				  * didn't find a non-visited station with non-null distance
				  * and all the nodes that we visited from the start point are reached.
				  * we cannot do any more, our target is in some other subgraph or a node that does not exist? 
				  */
				break;
			}
			graphStations.remove(u); // remove because we now visited this node
			
			HashMap<String, Double> neighborMapping = adjacencyMapping.get(u.getIdentifier()); // get the neighbors of the current node (outgoing edges only)
			
			if (neighborMapping == null){ break; } // this key is not initialized, this means that there are no neighbors; skip this
			Set<String> neighbours = neighborMapping.keySet(); // get all neighbors
			
			for (String v : neighbours){ // for each neighbor check if the station is in the list of unvisited stations and update the distance
				if (checkIfStationNameInStationList(v, graphStations)){
					this.updateDistance(u.getIdentifier(), v);
				}
			}
		}
		// the result is the distance of the end node. (null if we didn't reach it)
		Double result = this.nodeConnections.get(end.getIdentifier()).getDistance(); 
		return result;
	}
	
	// update the distance between two nodes (current node = u, neighbor = v)
	private void updateDistance(String u, String v){
		// current node (= u) + distance from u to neighbor v
		Double alternative = this.nodeConnections.get(u).getDistance() + this.adjacencyMapping.get(u).get(v);
		// current distance of v
		Double prevDistance = this.nodeConnections.get(v).getDistance();
		if (prevDistance == null || alternative < prevDistance){
			this.nodeConnections.get(v).setDistance(alternative);
			this.nodeConnections.get(v).setPredecessor(u);
		}
	}
	
	// checks if a node (defined by name) is in a list
	// this function is necessary because there's no elegant solution for doing this with our data types
	private boolean checkIfStationNameInStationList(String name, ArrayList<Station> allStations){
		for (Station s:allStations){
			if (s.getIdentifier().equals(name)){
				return true;
			}
		}
		return false;
	}
	
	// find the node with the smallest distance to start (the parameter is normally the list of unvisited nodes)
	private Station findStationWithLeastDistance(ArrayList<Station> allStations){
 		Station u = null;
 		Double minDist = null;
		for (Station s : allStations){ // for all stations
			Double sDist = this.nodeConnections.get(s.getIdentifier()).getDistance();
			if (sDist == null){continue;} // if the distance is null (node currently not reachable): SKIP
			
			// if the distance is smaller than what we previously had (or it's the first distance): CHOOSE THIS NODE
			if (minDist == null || sDist < minDist){ 
				u = s;
				minDist = sDist;
			}
		}
		
		return u; // if we return null it means that there was no node with a non-null distance to start in the list
	}
	

}
