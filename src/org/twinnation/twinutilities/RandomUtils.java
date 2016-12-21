package org.twinnation.twinutilities;

import java.security.SecureRandom;

/**
 * Utility class with multiple methods related to the generation of 
 * random elements
 */
public class RandomUtils {
	
	/** Prevents instantiation of this utility class */
	private RandomUtils() {}
	
	
	/**
	 * Generates a random lowercase character
	 * @return Lowercase char
	 */
	public static char randomLowercaseLetter() {
		SecureRandom random = new SecureRandom();
		return (char)((int)'a'+random.nextInt(('z'-'a')+1));
	}
	
	
	/**
	 * Generates a random uppercase character
	 * @return Uppercase char
	 */
	public static char randomUppercaseLetter() {
		SecureRandom random = new SecureRandom();
		return (char)((int)'A'+random.nextInt(('Z'-'A')+1));
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
		SecureRandom random = new SecureRandom();
		return (min+random.nextInt(max-min+1));
	}
}
