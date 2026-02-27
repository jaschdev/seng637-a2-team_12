package org.jfree.data.test;

import static org.junit.Assert.assertEquals;

import org.jfree.data.Range;
import org.junit.Test;

public class testGetLengthNegativeBounds {

	@Test
	public void testGetLengthNegativeBounds() {
		Range r = new Range(-5.0, -2.0);
		assertEquals(3.0, r.getLength(), 0.0);
	}
}