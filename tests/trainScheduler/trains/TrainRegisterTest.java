/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler.trains;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TrainRegisterTest {

	@Before
	public void setupTest(){
		TrainRegister.getInstance().clearRegister();
	}
	
	@Test
	public void testSingletonInstantiates() {
		TrainRegister tr = TrainRegister.getInstance();
		assertNotNull(tr);
	}
	
	@Test
	public void testSingletonInstantiatesOnlyOnce(){
		TrainRegister tr1 = TrainRegister.getInstance();
		TrainRegister tr2 = TrainRegister.getInstance();
		assertNotNull(tr1);
		assertNotNull(tr2);
		assertSame(tr1, tr2);
	}
	
	@Test
	public void testNoStationsAtStart(){
		TrainRegister tr = TrainRegister.getInstance();
		assertEquals(tr.getElementCount(), 0);
	}

	@Test
	public void testRegisterAtomicTrains(){
		String tid = "loc";
		Train l = new Locomotive(tid, 2, 100);
		
		String tid2 = "wag";
		Train w = new Wagon(tid2, 200, 100);
		
		TrainRegister tr = TrainRegister.getInstance();
		tr.registerElement(l);
		tr.registerElement(w);
		assertEquals(tr.getElementCount(), 2);
	}
	
	@Test
	public void testCreateAtomicTrains(){
		TrainRegister tr = TrainRegister.getInstance();
		tr.createLocomotive("loc", 2, 100);
		tr.createWagon("wag", 200, 110);
		assertEquals(tr.getElementCount(), 2);
	}
	
	@Test
	public void testClearRegister(){
		TrainRegister tr = TrainRegister.getInstance();
		tr.createLocomotive("loc", 2, 100);
		tr.createWagon("wag", 200, 110);
		assertEquals(tr.getElementCount(), 2);
		tr.clearRegister();
		assertEquals(tr.getElementCount(), 0);
	}
	
	@Test
	public void testGetLocomotiveAfterCreate(){
		TrainRegister tr = TrainRegister.getInstance();
		tr.createLocomotive("loc", 2, 100);
		
		Train tGet = tr.getElementByIdentifier("loc");
		assertEquals(tr.getElementCount(), 1);
		assertNotNull(tGet);
	}
	
	@Test
	public void testGetWaggonAfterAdd(){
		TrainRegister tr = TrainRegister.getInstance();
		Wagon w = new Wagon("wag", 200, 100);
		tr.registerElement(w);
		
		Train tGet = tr.getElementByIdentifier("wag");
		assertEquals(tr.getElementCount(), 1);
		assertNotNull(tGet);
	}
	
	@Test
	public void testRemoveTrain(){
		String name = "wag";
		
		TrainRegister tr = TrainRegister.getInstance();
		Wagon w = new Wagon(name, 200, 100);
		tr.registerElement(w);
		assertEquals(tr.getElementCount(), 1);
		
		Train tGet = tr.removeElementByIdentifier(name);
		assertSame(w, tGet);
		assertEquals(tr.getElementCount(), 0);
		
		assertNull(tr.getElementByIdentifier(name));
	}
	
	@Test
	public void testCreateCompositeFromRegisteredTrains(){
		TrainRegister tr = TrainRegister.getInstance();
		Locomotive l1 = new Locomotive("loc", 2, 100);
		Wagon w1 = new Wagon("wag", 200, 110);
		Locomotive l2 = new Locomotive("loc2", 2, 100);
		Wagon w2 = new Wagon("wag2", 200, 110);
		tr.registerElement(l1);
		tr.registerElement(l2);
		tr.registerElement(w1);
		tr.registerElement(w2);
		
		
		assertEquals(tr.getElementCount(), 4);
		tr.createCompositeTrainByIdentifier("composite");
		assertEquals(tr.getElementCount(), 5);
		tr.addTrainToComposite("composite", w1);
		tr.addTrainToComposite("composite", l1);
		assertEquals(tr.getElementCount(), 3);
		
		ArrayList<String> tp = new ArrayList<String>();
		tp.add(l2.getIdentifier());
		tp.add(w2.getIdentifier());
		
		tr.createCompositeTrainByIdentifier("composite2", tp);
		assertEquals(tr.getElementCount(), 2);
	}
	
	@Test
	public void testCreateCompositeFromUnregisteredTrains(){
		TrainRegister tr = TrainRegister.getInstance();
		Locomotive l1 = new Locomotive("loc", 2, 100);
		Wagon w1 = new Wagon("wag", 200, 110);
		Locomotive l2 = new Locomotive("loc2", 2, 100);
		Wagon w2 = new Wagon("wag2", 200, 110);
		
		assertEquals(tr.getElementCount(), 0);
		tr.createCompositeTrainByIdentifier("composite");
		assertEquals(tr.getElementCount(), 1);
		tr.addTrainToComposite("composite", w1);
		tr.addTrainToComposite("composite", l1);
		assertEquals(tr.getElementCount(), 1);
		
		ArrayList<String> tp = new ArrayList<String>();
		tp.add(l2.getIdentifier());
		tp.add(w2.getIdentifier());
		
		tr.createCompositeTrainByIdentifier("composite2", tp);
		assertEquals(tr.getElementCount(), 2);
	}
	
	@Test
	public void testGetAllTrains(){
		TrainRegister tr = TrainRegister.getInstance();
		Locomotive l1 = new Locomotive("loc", 2, 100);
		Wagon w1 = new Wagon("wag", 200, 110);
		Locomotive l2 = new Locomotive("loc2", 2, 100);
		Wagon w2 = new Wagon("wag2", 200, 110);
		tr.registerElement(l1);
		tr.registerElement(l2);
		tr.registerElement(w1);
		tr.registerElement(w2);
		
		
		assertEquals(tr.getElementCount(), 4);
		tr.createCompositeTrainByIdentifier("composite");
		assertEquals(tr.getElementCount(), 5);
		tr.addTrainToComposite("composite", w1);
		tr.addTrainToComposite("composite", l1);
		assertEquals(tr.getElementCount(), 3);
		
		assertEquals(tr.getAllElements().size(), 3);
		assertTrue(tr.getAllElements().contains(w2));
		assertTrue(tr.getAllElements().contains(l2));
		assertTrue(tr.getAllElements().contains(tr.getElementByIdentifier("composite")));
	}
	
	@Test
	public void testAddTrainToComposite(){
		TrainRegister tr = TrainRegister.getInstance();
		Locomotive l1 = new Locomotive("loc", 2, 100);
		Wagon w1 = new Wagon("wag", 200, 110);
		Locomotive l2 = new Locomotive("loc2", 2, 100);
		tr.registerElement(l1);
		tr.registerElement(l2);
		tr.registerElement(w1);
		
		ArrayList<String> tp = new ArrayList<String>();
		tp.add(l1.getIdentifier());
		tp.add(w1.getIdentifier());
		
		
		CompositeTrain comp = (CompositeTrain) tr.createCompositeTrainByIdentifier("comp1", tp);
		comp.addTrainPart(l2);
		CompositeTrain comp2 = (CompositeTrain) tr.getElementByIdentifier("comp1");
		assertTrue(comp2.getTrainPartsAsList().contains(l2));
	}
}
