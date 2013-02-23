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
