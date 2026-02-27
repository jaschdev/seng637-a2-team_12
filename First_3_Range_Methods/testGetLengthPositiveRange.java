package org.jfree.data.test;

import static org.junit.Assert.assertEquals;

import org.jfree.data.Range;
import org.junit.Test;

public class testGetLengthPositiveRange {

	@Test
	public void testGetLengthPositiveRange() {
		Range r = new Range(2.0, 10.0);
		assertEquals(8.0, r.getLength(), 0.0);
	}
}