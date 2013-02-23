package trainScheduler.fileparser.expressionHandlers;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import trainScheduler.stations.StationRegister;

public class StationExpressionHandlerTest {

	public static StationExpressionHandler seh = new StationExpressionHandler();
	public static StationRegister sr = StationRegister.getInstance();
	
	@BeforeClass
	public static void init(){
		sr.clearRegister();
	}
	
	@Test
	public void testHandleInput() {
		int size = sr.getElementCount();
		seh.handleInput(new String[]{"station1"});
		assertEquals(sr.getElementCount(), size+1);
		assertNotNull(sr.getElementByIdentifier("station1"));
		seh.handleInput(new String[]{""});
		assertEquals(sr.getElementCount(), size+1);
		seh.handleInput(new String[2]);
		assertEquals(sr.getElementCount(), size+1);
	}

	@Test
	public void testGetSymbol() {
		assertEquals(seh.getSymbol(), "Station");
	}

}
