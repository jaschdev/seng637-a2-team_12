package org.jfree.data.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.jfree.data.Range;
import org.junit.Test;

public class testCombineContained {

	@Test
	public void testCombineContained() {
		Range r1 = new Range(1.0, 10.0);
		Range r2 = new Range(3.0, 4.0);

		try {

			Range result = Range.combine(r1, r2);

			assertNotNull("Result should not be null", result);
			assertEquals(1.0, result.getLowerBound(), 0.0);
			assertEquals(10.0, result.getUpperBound(), 0.0);

			// Added assertNotNull() to catch this error, and result in the test failure

		} catch (IllegalArgumentException e) {
			fail("combine() should not throw exception for overlapping ranges");
		}
	}
}