package org.jfree.data.test;

import static org.junit.Assert.assertTrue;

import org.jfree.data.Range;
import org.junit.Test;

public class testContainsLowerBoundary {

	@Test
	public void testContainsLowerBoundary() {
		Range r = new Range(1.0, 5.0);
		assertTrue(r.contains(1.0));
	}
}