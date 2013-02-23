package trainScheduler.tracks;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import trainScheduler.stations.Station;

public class AtomicTrackTest {

	@Test
	public void testProperties() {
		Station s1 = new Station("st_1");
		Station s2 = new Station("st_2");
		
		AtomicTrack at = new AtomicTrack("track_1", s1, s2, 50);
		assertEquals(at.getLength(), 50);
		assertEquals(at.getIdentifier(), "track_1");
		Station[] stations = at.getStations();
		assertTrue(Arrays.asList(stations).contains(s1)); 
		assertTrue(Arrays.asList(stations).contains(s2)); 
	}

}
