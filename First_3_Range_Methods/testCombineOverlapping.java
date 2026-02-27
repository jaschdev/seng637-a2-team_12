package org.jfree.data.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.jfree.data.Range;
import org.junit.Test;

public class testCombineOverlapping {

	@Test
	public void testCombineOverlapping() {
		Range r1 = new Range(1.0, 5.0);
		Range r2 = new Range(3.0, 8.0);

		try {
			Range result = Range.combine(r1, r2);

			assertNotNull("Result should not be null", result);
			assertEquals(1.0, result.getLowerBound(), 0.0);
			assertEquals(8.0, result.getUpperBound(), 0.0);

			// SUT fails here, tries to create new Range(1.0, 3.0), results in an error
			// because assertions don't execute.
			// Added assertNotNull() to catch this error, and result in the test failure

		} catch (IllegalArgumentException e) {
			fail("combine() should not throw exception for overlapping ranges");
		}

	}
}