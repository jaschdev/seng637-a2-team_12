package org.jfree.data.test;

import static org.junit.Assert.assertNull;

import org.jfree.data.Range;
import org.junit.Test;

public class testCombineBothNull {

	@Test
	public void testCombineBothNull() {
		Range result = Range.combine(null, null);
		assertNull(result);
	}
}