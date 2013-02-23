package trainScheduler.trains;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CompositeTrainTest {

	@Test
	public void testConnectTwoLocomotives(){
		Locomotive l1 = new Locomotive("loc_1", 2, 100);
		Locomotive l2 = new Locomotive("loc_2", 2, 100);
		
		ArrayList<Train> trainlist = new ArrayList<Train>();
		trainlist.add(l1);
		trainlist.add(l2);
		
		CompositeTrain ct = new CompositeTrain("composite_1", trainlist);
		assertEquals(ct.getTrainPartsAsList().size(), 2);
	}
	
	@Test
	public void testConnectThreeWaggons(){
		Wagon w1 = new Wagon("wag_1", 200, 100);
		Wagon w2 = new Wagon("wag_2", 200, 100);
		Wagon w3 = new Wagon("wag_3", 200, 100);
		
		ArrayList<Train> trainlist = new ArrayList<Train>();
		trainlist.add(w1);
		trainlist.add(w2);
		trainlist.add(w3);
		
		CompositeTrain ct = new CompositeTrain("composite_1", trainlist);
		assertEquals(ct.getTrainPartsAsList().size(), 3);
	}
	
	@Test
	public void testConnectLocomotiveAndWaggon(){
		Wagon w = new Wagon("wag", 200, 100);
		Locomotive l = new Locomotive("loc", 2, 100);
		
		ArrayList<Train> trainlist = new ArrayList<Train>();
		trainlist.add(w);
		trainlist.add(l);
		
		CompositeTrain ct = new CompositeTrain("composite_1", trainlist);
		assertEquals(ct.getTrainPartsAsList().size(), 2);
	}
	
	@Test
	public void testAddTrainPart(){
		Wagon w = new Wagon("wag", 200, 100);
		Locomotive l = new Locomotive("loc", 2, 100);
		
		CompositeTrain ct = new CompositeTrain("composite_1");
		ct.addTrainPart(w);
		ct.addTrainPart(l);
		assertEquals(ct.getTrainPartsAsList().size(), 2);
	}
	
	@Test
	public void testGetIdentifier(){
		
		String name = "composite_1";
		CompositeTrain ct = new CompositeTrain(name);
		assertEquals(ct.getIdentifier(), name);
	}
	
	@Test
	public void testRecursiveFunctions(){
		Wagon w = new Wagon("wag", 200, 100);
		Wagon w2 = new Wagon("wag", 150, 90);

		Locomotive l = new Locomotive("loc", 2, 70);
		Locomotive l2 = new Locomotive("loc", 3, 80);
		
		CompositeTrain ct = new CompositeTrain("composite_1");
		ct.addTrainPart(w);
		ct.addTrainPart(w2);
		assertEquals(ct.getMaxSpeed(), 90);
		
		
		CompositeTrain ct2 = new CompositeTrain("composite_2");
		ct2.addTrainPart(l);
		ct2.addTrainPart(l2);
		assertEquals(ct2.getMaxSpeed(), 70);
		
		CompositeTrain ct3 = new CompositeTrain("composite_3");
		ct3.addTrainPart(ct);
		ct3.addTrainPart(ct2);
		
		assertEquals(ct3.getMaxSpeed(), 70);
	}

	@Test
	public void testConnectOneTrainpartOnly(){
		Wagon w = new Wagon("wag", 200, 100);		
		ArrayList<Train> trainlist = new ArrayList<Train>();
		trainlist.add(w);
		CompositeTrain ct = new CompositeTrain("composite_1", trainlist);
		assertEquals(ct.getTrainPartsAsList().size(), 1);
		
		
		Locomotive l = new Locomotive("loc", 2, 100);
		ArrayList<Train> trainlist2 = new ArrayList<Train>();
		trainlist2.add(l);
		CompositeTrain ct2 = new CompositeTrain("composite_2", trainlist2);
		assertEquals(ct2.getTrainPartsAsList().size(), 1);
	}
	
	
	@Test
	public void testConnectTwoComposites(){
		Locomotive l1 = new Locomotive("loc_1", 2, 100);
		Locomotive l2 = new Locomotive("loc_2", 2, 100);
		
		ArrayList<Train> trainlist = new ArrayList<Train>();
		trainlist.add(l1);
		trainlist.add(l2);
		
		CompositeTrain ct = new CompositeTrain("composite_1", trainlist);
		assertEquals(ct.getTrainPartsAsList().size(), 2);

		Wagon w1 = new Wagon("wag_1", 200, 100);
		Wagon w2 = new Wagon("wag_2", 200, 100);
		
		ArrayList<Train> trainlist2 = new ArrayList<Train>();
		trainlist2.add(w1);
		trainlist2.add(w2);
		
		CompositeTrain ct2 = new CompositeTrain("composite_2", trainlist2);
		assertEquals(ct2.getTrainPartsAsList().size(), 2);
		
		ArrayList<Train> trainlist3 = new ArrayList<Train>();
		trainlist3.add(ct);
		trainlist3.add(ct2);
		
		CompositeTrain ct3 = new CompositeTrain("composite_3", trainlist3);
		assertTrue(ct3.getTrainPartsAsList().contains(ct));
		assertTrue(ct3.getTrainPartsAsList().contains(ct2));
	}
	
	@Test
	public void testConnectCompositeAndAtomic(){
		Locomotive l1 = new Locomotive("loc_1", 2, 100);
		Locomotive l2 = new Locomotive("loc_2", 2, 90);
		
		ArrayList<Train> trainlist = new ArrayList<Train>();
		trainlist.add(l1);
		trainlist.add(l2);
		
		CompositeTrain ct = new CompositeTrain("composite_1", trainlist);
		assertEquals(ct.getTrainPartsAsList().size(), 2);

		Wagon w = new Wagon("wag_1", 200, 110);
		
		CompositeTrain ct2 = new CompositeTrain("composite_2");
		ct2.addTrainPart(ct);
		ct2.addTrainPart(w);
		assertEquals(ct2.getTrainPartsAsList().size(), 2);
		
		// assert recursives
		assertEquals(ct2.getMaxSpeed(), 90);
		
	}
	
}
