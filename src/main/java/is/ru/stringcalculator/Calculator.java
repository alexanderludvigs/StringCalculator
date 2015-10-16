package is.ru.stringcalculator;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Arrays;
import java.util.ArrayList;

public class Calculator {

	public static int add(String text) throws Exception {
		/* if optional Delimiter is present */
		String delimiter = "";

		/* Check for optional delimiter */
		if (text.startsWith("//")) {
			/* Check for delimiter longer than 1 char */
			if (text.startsWith("//[")) {

				/* get all delimiters of the string , we want 
				   everything between brackets [ .. ] */
				Matcher m = Pattern.compile("\\[(.*?)\\]").matcher(text);
		        while (m.find()) {
		        	delimiter = delimiter + m.group();
		        }
		        /* gives us [delimiter], so we have to get rid of brackets */
		        delimiter = delimiter.replace("[", "").replace("]", "");

		   		/* the "splitter" we want to return */
		        delimiter = "[" + delimiter + "]";

				/* get numbers part of string */
				text = text.substring(text.indexOf("\n")+1, text.length());

				return sum(splitNumbersWithDelimiter(text, delimiter));	
			}
			delimiter = text.substring(2, text.indexOf("\n"));
			text = text.substring(text.indexOf("\n")+1, text.length());

			return sum(splitNumbersWithDelimiter(text, delimiter));	
		}

		/* if NO optional Delimiter */

		if (text.contains("\n")) {
			if (text.endsWith("\n") || text.startsWith("\n")) {
				errorMsg("invalid input");
				return -1;
			} 

			/* Replace "\n" with "," */
			text = text.replace("\n", ",");
		} 

		if(text.equals("")){
			return 0;
		}
		else if(text.contains(",")){

			/* Check for negative integers */
			if (containsNegatives(text) == true) {
				handlingNegatives(text);
			}

			return sum(splitNumbers(text));
		}
		else
			return 1;
	}

	private static int toInt(String number){
		return Integer.parseInt(number);
	}

	private static String[] splitNumbers(String numbers){
	    return numbers.split(",");
	}

	private static String[] splitNumbersWithDelimiter(String numbers, String delimiter ) {
	    return numbers.split(delimiter);
	}
    
    public static boolean isNumeric(String str) {  
    	try {  
			double d = Double.parseDouble(str);  
		} catch(NumberFormatException nfe) {  
			return false;  
		}  
		return true;  
    }  

    private static int sum(String[] numbers){
 	    ArrayList<String> temp = new ArrayList<String>(); 
		/* we only want numbers */	
		for (String item : numbers) {
			if (isNumeric(item)) {
				temp.add(item);
			}
		}

 	    int total = 0;
        for(String number : temp) {
        	/* we only do numbers below 1000 */
		    if (toInt(number) <= 1000) {
		    	total += toInt(number);
		    }
		   
		}
		return total;
    }

    private static boolean containsNegatives(String numbers) {

    	for (String item : splitNumbers(numbers)) {
    		if (toInt(item) < 0) {
    			return true; 
    		}
    	}
    	return false;
    }

    private static void handlingNegatives(String numbers) throws Exception {

		String toReturn = "Negatives not allowed: ";

		for (String item : splitNumbers(numbers)) {
			if (toInt(item) < 0) {
				toReturn = toReturn + item + ",";
			}
		}
		/* remove last comma from string */
		toReturn = toReturn.substring(0, toReturn.length()-1);
		/* Throw final negative msg */
		throw new Exception(toReturn);
    }

    private static void errorMsg(String text) {
    	System.out.println("Error: " + text);
    }


}