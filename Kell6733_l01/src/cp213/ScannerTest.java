package cp213;

import java.util.Scanner;

/**
 * Class to demonstrate the use of Scanner with a keyboard and File objects.
 *
 * @author Spencer Kelly
 * @version 2025-01-12
 */
public class ScannerTest {

    /**
     * Count lines in the scanned file.
     *
     * @param source Scanner to process
     * @return number of lines in scanned file
     */
    public static int countLines(final Scanner source) {
	int count = 0;

	// your code here
	// loop through the lines of the file
	while (source.hasNextLine()) {
	    // move to next line
	    source.nextLine();
	    // count the line
	    count++;
	}

	return count;
    }

    /**
     * Count tokens in the scanned object.
     *
     * @param source Scanner to process
     * @return number of tokens in scanned object
     */
    public static int countTokens(final Scanner source) {
	int tokens = 0;

	// your code here
	// loop through the tokens of the file
	while (source.hasNext()) {
	    // move to next token
	    source.next();
	    // count the token
	    tokens++;
	}

	return tokens;
    }

    /**
     * Ask for and total integers from the keyboard.
     *
     * @param source Scanner to process
     * @return total of positive integers entered from keyboard
     */
    public static int readNumbers(final Scanner source) {
	int total = 0;

	// your code here
	// Prompt the user
	System.out.println("Enter a integer or q to quit:");
	
	// loop through the integers of the file
	while (true) {
            // Check if there is an integer input available
            if (source.hasNextInt()) {
                int number = source.nextInt(); // Read the integer
                total += number; // Add the integer to the total
            } else {
                String input = source.next(); // Read the next token as a string

                // Check if the input is 'q'
                if (input.equalsIgnoreCase("q")) {
                    break;
                } else {
                    // If input is not an integer or 'q', display an error message
                    System.out.println("'" + input + "' is not an integer or 'q'.");
                }
            }
        }

	// Return the total of all valid integers
        return total;

    }
}
