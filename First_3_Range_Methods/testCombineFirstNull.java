package org.jfree.data.test;

import static org.junit.Assert.assertEquals;

import org.jfree.data.Range;
import org.junit.Test;

public class testCombineFirstNull {

	@Test
	public void testCombineFirstNull() {
		Range r2 = new Range(1.0, 5.0);

		Range result = Range.combine(null, r2);

		assertEquals(r2.getLowerBound(), result.getLowerBound(), 0.0);
		assertEquals(r2.getUpperBound(), result.getUpperBound(), 0.0);
	}
}