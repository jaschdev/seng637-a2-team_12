package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeTest_Shift_toString {
    private Range exampleRange;
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { exampleRange = new Range(-1, 1);
    }

    /*
     * ============================
     * SHIFT() TESTS
     * ============================
     */

    // Robustness Test: base = null → expect NullPointerException
    @Test(expected = NullPointerException.class)
    public void testShiftNullBase() {
        Range.shift(null, 1.0, true);
    }

    // Boundary Value Analysis: delta = 0 → range unchanged
    @Test
    public void testShiftZeroDelta() {
        Range shifted = Range.shift(exampleRange, 0.0, true);
        assertEquals(-1.0, shifted.getLowerBound(), 0.000000001d);
        assertEquals(1.0, shifted.getUpperBound(), 0.000000001d);
    }

    // Weak ECP: delta > 0, allow zero crossing → both bounds increase
    @Test
    public void testShiftPositiveDeltaAllow() {
        Range shifted = Range.shift(exampleRange, 2.0, true);
        assertEquals(1.0, shifted.getLowerBound(), 0.000000001d);
        assertEquals(3.0, shifted.getUpperBound(), 0.000000001d);
    }

    // Weak ECP: delta < 0, allow zero crossing → both bounds decrease
    @Test
    public void testShiftNegativeDeltaAllow() {
        Range shifted = Range.shift(exampleRange, -2.0, true);
        assertEquals(-3.0, shifted.getLowerBound(), 0.000000001d);
        assertEquals(-1.0, shifted.getUpperBound(), 0.000000001d);
    }

    // Strong ECP: zero crossing prevented when allowZeroCrossing = false
    @Test
    public void testShiftNegativeAllowZeroCross() {
        Range base = new Range(-1.0, 1.0);
        Range shifted = Range.shift(base, -2.0, false);

        // Lower bound should stop at 0 instead of crossing
        assertEquals(-3.0, shifted.getLowerBound(), 0.000000001d);
        assertEquals(0.0, shifted.getUpperBound(), 0.000000001d);
    }

    /*
     * ============================
     * toString() TESTS
     * ============================
     */

    // Weak ECP: normal positive range formatting
    @Test
    public void testToStringNormalRange() {
        Range rangeString1 = new Range(1.0, 5.0);
        assertEquals("Range[1.0,5.0]", rangeString1.toString());
    }

    // Boundary Value Analysis: negative bounds
    @Test
    public void testToStringNegativeRange() {
        Range rangeString2 = new Range(-5.0, -1.0);
        assertEquals("Range[-5.0,-1.0]", rangeString2.toString());
    }

    // Boundary Value Analysis: zero-length range (lower = upper)
    @Test
    public void testToStringZeroLength() {
        Range rangeString3 = new Range(2.0, 2.0);
        assertEquals("Range[2.0,2.0]", rangeString3.toString());
    }

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
