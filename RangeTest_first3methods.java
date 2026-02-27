package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeTest_first3methods {
    private Range exampleRange;
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception { exampleRange = new Range(-1, 1);
    }
    
    /*
     * ============================
     * COMBINE() TESTS
     * ============================
     */
    
    // Robustness Test: both ranges are null → expect null result
	@Test
	public void testCombineBothNull() {
		Range result = Range.combine(null, null);
		assertNull(result);
	}
	
    // Weak ECP: first parameter null → return second range
	@Test
	public void testCombineFirstNull() {
		Range r2 = new Range(1.0, 5.0);

		Range result = Range.combine(null, r2);

		assertEquals(r2.getLowerBound(), result.getLowerBound(), 0.000000001d);
		assertEquals(r2.getUpperBound(), result.getUpperBound(), 0.000000001d);
	}
	
    // Weak ECP: second parameter null → return first range
	@Test
	public void testCombineSecondNull() {
		Range r1 = new Range(-3.0, 2.0);

		Range result = Range.combine(r1, null);

		assertEquals(r1.getLowerBound(), result.getLowerBound(), 0.000000001d);
		assertEquals(r1.getUpperBound(), result.getUpperBound(), 0.000000001d);
	}
	
    // Weak ECP: partially overlapping ranges
	@Test
	public void testCombineOverlapping() {
		Range r1 = new Range(1.0, 5.0);
		Range r2 = new Range(3.0, 8.0);

		try {
			Range result = Range.combine(r1, r2);

			assertNotNull("Result should not be null", result);
			assertEquals(1.0, result.getLowerBound(), 0.000000001d);
			assertEquals(8.0, result.getUpperBound(), 0.000000001d);

			// SUT fails here, tries to create new Range(1.0, 3.0), results in an error
			// because assertions don't execute.
			// Added assertNotNull() to catch this error, and result in the test failure

		} catch (IllegalArgumentException e) {
			fail("combine() should not throw exception for overlapping ranges");
		}
	}
	
    // Weak ECP: disjoint non-overlapping ranges
	@Test
	public void testCombineDisjoint() {
		Range r1 = new Range(1.0, 2.0);
		Range r2 = new Range(5.0, 7.0);

		try {

			Range result = Range.combine(r1, r2);

			assertNotNull("Result should not be null", result);
			assertEquals(1.0, result.getLowerBound(), 0.000000001d);
			assertEquals(7.0, result.getUpperBound(), 0.000000001d);

			// Same issue here, SUT tries to do new Range(5.0, 1.0). throws error.

			// Added assertNotNull() to catch this error, and result in the test failure

		} catch (IllegalArgumentException e) {
			fail("combine() should not throw exception for overlapping ranges");
		}
	}
    
    // Weak ECP: one range fully contained inside the other
	@Test
	public void testCombineContained() {
		Range r1 = new Range(1.0, 10.0);
		Range r2 = new Range(3.0, 4.0);

		try {

			Range result = Range.combine(r1, r2);

			assertNotNull("Result should not be null", result);
			assertEquals(1.0, result.getLowerBound(), 0.000000001d);
			assertEquals(10.0, result.getUpperBound(), 0.000000001d);

			// Added assertNotNull() to catch this error, and result in the test failure

		} catch (IllegalArgumentException e) {
			fail("combine() should not throw exception for overlapping ranges");
		}
	}
	
    /*
     * ============================
     * CONTAINS(double) TESTS
     * ============================
     */
	
    // Weak ECP: value strictly inside range
	@Test
	public void testContainsValueInside() {
		Range r = new Range(1.0, 5.0);
		assertTrue(r.contains(3.0));
	}
	
    // Boundary Value Analysis: value equals lower boundary
	@Test
	public void testContainsLowerBoundary() {
		Range r = new Range(1.0, 5.0);
		assertTrue(r.contains(1.0));
	}

    // Boundary Value Analysis: value equals upper boundary
	@Test
	public void testContainsUpperBoundary() {
		Range r = new Range(1.0, 5.0);
		assertTrue(r.contains(5.0));
	}
	
    // Boundary Value Analysis: value below lower bound
	@Test
	public void testContainsBelowLower() {
		Range r = new Range(1.0, 5.0);
		assertFalse(r.contains(0.5));
	}
	
    // Boundary Value Analysis: value above upper bound
	@Test
	public void testContainsAboveUpper() {
		Range r = new Range(1.0, 5.0);
		assertFalse(r.contains(6.0));
	}
	
    /*
     * ============================
     * GETLENGTH() TESTS
     * ============================
     */

    // Weak ECP: positive range
	@Test
	public void testGetLengthPositiveRange() {
		Range r = new Range(2.0, 10.0);
		assertEquals(8.0, r.getLength(), 0.000000001d);
	}
	
    // Boundary Value Analysis: zero-length range (lower == upper)
	@Test
	public void testGetLengthZeroLength() {
		Range r = new Range(3.0, 3.0);
		assertEquals(0.0, r.getLength(), 0.000000001d);
	}
	
    // Weak ECP: both bounds negative
	@Test
	public void testGetLengthNegativeBounds() {
		Range r = new Range(-5.0, -2.0);
		assertEquals(3.0, r.getLength(), 0.000000001d);
	}
	
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
