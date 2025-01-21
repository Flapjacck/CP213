package cp213;

/**
 * @author Spencer Kelly, 169066733
 * @version 2025-01-05
 */
public class Strings {
    // Constants
    public static final String VOWELS = "aeiouAEIOU";

    /**
     * Determines if string is a "palindrome": a word, verse, or sentence (such as
     * "Able was I ere I saw Elba") that reads the same backward or forward. Ignores
     * case, spaces, digits, and punctuation in the string parameter s.
     *
     * @param string a string
     * @return true if string is a palindrome, false otherwise
     */
    public static boolean isPalindrome(final String string) {

	// your code here
	StringBuilder filtered = new StringBuilder();
	for (int i = 0; i < string.length(); i++) {
	    if (Character.isLetter(string.charAt(i))) {
		filtered.append(string.charAt(i));
	    }
	}
	String filteredString = filtered.toString();
	String reversed = new StringBuilder(filteredString).reverse().toString();
	

	return filteredString.equalsIgnoreCase(reversed);
    }

    /**
     * Determines if name is a valid Java variable name. Variables names must start
     * with a letter or an underscore, but cannot be an underscore alone. The rest
     * of the variable name may consist of letters, numbers and underscores.
     *
     * @param name a string to test as a Java variable name
     * @return true if name is a valid Java variable name, false otherwise
     */
    public static boolean isValid(final String name) {

	// your code here
	if (name == null || name.isEmpty() || name.equals("_")) {
	    return false;
	}
	if (!Character.isLetter(name.charAt(0)) && name.charAt(0) != '_') {
	    return false;
	}
	for (char c : name.toCharArray()) {
	    if (!Character.isLetterOrDigit(c) && c != '_') {
                return false;
	    }
	}
	    
	return true;
    }

    /**
     * Converts a word to Pig Latin. The conversion is:
     * <ul>
     * <li>if a word begins with a vowel, add "way" to the end of the word.</li>
     * <li>if the word begins with consonants, move the leading consonants to the
     * end of the word and add "ay" to the end of that. "y" is treated as a
     * consonant if it is the first character in the word, and as a vowel for
     * anywhere else in the word.</li>
     * </ul>
     * Preserve the case of the word - i.e. if the first character of word is
     * upper-case, then the new first character should also be upper case.
     *
     * @param word The string to convert to Pig Latin
     * @return the Pig Latin version of word
     */
    public static String pigLatin(String word) {

	// your code here
	if (word == null || word.isEmpty()) {
            return word;
        }

        boolean isFirstUpper = Character.isUpperCase(word.charAt(0));
        word = word.toLowerCase();

        if (VOWELS.indexOf(word.charAt(0)) != -1) {
            word = word + "way";
        } else {
            int vowelIndex = -1;
            for (int i = 0; i < word.length(); i++) {
                if (VOWELS.indexOf(word.charAt(i)) != -1) {
                    vowelIndex = i;
                    break;
                }
            }
            if (vowelIndex == -1) {
                word = word + "ay";
            } else {
                word = word.substring(vowelIndex) + word.substring(0, vowelIndex) + "ay";
            }
        }

        if (isFirstUpper) {
            word = Character.toUpperCase(word.charAt(0)) + word.substring(1);
        }

        return word;
    }

}
