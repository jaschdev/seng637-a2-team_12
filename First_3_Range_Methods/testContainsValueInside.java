package org.jfree.data.test;

import static org.junit.Assert.assertTrue;

import org.jfree.data.Range;
import org.junit.Test;

public class testContainsValueInside {

	@Test
	public void testContainsValueInside() {
		Range r = new Range(1.0, 5.0);
		assertTrue(r.contains(3.0));
	}
}