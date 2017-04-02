package stars;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Melvin Bosnjak, u0989241
 * @author Adrian Bollerslev, u1115021
 */
public class Geometry_VectorTest {

	/**
	 * Tests for the geometry vector class
	 */
	@Test
	public void testMagnitudeCommonCase() {
		Geometry_Vector testCase = new Geometry_Vector(3.0, 4.0);
		assertTrue(testCase.magnitude() == 5.0);
	}
	
	@Test
	public void testMagnitudeNegativeCase() {
		Geometry_Vector testCase = new Geometry_Vector(-3.0, 4.0);
		assertTrue(testCase.magnitude() == 5.0);
	}
	
	@Test
	public void testAddToMeCommonCase() {
		Geometry_Vector vector = new Geometry_Vector(3.0, 4.0);
		Geometry_Vector testCase = new Geometry_Vector(3.0, 4.0);
		testCase.add_to_me(vector);
		assertTrue(testCase.getX() == 6.0 && testCase.getY() == 8.0);
	}
	
	@Test
	public void testAddToMeNegativeCase() {
		Geometry_Vector vector = new Geometry_Vector(-3.0, -4.0);
		Geometry_Vector testCase = new Geometry_Vector(3.0, 4.0);
		testCase.add_to_me(vector);
		assertTrue(testCase.getX() == 0.0 && testCase.getY() == 0.0);
	}
	
	@Test
	public void testSubtractToMeCommonCase() {
		Geometry_Vector vector = new Geometry_Vector(3.0, 4.0);
		Geometry_Vector testCase = new Geometry_Vector(3.0, 4.0);
		testCase.subtract_from_me(vector);
		assertTrue(testCase.getX() == 0.0 && testCase.getY() == 0.0);
	}
	
	@Test
	public void testSubtractToMeNegativeCase() {
		Geometry_Vector vector = new Geometry_Vector(-3.0, -4.0);
		Geometry_Vector testCase = new Geometry_Vector(3.0, 4.0);
		testCase.subtract_from_me(vector);
		assertTrue(testCase.getX() == 6.0 && testCase.getY() == 8.0);
	}
	
	@Test
	public void testDivideBy() {
		Geometry_Vector vector = new Geometry_Vector(6.0, 4.0);
		vector.divide_by(2);
		assertTrue(vector.getX() == 3.0 && vector.getY() == 2.0);

	}

	@Test
	public void testDivideByEdgeCase() {
		Geometry_Vector vector = new Geometry_Vector(6.0, 4.0);
		vector.divide_by(0);
		assertTrue(vector.getX() == Double.POSITIVE_INFINITY && vector.getY() == Double.POSITIVE_INFINITY);
	}

	@Test
	public void testMultiplyMeBy() {
		Geometry_Vector vector = new Geometry_Vector(6.0, 4.0);
		vector.multiply_me_by(2);
		assertTrue(vector.getX() == 12.0 && vector.getY() == 8.0);

	}
	
	@Test
	public void testMultiplyMeByZero() {
		Geometry_Vector vector = new Geometry_Vector(6.0, 4.0);
		vector.multiply_me_by(0);
		assertTrue(vector.getX() == 0.0 && vector.getY() == 0.0);

	}
	
	@Test
	public void testToString() {
		Geometry_Vector vector = new Geometry_Vector(3.0, 4.0);
		assertTrue(vector.toString().equals("X component: 3.0 Y component: 4.0 Magnitude: 5.0"));
	}
	
	@Test
	public void testZeroToString() {
		Geometry_Vector vector = new Geometry_Vector(0.0, 0.0);
		assertTrue(vector.toString().equals("X component: 0.0 Y component: 0.0 Magnitude: 0.0"));
	}

	@Test
	public void testNormalize() {
		Geometry_Vector vector = new Geometry_Vector(6.0, 4.0);
		vector.normalize();
		assertTrue(vector.magnitude() == 1.0);

	}@Test
	public void testNormalizeNegativeNumber() {
		Geometry_Vector vector = new Geometry_Vector(-3.0, -6.0);
		vector.normalize();
		assertTrue(vector.magnitude() == 0.9999999999999999);
	}
}
