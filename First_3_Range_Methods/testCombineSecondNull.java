package org.jfree.data.test;

import static org.junit.Assert.assertEquals;

import org.jfree.data.Range;
import org.junit.Test;

public class testCombineSecondNull {

	@Test
	public void testCombineSecondNull() {
		Range r1 = new Range(-3.0, 2.0);

		Range result = Range.combine(r1, null);

		assertEquals(r1.getLowerBound(), result.getLowerBound(), 0.0);
		assertEquals(r1.getUpperBound(), result.getUpperBound(), 0.0);
	}
}