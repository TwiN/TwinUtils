package org.twinnation.twinutilities;

/**
 * Search-related utility class
 */
public class SearchUtils {
	
	/**
	 * Prevents instantiation
	 */
	private SearchUtils() {}
	
	
	/**
	 * Gets the string between two strings
	 */
	public static String getBetween(String source, String start, String end) {
		try {
			String sourceStartingFromStart = source.substring(source.indexOf(start) + start.length());
			return sourceStartingFromStart.substring(0, sourceStartingFromStart.indexOf(end));
		} catch (Exception e) {
			return "";
		}
	}
	
	
	/**
	 * Checks if the object <i>needle</i> is in the array <i>haystack</i>
	 * NOTE: 1.0 (double) != 1 (int)...
	 * @param haystack Array to search
	 * @param needle Element to find
	 * @return Whether the <i>needle</i> is in the <i>haystack</i> or not
	 */
	public static boolean isInList(Object[] haystack, Object needle) {
		for(Object o : haystack) {
			if (o.equals(needle)) { return true; }
		}
		return false;
	}
	
	
	/**
	 * Checks if the String <i>needle</i> is in the String <i>haystack</i>
	 * @param haystack String to search
	 * @param needle String to find
	 * @return Whether the <i>needle</i> is in the <i>haystack</i> or not
	 */
	public static boolean isInString(String haystack, String needle) {
		return (haystack.indexOf(needle) >= 0);
	}
	
	
	/**
	 * Checks if an object is null
	 * @param o Object
	 * @return Whether the Object <i>o</i> is null or not
	 */
	public static boolean isNull(Object o) {
		return (o == null || (o+"").equalsIgnoreCase("null"));
	}
	
	
	/**
	 * Checks if the given String is a palindrome.
	 * @param s String to check
	 * @return Whether the String <i>s</i> is a palindrome or not
	 */
	public static boolean isPalindrome(String s) {
		for (int i = 0; i < s.length()/2; i++) {
			if (s.charAt(i) != s.charAt(s.length()-1-i)) {
				return false;
			}
		}
		return true;
	}
	
}
