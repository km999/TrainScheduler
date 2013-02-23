/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler.queries;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import trainScheduler.connections.ConnectionRegister;
import trainScheduler.connections.ScheduleRegister;
import trainScheduler.stations.Station;
import trainScheduler.stations.StationRegister;
import trainScheduler.tracks.TrackRegister;
import trainScheduler.trains.Locomotive;
import trainScheduler.trains.Train;
import trainScheduler.trains.TrainRegister;

public class QueryMinTrainSwitchesTest {

	public static QueryMinTrainSwitches qmts;
	public static StationRegister sr = StationRegister.getInstance();
	public static TrackRegister tr = TrackRegister.getInstance();
	public static ConnectionRegister cr = ConnectionRegister.getInstance();
	public static ScheduleRegister schr = ScheduleRegister.getInstance();
	
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
		tr.createAtomicTrack("gh", "G", "H", 10);
		
		cr.createConnection("ab", "A", "B", "ab");
		cr.createConnection("ad", "A", "D", "ad");
		cr.createConnection("fa", "F", "A", "af");
		cr.createConnection("be", "B", "E", "be");
		cr.createConnection("de", "D", "E", "de");
		cr.createConnection("gf", "G", "F", "fg");
		cr.createConnection("ec", "E", "C", "ec");
		cr.createConnection("cg", "C", "G", "cg");
		cr.createConnection("gh", "G", "H", "gh");
				
		Train t = TrainRegister.getInstance().registerElement(new Locomotive("loc", 2, 100));
		
		ArrayList<String> s1_conns = new ArrayList<String>();
		s1_conns.add("ab");
		s1_conns.add("be");
		s1_conns.add("ec");
		schr.createSchedule("s1", t, s1_conns);
		
		ArrayList<String> s2_conns = new ArrayList<String>();
		s2_conns.add("ad");
		s2_conns.add("de");
		schr.createSchedule("s2", t, s2_conns);
		
		ArrayList<String> s3_conns = new ArrayList<String>();
		s3_conns.add("cg");
		s3_conns.add("gf");
		s3_conns.add("fa");
		schr.createSchedule("s3", t, s3_conns);
		
		schr.createSchedule("s4", t, "gh");
		
		qmts = new QueryMinTrainSwitches(sr.getAllElements(), schr.getAllElements());
	}

	@Test
	public void testEdges() throws Exception{
		assertEquals(qmts.execute(sr.getElementByIdentifier("D"), sr.getElementByIdentifier("F")), 3.0, 0.001);
	}
	
	@Test
	public void testEdges2() throws Exception{
		assertEquals(qmts.execute(sr.getElementByIdentifier("F"), sr.getElementByIdentifier("C")), 2.0, 0.001);
	}
	
	@Test
	public void testEdges3() throws Exception{
		assertEquals(qmts.execute(sr.getElementByIdentifier("A"), sr.getElementByIdentifier("A")), 0.0, 0.001);
	}
	
	@Test
	public void testEdges4() throws Exception{
		assertEquals(qmts.execute(sr.getElementByIdentifier("F"), sr.getElementByIdentifier("H")), 4.0, 0.001);
	}
	
	@Test
	public void testError() throws Exception{
		assertNull(qmts.execute(sr.getElementByIdentifier("H"), sr.getElementByIdentifier("G")));// 4.0, 0.001);
	}
	
}
