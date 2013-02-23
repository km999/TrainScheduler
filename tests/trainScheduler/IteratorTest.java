/*******************************************************************************
 * Project: TrainScheduler
 * Author: Stefan Klikovits
 * 
 * This software has been created by Stefan Klikovits in 2013. 
 * It was written for the course 'Pattern based software development' at the University of Manchester.
 ******************************************************************************/
package trainScheduler;

import static org.junit.Assert.*;

import org.junit.Test;

import trainScheduler.templates.Iterator;



public class IteratorTest {
	
	@Test
	public void testConstructorWithParameter(){
		Object current = new Object();
		Iterator<Object> it = new Iterator<Object>(current);
		assertNotNull(it.getObject());
		assertSame(current, it.getObject());
		
		assertNull(it.next());
		assertFalse(it.hasNext());
	}
	
	@Test
	public void testConstructorWithFullParameters(){
		Object current = new Object();
		Iterator<Object> next = new Iterator<Object>(null);

		Iterator<Object> it = new Iterator<Object>(current, next);
		assertSame(current, it.getObject());
		assertNotNull(it.next());
		assertTrue(it.hasNext());
		assertSame(next, it.next());
	}
	
	@Test
	public void testSetCurrent(){
		Object current = new Object();
		Iterator<Object> it = new Iterator<Object>(null);
		it.setObject(current);
		
		assertNotNull(it.getObject());
		assertSame(current, it.getObject());
		assertNull(it.next());
		assertFalse(it.hasNext());
	}
	
	@Test
	public void testSetNextWithIterator(){
		Iterator<Object> next = new Iterator<Object>(null);
		Iterator<Object> it = new Iterator<Object>(null);
		assertNull(it.getObject());
		assertNull(it.next());
		assertFalse(it.hasNext());
		
		it.setNext(next);
		assertNotNull(it.next());
		assertTrue(it.hasNext());
		assertSame(next, it.next());
	}
	
	
	@Test
	public void testSetNextWithObject(){
		Object next = new Object();
		Iterator<Object> it = new Iterator<Object>(null);
		assertNull(it.getObject());
		assertNull(it.next());
		assertFalse(it.hasNext());
		
		it.setNext(next);
		assertNotNull(it.next());
		assertTrue(it.hasNext());
		assertSame(next, it.next().getObject());
	}
	
	@Test
	public void testCreateNext(){
		Object current = new Object();
		Object next = new Object();
		Iterator<Object> it = new Iterator<Object>(current);
		it.setNext(next);
		
		assertNotNull(it.getObject());
		assertNotNull(it.next());
		assertSame(it.next().getObject(), next);
	}
}
