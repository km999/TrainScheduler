/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler.stations;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class StationRegisterTest {
	
	@Before
	public void setupTest(){
		StationRegister.getInstance().clearRegister();
	}
	
	@Test
	public void testSingletonInstantiates() {
		StationRegister sr = StationRegister.getInstance();
		assertNotNull(sr);
	}
	
	@Test
	public void testSingletonInstantiatesOnlyOnce(){
		StationRegister sr1 = StationRegister.getInstance();
		StationRegister sr2 = StationRegister.getInstance();
		assertNotNull(sr1);
		assertNotNull(sr2);
		assertSame(sr1, sr2);
	}
	
	@Test
	public void testNoStationsAtStart(){
		StationRegister sr = StationRegister.getInstance();
		assertEquals(sr.getElementCount(), 0);
	}
	
	@Test
	public void testAddStation(){
		String sname = "station";
		Station s = new Station(sname);
		StationRegister sr = StationRegister.getInstance();
		sr.registerElement(s);
		assertEquals(sr.getElementCount(), 1);
	}
	
	@Test
	public void testCreateStation(){
		String sname = "station";
		StationRegister sr = StationRegister.getInstance();
		sr.registerElement(new Station(sname));
		assertEquals(sr.getElementCount(), 1);
	}
	
	@Test
	public void testClearRegister(){
		String sname = "station";
		StationRegister sr = StationRegister.getInstance();
		sr.registerElement(new Station(sname));
		assertEquals(sr.getElementCount(), 1);
		sr.clearRegister();
		assertEquals(sr.getElementCount(), 0);
	}
	
	
	@Test
	public void testGetStationAfterAdd(){
		String sname = "station";
		Station sAdd = new Station(sname);
		StationRegister sr = StationRegister.getInstance();
		sr.registerElement(sAdd);
		assertEquals(sr.getElementCount(), 1);
		
		Station sGet = sr.getElementByIdentifier(sname);
		assertSame(sAdd, sGet);
		assertEquals(sr.getElementCount(), 1);
	}
	
	@Test
	public void testGetStationAfterCreate(){
		String sname = "station";
		StationRegister sr = StationRegister.getInstance();
		sr.registerElement(new Station(sname));
		assertEquals(sr.getElementCount(), 1);
		
		Station sGet = sr.getElementByIdentifier(sname);
		assertEquals(sGet.getIdentifier(), sname);
		assertEquals(sr.getElementCount(), 1);
	}
	
	@Test
	public void testRemoveStationsByName(){
		String sname = "station";
		StationRegister sr = StationRegister.getInstance();
		sr.registerElement(new Station(sname));
		assertEquals(sr.getElementCount(), 1);
		
		Station sRemoved = sr.removeElementByIdentifier(sname);
		assertEquals(sr.getElementCount(), 0);
		assertNotNull(sRemoved);
	}
	
	@Test
	public void testRemoveNonExistingStation(){
		String sname = "station";
		StationRegister sr = StationRegister.getInstance();
		sr.registerElement(new Station(sname));
		assertEquals(sr.getElementCount(), 1);
		
		Station sRemoved = sr.removeElementByIdentifier("some_other_name");
		assertEquals(sr.getElementCount(), 1);
		assertNull(sRemoved);
	}
	
	@Test
	public void testGetAllStations(){
		String sname1 = "station1";
		String sname2 = "station2";
		String sname3 = "station3";
		
		StationRegister sr = StationRegister.getInstance();
		sr.registerElement(new Station(sname1));
		sr.registerElement(new Station(sname2));
		sr.registerElement(new Station(sname3));
		
		assertEquals(sr.getElementCount(), 3);
		
		ArrayList<Station> stations = sr.getAllElements();
		assertEquals(stations.size(), 3);
		ArrayList<String> names = new ArrayList<String>();
		names.add(sname1);
		names.add(sname2);
		names.add(sname3);
		for (Station s : stations){
			assertTrue(names.contains(s.getIdentifier()));
		}
	}
	
	
	
	
	
	
}
