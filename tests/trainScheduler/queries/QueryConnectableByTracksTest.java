package trainScheduler.queries;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import trainScheduler.queries.QueryMinOfTrackWeights;
import trainScheduler.stations.Station;
import trainScheduler.stations.StationRegister;
import trainScheduler.tracks.TrackRegister;

public class QueryConnectableByTracksTest {

	public static QueryMinOfTrackWeights qc;
	public static StationRegister sr = StationRegister.getInstance();
	public static TrackRegister tr = TrackRegister.getInstance();
	
	@BeforeClass
	public static void init() {
		sr.registerElement(new Station("A"));
		sr.registerElement(new Station("B"));
		sr.registerElement(new Station("C"));
		sr.registerElement(new Station("D"));
		sr.registerElement(new Station("E"));
		sr.registerElement(new Station("F"));
		sr.registerElement(new Station("G"));
		sr.registerElement(new Station("H"));
		sr.registerElement(new Station("I"));
		
		
		tr.createAtomicTrack("ab", "A", "B", 10);
		tr.createAtomicTrack("ad", "A", "D", 10);
		tr.createAtomicTrack("af", "A", "F", 5);
		tr.createAtomicTrack("be", "B", "E", 10);
		tr.createAtomicTrack("de", "D", "E", 10);
		tr.createAtomicTrack("fg", "F", "G", 10);
		tr.createAtomicTrack("ec", "E", "C", 10);
		tr.createAtomicTrack("cg", "C", "G", 10);
		
		qc = new QueryMinOfTrackWeights(sr.getAllElements(),
				tr.getAllAtomicTracks());
	}
	
	@Test
	public void testExistingPath() throws Exception{	
		assertNotNull(qc.execute(sr.getElementByIdentifier("B"), sr.getElementByIdentifier("G")));
	}
	
	@Test
	public void testExistingPathRetour() throws Exception{	
		assertNotNull(qc.execute(sr.getElementByIdentifier("G"), sr.getElementByIdentifier("E")));
	}
	
	@Test
	public void testNotExistingPath() throws Exception{
		assertNull(qc.execute(sr.getElementByIdentifier("B"), sr.getElementByIdentifier("I")));
	}
	
	@Test (expected=Exception.class)
	public void testNotExistingStart() throws Exception{
		assertNull(qc.execute(sr.getElementByIdentifier("XYZ"), sr.getElementByIdentifier("B")));
	}
	
	@Test (expected=Exception.class)
	public void testNotExistingEnd() throws Exception{
		assertNull(qc.execute(sr.getElementByIdentifier("B"), sr.getElementByIdentifier("XYZ")));
	}

}
