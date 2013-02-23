package trainScheduler.trains;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;


public class WagonTest {

	@Test
	public void testConstructorSetsCorrectProperties() {
		String id = "wag_1";
		int seats = 200;
		int mxSpd = 100; 
		
		Wagon w = new Wagon(id, seats, mxSpd);
		assertNotNull(w);
		
		assertEquals(w.getIdentifier(), id);
		assertEquals(w.getMaxSpeed(), mxSpd);
	}
}
