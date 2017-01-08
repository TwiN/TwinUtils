package org.twinnation.twinutilities;

import java.security.SecureRandom;

/**
 * Utility class with multiple methods related to the generation of 
 * random elements
 */
public final class RandomUtils {
	
	/** Prevents instantiation of this utility class */
	private RandomUtils() {}
	
	
	/**
	 * Generates a random lowercase character
	 * @return Random lowercase char
	 */
	public static char randomLowercaseLetter() {
		SecureRandom random = new SecureRandom();
		return (char)((int)'a'+random.nextInt(('z'-'a')+1));
	}
	
	
	/**
	 * Generates a random uppercase character
	 * @return Random uppercase char
	 */
	public static char randomUppercaseLetter() {
		SecureRandom random = new SecureRandom();
		return (char)((int)'A'+random.nextInt(('Z'-'A')+1));
	}
	
	
	/**
	 * Generates a random character
	 * @return Random char
	 */
	public static char randomLetter() {
		SecureRandom random = new SecureRandom();
		return random.nextBoolean() ?
				randomLowercaseLetter() : randomUppercaseLetter();
	}
	
	
	/**
	 * Generates a random integer between min and max
	 * @param min Minimum value
	 * @param max Maximum value
	 * @return Random integer
	 */
	public static int randomInteger(int min, int max) {
		SecureRandom random = new SecureRandom();
		return (min+random.nextInt(max-min+1));
	}
	
	 
	/**
	 * Generates a random password
	 * TODO: add way to control which characters you want in the password (e.g. uppercase, lowercase, numbers, etc..)
	 * @param length How much characters to randomly generate
	 * @return Random password
	 */
	public static String generatePassword(int length) {
		SecureRandom random = new SecureRandom();
		String password = "";
		while (length --> 0) {
			password += (random.nextBoolean() ? 
					randomLetter() : ""+randomInteger(0, 9));
		}
		return password;
	}
}
