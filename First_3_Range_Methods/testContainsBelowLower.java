package org.jfree.data.test;

import static org.junit.Assert.assertFalse;

import org.jfree.data.Range;
import org.junit.Test;

public class testContainsBelowLower {

	@Test
	public void testContainsBelowLower() {
		Range r = new Range(1.0, 5.0);
		assertFalse(r.contains(0.5));
	}
}