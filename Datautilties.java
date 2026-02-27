package org.jfree.data.test;

import org.jfree.data.DataUtilities;


import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.Values2D;
import org.jfree.data.KeyedValues;
import org.jmock.Mockery;
import org.jmock.Expectations;




public class DataUtilitiesTest extends DataUtilities {
	
	private static final double EPS = 0.000000001d;
	
	private DefaultKeyedValues2D createMulti() {
		DefaultKeyedValues2D multiRowData = new DefaultKeyedValues2D();
	    multiRowData.addValue(1,  "row0", "col0");
	    multiRowData.addValue(-1, "row1", "col0");
	    multiRowData.addValue(0,  "row2", "col0");
	    multiRowData.addValue(2,  "row3", "col0");
	    multiRowData.addValue(10, "row4", "col0");
	    return multiRowData;
	}
	
	private DefaultKeyedValues2D createSingle() {
		DefaultKeyedValues2D singleRowData = new DefaultKeyedValues2D();
		singleRowData.addValue(5,"row0", "col0");
		
		return singleRowData;
	}
	

    // Test 1: Check for null input (not allowed)
    @Test
    public void createNumberArray2D_nullInput() {
        
    	try {
            DataUtilities.createNumberArray2D(null);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {}
    }
    
    
    // Test 2:  2D array with 1 empty row
    @Test
    public void createNumberArray2D_emptyInnerArray() {
        
    	double[][] input = { {} };

        Number[][] output = DataUtilities.createNumberArray2D(input);

        assertEquals(1, output.length);
        assertNotNull(output[0]);
        assertEquals(0, output[0].length);
    }
    
    
    // Test 3: Empty outer array
    @Test
    public void createNumberArray2D_emptyOuterArray() {
       
    	double[][] input = new double[0][];

        Number[][] output = DataUtilities.createNumberArray2D(input);

        assertNotNull(output);
        assertEquals(0, output.length);
    }


    // Test 4: 1x1 array
    @Test
    public void createNumberArray2D_singleElement() {
       
    	double[][] input = { { 5.5 } };

        Number[][] output = DataUtilities.createNumberArray2D(input);
        
        assertNotNull("Output should not be null", output);
        assertNotNull("Output should not be null", output[0][0]);

        assertEquals(1, output.length);
        assertEquals(1, output[0].length);
        assertEquals(5.5, output[0][0].doubleValue(), EPS);
    }
 
    
    // Test 5: 2x2 array with negative/zero/positive values
    @Test
    public void createNumberArray2D_negativeAndZeroValues() {
        
    	double[][] input = {
            { 1.0, -2.5 },
            { 0.0,  3.25 }
        };

        Number[][] output = DataUtilities.createNumberArray2D(input);

        assertNotNull("Output should not be null", output);
        assertNotNull("Output should not be null", output[0][0]);
        assertNotNull("Output should not be null", output[0][1]);
        assertNotNull("Output should not be null", output[1][0]);
        assertNotNull("Output should not be null", output[1][1]);

        assertTrue(output[0][0] instanceof Double);

        assertEquals(2, output.length);
        assertEquals(2, output[0].length);
        assertEquals(2, output[1].length);

        assertEquals(1.0,  output[0][0].doubleValue(), EPS);
        assertEquals(-2.5, output[0][1].doubleValue(), EPS);
        assertEquals(0.0,  output[1][0].doubleValue(), EPS);
        assertEquals(3.25, output[1][1].doubleValue(), EPS);

    }



    // Test suit for getCumulativePercentages
    
    
    // Test 1: null input (not allowed)
    @Test
    public void getCumulativePercentages_nullInput() {
    	
    	try {
            DataUtilities.getCumulativePercentages(null);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {}
    }
    
    
    // Test 2: input empty dataset
    @Test
    public void getCumulativePercentages_emptyDataset() {

    	Mockery context = new Mockery();
        final KeyedValues data = context.mock(KeyedValues.class);

        context.checking(new Expectations() {{
            allowing(data).getItemCount(); 
            will(returnValue(0));
        }});

        
        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        
        assertNotNull(result);
        assertEquals(0, result.getItemCount());

        context.assertIsSatisfied();
    }
    
    
    // Test 3: input dataset with single key/value
    @Test
    public void getCumulativePercentages_singleValue() {
        Mockery context = new Mockery();
        final KeyedValues data = context.mock(KeyedValues.class);

        context.checking(new Expectations() {{
            allowing(data).getItemCount(); will(returnValue(1));
            allowing(data).getKey(0); will(returnValue("A"));
            allowing(data).getValue(0); will(returnValue(7.0));
        }});

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertNotNull(result);
        assertEquals(1, result.getItemCount());
        assertEquals("A", result.getKey(0));
        assertEquals(1.0, result.getValue(0).doubleValue(), EPS);

    }
    
    
    // Test 4: input dataset with multiple positive values
    @Test
    public void getCumulativePercentages_multiplePositiveValues() {
        Mockery context = new Mockery();
        final KeyedValues data = context.mock(KeyedValues.class);

        context.checking(new Expectations() {{
            allowing(data).getItemCount(); will(returnValue(2));
            allowing(data).getKey(0); will(returnValue("A"));
            allowing(data).getValue(0); will(returnValue(7.0));
  		    allowing(data).getKey(1); will(returnValue("B"));
  		    allowing(data).getValue(1); will(returnValue(2.0));
        }});

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertNotNull(result);
        assertEquals(2, result.getItemCount());
        assertEquals(7.0/9.0, result.getValue(0).doubleValue(), EPS);        
        assertEquals(1.0, result.getValue(1).doubleValue(), EPS);

    }
    
    
    // Test 4: input dataset with multiple zero values
    @Test
    public void getCumulativePercentages_multipleZeroValues() {
        Mockery context = new Mockery();
        final KeyedValues data = context.mock(KeyedValues.class);

        context.checking(new Expectations() {{
            allowing(data).getItemCount(); will(returnValue(2));
            allowing(data).getKey(0); will(returnValue("A"));
            allowing(data).getValue(0); will(returnValue(0.0));
  		    allowing(data).getKey(1); will(returnValue("B"));
  		    allowing(data).getValue(1); will(returnValue(0.0));
        }});

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertNotNull(result);
        assertEquals(2, result.getItemCount());
        assertTrue(Double.isNaN(result.getValue(0).doubleValue()));        
        assertTrue(Double.isNaN(result.getValue(1).doubleValue()));        

    }
    
    
    // Test 5: input dataset with mix of positive and negative values
    @Test
    public void getCumulativePercentages_mixedValues() {
        Mockery context = new Mockery();
        final KeyedValues data = context.mock(KeyedValues.class);

        context.checking(new Expectations() {{
            allowing(data).getItemCount(); will(returnValue(2));
            allowing(data).getKey(0); will(returnValue("A"));
            allowing(data).getValue(0); will(returnValue(7.0));
  		    allowing(data).getKey(1); will(returnValue("B"));
  		    allowing(data).getValue(1); will(returnValue(-2.0));
        }});

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertNotNull(result);
        assertEquals(2, result.getItemCount());
        assertEquals(7.0/5.0, result.getValue(0).doubleValue(), EPS);        
        assertEquals(1.0, result.getValue(1).doubleValue(), EPS);

    }
    
    
    // Test 6: input dataset with multiple zero values
    @Test
    public void getCumulativePercentages_zeroTotalSum() {
        Mockery context = new Mockery();
        final KeyedValues data = context.mock(KeyedValues.class);

        context.checking(new Expectations() {{
            allowing(data).getItemCount(); will(returnValue(2));
            allowing(data).getKey(0); will(returnValue("A"));
            allowing(data).getValue(0); will(returnValue(5.0));
  		    allowing(data).getKey(1); will(returnValue("B"));
  		    allowing(data).getValue(1); will(returnValue(-5.0));
        }});

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertNotNull(result);
        assertEquals(2, result.getItemCount());
        assertTrue(Double.isInfinite(result.getValue(0).doubleValue()));        
        assertTrue(Double.isNaN(result.getValue(1).doubleValue()));      

    }
	
	@Test
	public void testCalculateColumnTotalNullData() {
		DefaultKeyedValues2D data = new DefaultKeyedValues2D();
		data.addValue(null, "row0", "col0");
		data.addValue(null, "row0", "col0");
		
	    int column = 0;

	    try {
	    	double result = DataUtilities.calculateColumnTotal(data, column);
	    	fail("expected some exception to be thrown due to not permitting null data");
	    }
	    catch(Exception e){
	    	assertTrue(true);
	    }
	}
	
	@Test
	public void testCalculateColumnTotalEmptyTable() {
		
		DefaultKeyedValues2D data = new DefaultKeyedValues2D();
		int column = 0;
		
		double result = DataUtilities.calculateColumnTotal(data, column);
		
		assertEquals(0.0, result, 0.0000001);
	}
	
	@Test public void testCalculateColumnTotalSingleRow() {

		double expected = 5;
		int column = 0;
		
		double result = DataUtilities.calculateColumnTotal(createSingle(), column);
		
		System.out.println("results from testCalculateColumnTotalSingleRow:");
		System.out.println(result);
		
		assertEquals(expected, result, 0.00001d);
		
	}
	
	@Test public void testCalculateColumnTotalMultipleRows() {

		double expected = 1 + -1 + 0 + 2 + 10; //Hand calculated, the answer should be 12
		
		
		int column = 0;
		
		double result = DataUtilities.calculateColumnTotal(createMulti(), column);
		System.out.println("results from testCalculateColumnMultipleRows:");
		System.out.println(result);
		assertEquals(expected, result, 0.00001d);
		
	}
	
	
	@Test 
	public void testCalculateColumnTotalNegativeColumn() {
		
		int column = -1;
		try {
			double result = DataUtilities.calculateColumnTotal(createMulti(), column);
			System.out.println("results from testCalculateColumnTotalSingleRow:");
			System.out.println(result);
			fail("expected some exception to be thrown");
		}
		catch(Exception e) {
			assertTrue(true);
		}
		
	}
	
	@Test 
	public void testCalculateColumnTotalTooLarge() {
		
		int column = 2;
		try {
			double result = DataUtilities.calculateColumnTotal(createSingle(), column);
			System.out.println("results from testCalculateColumnTooLarge:");
			System.out.println(result);
			fail("expected some exception to be thrown");
		}
		catch(Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testCalculateRowTotalNullData() {
		DefaultKeyedValues2D data = new DefaultKeyedValues2D();
		data.addValue(null, "row0", "col0");
		data.addValue(null, "row0", "col1");
		try {
			double result = DataUtilities.calculateRowTotal(data, 0);
		}
		catch(Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testCalculateRowTotalEmptyTable() {
		DefaultKeyedValues2D data = new DefaultKeyedValues2D();
		
		double result = DataUtilities.calculateRowTotal(data, 0);
		
		assertEquals(0.0, result, 0.0001d);
	}
	
	@Test
	public void testCalculateRowTotalSingleColumn() {

		DefaultKeyedValues2D data = new DefaultKeyedValues2D();
		data.addValue(1, "row0", "col0");
		data.addValue(0, "row1", "col0");

		
		double result = DataUtilities.calculateRowTotal(data, 0);
		System.out.println("results from testCalculateRowTotalSingleColumn, output should be 1: ");
		System.out.println(result);
		
		assertEquals(1, result, 0.0001d);
	}
	
	@Test
	public void testCalculateRowTotalMultipleColumns() {
		DefaultKeyedValues2D data = new DefaultKeyedValues2D();
		data.addValue(3, "row0", "col0");
		data.addValue(4, "row0", "col1");
		data.addValue(5, "row0", "col2");
		double expected = 3 + 4 + 5; //hand calculated to 12
		
		double result = DataUtilities.calculateRowTotal(data, 0);
		System.out.println("testCalculateRowTotalMultipleColumns: expected value is 12 actual value is: ");
		System.out.println(result);
		assertEquals(12, result, 0.00001d);
	}
	
	@Test
	public void testCalculateRowTotalNegativeRow() {
		DefaultKeyedValues2D data = new DefaultKeyedValues2D();
		data.addValue(3, "row0", "col0");
		data.addValue(4, "row0", "col1");
		data.addValue(5, "row0", "col2");
		double expected = 3 + 4 + 5; //hand calculated to 12
		
		try {
		double result = DataUtilities.calculateRowTotal(data, -1);
		System.out.println("testCalculateRowTotalMultipleColumns: expected value is 12 actual value is: ");
		System.out.println(result);
		}
		catch(Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testCalculateRowTotalRowTooLarge() {
		DefaultKeyedValues2D data = new DefaultKeyedValues2D();
		data.addValue(3, "row0", "col0");
		data.addValue(4, "row0", "col1");
		data.addValue(5, "row0", "col2");
		double expected = 3 + 4 + 5; //hand calculated to 12
		
		try {
		double result = DataUtilities.calculateRowTotal(data, 1);
		System.out.println("testCalculateRowTotalRowTooLarge: expected to fail but it did not");
		System.out.println(result);
		}
		catch(Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testCreateNumberArrayNull() {
		//null not permitted
		try {
		
			Number[] data = DataUtilities.createNumberArray(null);
			fail("the data array was created when an error should have been thrown");
		}
		catch(Exception e) {
			assertTrue(true);
		}
	
		
	}
	
	@Test
	public void testCreateNumberArrayEmpty() {
		double[] emptyArray = {};
		try {
			Number[] data = DataUtilities.createNumberArray(emptyArray);
			int len = data.length;
			//make sure number array has length of 0
			assertEquals(0,len, 0.0001d);
		}
		catch (Exception e) {
			//array creation failed
			assertTrue(false);
		}
		
		
	}
	
	@Test
	public void testCreateNumberArraySingleValue() {
		double[] singleValueArray = {1};
		try {
			Number[] data = DataUtilities.createNumberArray(singleValueArray);
			int len = data.length;
			//make sure number array has length of 1
			assertEquals(1, len, 0.0001d);
		}
		catch (Exception e) {
			//array creation failed
			assertTrue(false);
		}
	}
	
	@Test
	public void testCreateNumberArrayMultipleValues() {
		double[] singleValueArray = {1, 2 , 3};
		try {
			Number[] data = DataUtilities.createNumberArray(singleValueArray);
			int len = data.length;
			//make sure that number array has length of 3
			assertEquals(3, len, 0.0001d);
		}
		catch (Exception e) {
			//array creation failed
			assertTrue(false);
		}
	}



}
