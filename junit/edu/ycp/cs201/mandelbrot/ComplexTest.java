/**
 * 
 */
package edu.ycp.cs201.mandelbrot;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Jackson
 *
 */
public class ComplexTest {

	private Complex comp1, comp2, comp3, comp4;
	private static final double DELTA = 0.01;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		comp1 = new Complex(3.0,2.0);
		comp2 = new Complex(1.0,4.0);
		comp3 = new Complex(2.2, 3.4);
		comp4 = new Complex(4, -9);
	}

	@Test
	public void testMultiply() {
		assertEquals(-5.0, comp1.multiply(comp2).getReal(), DELTA);
		assertEquals(14.0, comp1.multiply(comp2).getImag(), DELTA);
		assertEquals(-11.39, comp3.multiply(comp2).getReal(), DELTA);
		assertEquals(12.2, comp3.multiply(comp2).getImag(), DELTA);
		assertEquals(40.0, comp4.multiply(comp2).getReal(), DELTA);
		assertEquals(7.0, comp4.multiply(comp2).getImag(), DELTA);
	}
	
	@Test
	public void testAdd()
	{
		assertEquals(3.2, comp3.add(comp2).getReal(), DELTA);
		assertEquals(7.4, comp3.add(comp2).getImag(), DELTA);
		assertEquals(4, comp1.add(comp2).getReal(), DELTA);
		assertEquals(6, comp1.add(comp2).getImag(), DELTA);
		assertEquals(5.0, comp4.add(comp2).getReal(), DELTA);
		assertEquals(-5.0, comp4.add(comp2).getImag(), DELTA);
		
	}
	
	@Test
	public void testMagnitude()
	{
		assertEquals(9.85, comp4.getMagnitude(), DELTA);
		assertEquals(4.12, comp2.getMagnitude(), DELTA);
	}
	

}
