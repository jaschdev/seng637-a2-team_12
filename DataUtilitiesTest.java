package org.jfree.data.test;
import static org.junit.Assert.*;
import org.jfree.data.DataUtilities;
import org.junit.Test;

import org.jfree.data.KeyedValues;
import org.jmock.Expectations;
import org.jmock.Mockery;



public class DataUtilitiesTest {

    private static final double EPS = 0.000000001d;

    
    
    
    // Test suite for createNumberArray2D() method
    
    
    
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
        assertEquals(42.0, output[0][0].doubleValue(), EPS);
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

}
