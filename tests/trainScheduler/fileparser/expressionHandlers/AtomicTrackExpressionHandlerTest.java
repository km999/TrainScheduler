/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler.fileparser.expressionHandlers;

import static org.junit.Assert.*;


import org.junit.Test;

import trainScheduler.stations.Station;
import trainScheduler.stations.StationRegister;
import trainScheduler.tracks.AtomicTrackRegister;

public class AtomicTrackExpressionHandlerTest {
	private AtomicTrackExpressionHandler ateh = new AtomicTrackExpressionHandler();
	private AtomicTrackRegister atr = AtomicTrackRegister.getInstance();
	
	@Test
	public void testHandleInput() throws Exception {
		StationRegister.getInstance().registerElement(new Station("station1"));
		StationRegister.getInstance().registerElement(new Station("station2"));
		
		int elementCount = atr.getElementCount();
		ateh.handleInput(new String[]{"atrack1","station1","station2","2"});
		assertEquals(atr.getElementCount(),elementCount+1);
		assertNotNull(atr.getElementByIdentifier("atrack1"));
	}

	@Test
	public void testHandleInputWithNoRegisteredStations() throws Exception {
		StationRegister.getInstance().registerElement(new Station("station3"));
		
		int elementCount = atr.getElementCount();
		ateh.handleInput(new String[]{"atrack2","station3","station4","2"});
		assertEquals(atr.getElementCount(),elementCount);
		assertNull(atr.getElementByIdentifier("atrack2"));
	}
	
	
	
	@Test
	public void testGetSymbol() {
		assertEquals(ateh.getSymbol(), "AtomicTrack");
	}

}
