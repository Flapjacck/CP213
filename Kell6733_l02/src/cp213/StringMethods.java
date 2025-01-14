package cp213;

/**
 * Sample string methods.
 *
 * @author Spencer Kelly, 169066733, Kell6733@mylaurier.ca
 * @version 2022-05-06
 */
public class StringMethods {
    // Constants
    /**
     * String of vowels.
     */
    public static final String VOWELS = "aeiouAEIOU";

    /**
     * Counts the number of vowels in a string. Does not include 'y'.
     *
     * @param string A string.
     * @return Number of vowels in string.
     */
    public static int vowelCount(final String string) {
	int count = 0;

	// your code here
	// iterate through the string
	for (int i = 0; i < string.length(); i++) {
	    // if the character at index i is a vowel
	    if (VOWELS.indexOf(string.charAt(i)) != -1) {
		// increment the count
		count++;
	    }
	}
	
	// return the count
	return count;
    }

    /**
     * Counts the number of digits in a string.
     *
     * @param string A string.
     * @return Number of digits in string.
     */
    public static int digitCount(final String string) {
	int count = 0;

	// your code here
	// iterate through the string
	for (int i = 0; i < string.length(); i++) {
	    // if the character at index i is a digit
	    if (Character.isDigit(string.charAt(i))) {
		// increment the count
		count++;
	    }
	}
		
		// return the count
		return count;
    }

    /**
     * Totals the individual digits in a string.
     *
     * @param string A string.
     * @return The integer total of all individuals digits in string.
     */
    public static int totalDigits(final String string) {
	int total = 0;

	// your code here
	// iterate through the string
	for (int i = 0; i < string.length(); i++) {
	    // if the character at index i is a digit
	    if (Character.isDigit(string.charAt(i))) {
		// add the value of the digit to the total
		total += Character.getNumericValue(string.charAt(i));
	    }
	}
	

	return total;
    }

    /**
     * Compares string1 and string2 and returns a comma-delimited concatenated
     * string consisting of the string that is first lexically followed by the
     * string that is second lexically. If the strings are equal, then only string1
     * is returned.
     *
     * @param string1 String to compare against string2.
     * @param string2 String to compare against string1.
     * @return A concatenation of string1 and string2 in order.
     */
    public static String pairs(String string1, String string2) {
	String line = null;

	// your code here
	// compare the strings
	int compare = string1.compareTo(string2);
	
	
	if (compare < 0) {  // compare is negative if string1 is lexically before string2
	    // concatenate string1 and string2
	    line = string1 + "," + string2;
	} else if (compare > 0) {  // compare is positive if string2 is lexically before string
	    // concatenate string2 and string1
	    line = string2 + "," + string1;
	} else {
	    // if the strings are equal, return string1
	    line = string1;
	}
	

	return line;
    }

    /**
     * Finds the distance between the s1 and s2. The distance between two strings of
     * the same length is the number of positions in the strings at which their
     * characters are different. If two strings are not the same length, the
     * distance is unknown (-1). If the distance is zero, then two strings are
     * equal.
     *
     * @param string1 String to compare against string2.
     * @param string2 String to compare against string1.
     * @return The distance between string1 and string2.
     */
    public static int stringDistance(String string1, String string2) {
	int distance = 0;

	// your code here
	// if the strings are not the same length
	if (string1.length() != string2.length()) {
        distance = -1;
    } else {
	// iterate through the strings
        for (int i = 0; i < string1.length(); i++) {
            // if the characters at index i are different
            if (string1.charAt(i) != string2.charAt(i)) {
        	// increment the distance
        	distance++;
	    
            }
        }
    }
	return distance;
    }
}
