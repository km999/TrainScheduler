/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler.tracks;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import trainScheduler.stations.Station;


public class RouteTest {
	
	@Test
	public void testRouteWithOneAtomicTrack(){
		Station s1 = new Station("st_1");
		Station s2 = new Station("st_2");
		AtomicTrack at = new AtomicTrack("track_1", s1, s2, 50);
		
		Route r = new Route("route_1", s1, s2, at);
		
		assertEquals(r.getIdentifier(), "route_1");
		Station[] stations = at.getStations();
		assertTrue(Arrays.asList(stations).contains(s1)); 
		assertTrue(Arrays.asList(stations).contains(s2)); 
		assertEquals(r.getLength(), 50);
	}
	
	@Test
	public void testRouteWithTwoAtomicTracks(){
		Station s1 = new Station("st_1");
		Station s2 = new Station("st_2");
		Station s3 = new Station("st_3");
		AtomicTrack at = new AtomicTrack("track_1", s1, s2, 55);
		AtomicTrack at2 = new AtomicTrack("track_2", s2, s3, 45);
		
		Route r = new Route("route_1", s1, s3, at);
		r.addTrackPart(at2);
		
		assertEquals(r.getIdentifier(), "route_1");
		Station[] stations = r.getStations();
		assertTrue(Arrays.asList(stations).contains(s1)); 
		assertTrue(Arrays.asList(stations).contains(s3)); 
		assertEquals(r.getLength(), 100);
	}
	
	@Test
	public void testRouteWithComposites(){
		Station s1 = new Station("st_1");
		Station s2 = new Station("st_2");
		Station s3 = new Station("st_3");
		Station s4 = new Station("st_4");
		Station s5 = new Station("st_5");
		AtomicTrack at = new AtomicTrack("track_1", s1, s2, 55);
		AtomicTrack at2 = new AtomicTrack("track_2", s2, s3, 45);
		AtomicTrack at3 = new AtomicTrack("track_3", s3, s4, 70);
		AtomicTrack at4 = new AtomicTrack("track_4", s4, s5, 80);
		
		Route r = new Route("route_1", s1, s3, at);
		r.addTrackPart(at2);
		Route r2 = new Route("route_2", s3, s5, at3);
		r2.addTrackPart(at4);
		
		Route r_all = new Route("route_all", s1, s5, r);
		r_all.addTrackPart(r2);
		
		// verify first
		assertEquals(r.getIdentifier(), "route_1");
		Station[] stations = r.getStations();
		assertTrue(Arrays.asList(stations).contains(s1)); 
		assertTrue(Arrays.asList(stations).contains(s3)); 
		assertEquals(r.getLength(), 100);
		
		// verify second
		assertEquals(r2.getIdentifier(), "route_2");
		Station[] stations2 = r2.getStations();
		assertTrue(Arrays.asList(stations2).contains(s3)); 
		assertTrue(Arrays.asList(stations2).contains(s5)); 
		assertEquals(r2.getLength(), 150);
		
		// verify composition
		assertEquals(r_all.getIdentifier(), "route_all");
		Station[] stations_all = r_all.getStations();
		assertTrue(Arrays.asList(stations_all).contains(s1)); 
		assertTrue(Arrays.asList(stations_all).contains(s5)); 
		assertEquals(r_all.getLength(), 250);
	}
}
