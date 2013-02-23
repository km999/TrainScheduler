/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler.trains;

import static org.junit.Assert.*;

import org.junit.Test;

public class LocomotiveTest {

	@Test
	public void testConstructorSetsCorrectProperties() {
		String id = "loc_1";
		int pwr = 2;
		int mxSpd = 100; 
		
		Locomotive l = new Locomotive(id, pwr, mxSpd);
		assertNotNull(l);
		
		assertEquals(l.getIdentifier(), id);
		assertEquals(l.getMaxSpeed(), mxSpd);
	}
}
