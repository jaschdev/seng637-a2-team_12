package org.jfree.data.test;

import static org.junit.Assert.assertEquals;

import org.jfree.data.Range;
import org.junit.Test;

public class testGetLengthZeroLength {

	@Test
	public void testGetLengthZeroLength() {
		Range r = new Range(3.0, 3.0);
		assertEquals(0.0, r.getLength(), 0.0);
	}
}