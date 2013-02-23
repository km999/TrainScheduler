package trainScheduler.queries;

import java.util.ArrayList;
import java.util.HashMap;

import trainScheduler.stations.Station;

// provides some of the functionality for all queries
public abstract class AbstractQuery {

	ArrayList<Station> stations;
	ArrayList<FromToWeightTuple> edges;
	boolean weighted = true;
	
	// the entities of this class define directed weighted edges
	// these will then be converted into an adjacencyMapping (NOT AN ENTIRE MATRIX)
	protected class FromToWeightTuple{
		Station from;
		Station to;
		double weight;
		// c'tor
		public FromToWeightTuple(Station from, Station to, double weight){
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		public double getWeight(){
			return this.weight;
		}
		
		public Station getFrom(){
			return this.from;
		}
		
		public Station getTo(){
			return this.to;
		}
	}
	
	// c'tor
	public AbstractQuery(ArrayList<Station> stations){
		this.stations = stations;
	}
	
	public Double execute(Station from, Station to) throws Exception{
		DijkstraAlgorithm alg = new DijkstraAlgorithm(this.stations, this.generateAdjacencyMapping());
		Double result = alg.executeQuery(from, to);
		return result;
	}
	
	// generate a hashmap of hashmaps, so we can see for every station what it's distance to the next station is
	// (provided that there is an edge)
	private HashMap<String, HashMap<String, Double>> generateAdjacencyMapping(){
		HashMap<String, HashMap<String, Double>> adjacencyMatrix = new HashMap<String, HashMap<String, Double>>();
		
		for (FromToWeightTuple ftw: this.edges){ 				// for every entry in the directed edges
			String fromName = ftw.getFrom().getIdentifier();	// 
			String toName = ftw.getTo().getIdentifier();		// first get the details we are interested in
			Double length = 0.0;
			
			if (this.weighted){	// do we want it weighted? or should we make every edge-weight 1?
				length = ftw.getWeight();
			} else {
				length = 1.0;	
			} 
			
			// if there is no hashMap for a certain station, generate one and put this in
			if (adjacencyMatrix.get(fromName) == null){
				HashMap<String, Double> nodeDistanceMapping = new HashMap<String, Double>();
				nodeDistanceMapping.put(toName, length);
				adjacencyMatrix.put(fromName, nodeDistanceMapping);
				
				
			// there is already a HashMap, so we extend/update this mapping
			} else { 
				
				// if this is a new connection, create the entry
				if (adjacencyMatrix.get(fromName).get(toName) == null){
					adjacencyMatrix.get(fromName).put(toName, length);
					
				// in case there's already a connection between our two stations, we only take the one with less weight
				} else { 
					double existingLength = adjacencyMatrix.get(fromName).put(toName, length);
					if (existingLength > length){
						adjacencyMatrix.get(fromName).put(toName, length);
					}
				}
			}
		}
		return adjacencyMatrix;
	}
}
