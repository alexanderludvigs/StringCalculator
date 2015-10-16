package is.ru.stringcalculator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Arrays;

public class CalculatorTest {

	public static void main(String args[]) {
      org.junit.runner.JUnitCore.main("is.ru.stringcalculator.CalculatorTest");
    }

	@Test
	public void testEmptyString() throws Exception {
		assertEquals(0, Calculator.add(""));
	}

	@Test
	public void testOneNumber() throws Exception {
		assertEquals(1, Calculator.add("1"));
	}

	@Test
	public void testTwoNumbers() throws Exception{
		assertEquals(3, Calculator.add("1,2"));
	}	

	@Test
    public void testMultipleNumbers() throws Exception {
    	assertEquals(6, Calculator.add("1,2,3"));
    }

    @Test
    public void testNewLinesInAString() throws Exception {
    	assertEquals(6, Calculator.add("1\n2,3"));
    }

    @Test
    public void testStringEndsWithNewLine() throws Exception {
    	assertEquals(-1, Calculator.add("1,\n"));
    }

    @Test
    public void testStringStartsWithNewLine() throws Exception {
    	assertEquals(-1, Calculator.add("\n,2,3"));
    }

    @Test 
    public void testDifferentDelimiter() throws Exception {
    	assertEquals(3, Calculator.add("//;\n1;2"));
    }

    @Test 
    public void testNegativeNumbersException() throws Exception {
    	try {
    		Calculator.add("-1,2");
    	} catch (Exception e) {
    		String expectedMsg = "Negatives not allowed: -1";
    		assertEquals(expectedMsg, e.getMessage());
    	}
    }

	@Test 
    public void testNegativeNumbersException2() throws Exception {
    	try {
    		Calculator.add("2,-4,3,-5");
    	} catch (Exception e) {
    		String expectedMsg = "Negatives not allowed: -4,-5";
    		assertEquals(expectedMsg, e.getMessage());
    	}
    }    

    @Test 
    public void testNumbersBiggerthan1000() throws Exception {
    	assertEquals(2, Calculator.add("1001,2"));
    }

    @Test 
    public void testLongerDelimiter() throws Exception {
    	assertEquals(6, Calculator.add("//[***]\n1***2***3"));
    }

    @Test
    public void testMultiDelimiters() throws Exception {
    	assertEquals(6, Calculator.add("//[*][%]\n1*2%3"));
    }

    @Test
    public void testMultiDelimitersWithAnyLength() throws Exception {
    	assertEquals(10, Calculator.add("//[***][%%][$]\n1***2%%3$4"));
    }

    @Test
    public void testMultiDelimitersWithAnyLength2() throws Exception {
    	assertEquals(11, Calculator.add("//[$][%%][***][#]\n1#1***2%%3$4"));
    }

}