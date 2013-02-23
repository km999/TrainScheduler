package trainScheduler.connections;

import trainScheduler.stations.Station;
import trainScheduler.templates.AbstractRegisterable;
import trainScheduler.tracks.Track;
import trainScheduler.tracks.TrackRegister;

public class Connection  extends AbstractRegisterable {
	private Station from;
	private Station to;
	private Track track;
	
	// c'tor
	public Connection(String id, Station from, Station to, String track_identifier){
		super(id);
		this.from = from;
		this.to = to;
		this.track = TrackRegister.getInstance().getElementByIdentifier(track_identifier);
	}
	
	// c'tor
	public Connection(String id, Station from, Station to, Track track){
		super(id);
		this.from = from;
		this.to = to;
		this.track = track;
	}
	
	public Station getFrom(){
		return this.from;
	}
	
	public Station getTo(){
		return this.to;
	}
	
	public Track getTrack(){
		return this.track;
	}
	
	public int getLength(){
		return this.track.getLength();
	}
	
}


