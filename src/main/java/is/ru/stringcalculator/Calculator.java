package is.ru.stringcalculator;

public class Calculator {

	public static int add(String text){
		/* Delimiter part: */
		if (text.startsWith("//")) {
			/* we have a delimiter */
			/* "//[delimiter]\n[numbers]" */
			String delimiter = text.substring(2, text.indexOf("\n"));
			text = text.substring(text.indexOf("\n")+1, text.length());
			
			return sum(splitNumbersWithDelimiter(text, delimiter));	
		}


		/* Numbers part: */
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
      
    private static int sum(String[] numbers){
 	    int total = 0;
        for(String number : numbers){
		    total += toInt(number);
		}
		return total;
    }

    private static void errorMsg(String text) {
    	System.out.println("Error: " + text);
    }


}