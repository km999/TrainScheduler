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
