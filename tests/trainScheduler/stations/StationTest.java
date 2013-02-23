/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler.stations;

import static org.junit.Assert.*;

import org.junit.Test;

public class StationTest {

	@Test
	public void testConstructor() {
		String sname = "test";
		Station s = new Station(sname);
		assertNotNull(s.getIdentifier());
		assertEquals(s.getIdentifier(), sname);
	}

}
