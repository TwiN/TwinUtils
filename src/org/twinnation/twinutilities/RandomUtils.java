package org.twinnation.twinutilities;


public class RandomUtils {
	
	/** Prevents instantiation of this utility class */
	private RandomUtils() {}
	
	
	/**
	 * Generates a random lowercase character
	 * @return Lowercase char
	 */
	public static char randomLowercaseLetter() {
		return (char)((int)'a'+Math.random()*((int)'z'-(int)'a'+1));
	}
	
	
	/**
	 * Generates a random uppercase character
	 * @return Uppercase char
	 */
	public static char randomUppercaseLetter() {
		return (char)((int)'A'+Math.random()*((int)'Z'-(int)'A'+1));
	}
	
	
	/**
	 * Generates a random character
	 * @return char
	 */
	public static char randomLetter() {
		char[] c = {randomLowercaseLetter(), randomUppercaseLetter()};
		return c[(int)(Math.random()+0.5)];
	}
	
	
	/**
	 * Generates a random integer between min and max
	 * @param min Minimum value
	 * @param max Maximum value
	 * @return Integer
	 */
	public static int randomInteger(int min, int max) {
		return (int)((Math.random()*(max-min+1)+min));
	}
	
}
