package org.jfree.data.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.jfree.data.Range;
import org.junit.Test;

public class testCombineDisjoint {

	@Test
	public void testCombineDisjoint() {
		Range r1 = new Range(1.0, 2.0);
		Range r2 = new Range(5.0, 7.0);

		try {

			Range result = Range.combine(r1, r2);

			assertNotNull("Result should not be null", result);
			assertEquals(1.0, result.getLowerBound(), 0.0);
			assertEquals(7.0, result.getUpperBound(), 0.0);

			// Same issue here, SUT tries to do new Range(5.0, 1.0). throws error.

			// Added assertNotNull() to catch this error, and result in the test failure

		} catch (IllegalArgumentException e) {
			fail("combine() should not throw exception for overlapping ranges");
		}

	}
}