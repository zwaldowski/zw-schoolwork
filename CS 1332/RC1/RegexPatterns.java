/**
 * 
 * Regex assignment.
 * 
 * Fill out the empty string ("") inside each method with the correct regular expression.
 * Use the specifications in the comments. If you need help, ask a TA! :)
 * 
 * @author Naomi Robert
 *
 */

public class RegexPatterns {
	
	/**
	 * Match a name.
	 * 
	 * Provide a regex pattern that validates a person's first and last name. 
	 * Each name should be capitalized and should not contain any punctuation 
	 * characters except a "-" or " ". You should limit the pattern to only two names.
	 * 
	 * Notes: 
	 * 	- Hyphenated names (up to two names) counts as one name (see case 2)
	 * 	- Names may begin with 1 or 2 capital letters, but cannot contain an 
	 * 		apostrophe (see cases 3 and 4).
	 * 
	 *
	 * @param name the name
	 * @return true, if successful
	 */
	public static boolean testName(String name){
		// Any capital letter followed by one or more letter or hyphen
		// Immediately followed by a space and a capital letter
		// After that, also match a the second word: a space followed by the first line ^^
		return name.matches("([A-Z][A-Za-z-]+(?=\\s[A-Z])(?:\\s[A-Z][A-Za-z-]+))");
	}
	
	/**
	 * Match a phone number.
	 * 
	 * Provide a Regex pattern that matches Atlanta phone numbers. The format 
	 * should be "(###)###-####" and the only valid area codes are 404,678 and
	 * 770.
	 *
	 * @param phoneNumber the phone number
	 * @return true, if successful
	 */
	public static boolean testPhoneNumber(String phoneNumber){
		// Start capture group
		// Starts with (, matches 404, 678, 770, ends with )
		// Immediately followed by 3 digits, a dash, and 4 digits
		// End capture group
		return phoneNumber.matches("(\\((404|678|770)\\)([0-9]{3})-([0-9]{4}))");
	}
	
	/**
	 * Match an email.
	 * 
	 * Provide a Regex pattern that validates an email. The email should be
	 * broken up into the following sections:
	 * 
	 * 1) any word character, digit, underscore, dash and period combination,
	 *    but must start with a letter
	 * 2) the @ symbol
	 * 3) any word character, digit, underscore and dash combination, but must
	 *    start with a letter
	 * 4) a single period
	 * 5) valid emails must end in com, org or net
	 *
	 * @param email the email
	 * @return true, if successful
	 */
	public static boolean testEmail(String email){
		return email.matches("(^[A-Za-z][\\w-(.|\\n)]+@[A-Za-z][\\w-(.|\\n)]+(.|\\n)(com|net|org))");
	}
	
	/**
	 * Match an address.
	 * 
	 * Provide a regex pattern to validate a mailing address. The address should be of the form:
	 * 1) start with a number, 1-5 digits in length
	 * 2) whitespace
	 * 3) the street -  some combination of letters, numbers, spaces, period, and hyphens
	 * 4) break to a newline
	 * 5) the city - some combination of letters, spaces, period, and hyphens, starting with a capital letter
	 * 6) optional comma
	 * 7) whitespace
	 * 8) the state - 2 capital characters
	 * 9) whitespace
	 * 10) the zipcode - 5 digits
	 * 
	 * NOTE: The state characters can be any 2 capital letters, they do not need to
	 * actually match a real state.
	 *
	 * @param address the address
	 * @return true, if successful
	 */
	public static boolean testAddress(String address){
		return address.matches("(^\\d{1,5} [A-Za-z0-9 .-]+\\n[A-Z][A-Za-z .-]+(,?) [A-Z]{2} \\d{5}(?!-\\d*))");
	}
	
	/**
	 * Match a java executable.
	 * 
	 * Provide a regex pattern that will check a string of text (presumably
	 * from a .java file, but that is not important here) to see if it contains
	 * "public static void main(String[] args)".
	 *
	 * @param java the Java string
	 * @return true, if successful
	 */
	public static boolean testJavaExecutable(String java){
		return java.matches("^(.|\\n)*((public static void main)\\(String\\[\\] args\\))(.|\\n)*$");
	}

}
