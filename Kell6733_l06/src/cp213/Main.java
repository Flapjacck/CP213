package cp213;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    /**
     * @param args unused
     */
    public static void main(String[] args) {
	System.out.println("Test scannerTest");
	System.out.println();
	Scanner keyboard = new Scanner(System.in);
	int total = scannerTest(keyboard);
	keyboard.close();
	System.out.println("Total: " + total);
	System.out.println();

	System.out.print("Test stringPrinter");
	System.out.println();

	try {
	    String output = stringPrinter(5, "*");
	    System.out.println(output);
	    output = stringPrinter(-5, "*");
	    System.out.println(output);
	} catch (Exception e) {
	    System.out.println();
	    System.out.println("getMessage:");
	    System.out.println(e.getMessage());
	    System.out.println();
	    System.out.println("toString:");
	    System.out.println(e.toString());
	    System.out.println();
	    System.out.println("printStackTrace:");
	    e.printStackTrace();
	}
    }

    /**
     * Uses exceptions to capture bad input from a keyboard Scanner.
     *
     * @return The total of all the integers entered.
     */
    public static int scannerTest(final Scanner keyboard) {

	// your code here
	int ans = 0;

	while (true) {
	    System.out.println("Enter a valid integer (\"Quit\" to stop): ");

	    try {
		ans += keyboard.nextInt();
	    }

	    catch (InputMismatchException e) {
		System.out.println("Enter a valid integer!!!");

		if (String.valueOf(keyboard.next()).equalsIgnoreCase("Quit")) {
		    break;
		}
	    }

	}

	return ans;
    }

    /**
     * Repeats a string.
     *
     * @param n   Number of times to repeat a string.
     * @param str The string to print.
     * @return The repeated string.
     * @throws Exception If n is negative.
     */
    public static String stringPrinter(int n, String str) throws Exception {

	// your code here
	if (n < 0) {
	    throw new IllegalArgumentException("Enter a positive integer for n!!!");
	}

	StringBuilder ans = new StringBuilder();
	for (int i = 0; i < n; i++) {
	    ans.append(str);
	}

	return ans.toString();
    }

}
