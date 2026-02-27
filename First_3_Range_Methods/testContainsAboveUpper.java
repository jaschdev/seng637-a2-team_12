package org.jfree.data.test;

import static org.junit.Assert.assertFalse;

import org.jfree.data.Range;
import org.junit.Test;

public class testContainsAboveUpper {

	@Test
	public void testContainsAboveUpper() {
		Range r = new Range(1.0, 5.0);
		assertFalse(r.contains(6.0));
	}
}