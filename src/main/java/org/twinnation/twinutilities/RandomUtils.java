package org.twinnation.twinutilities;

import java.security.SecureRandom;

/**
 * Utility class with multiple methods related to the generation of 
 * random elements
 */
public interface RandomUtils {
	
	/**
	 * Generates a random lowercase character
	 * @return Random lowercase char
	 */
	static char randomLowercaseLetter() {
		return (char)((int)'a'+(new SecureRandom()).nextInt(('z'-'a')+1));
	}
	
	
	/**
	 * Generates a random uppercase character
	 * @return Random uppercase char
	 */
	static char randomUppercaseLetter() {
		return (char)((int)'A'+(new SecureRandom()).nextInt(('Z'-'A')+1));
	}
	
	
	/**
	 * Generates a random character
	 * @return Random char
	 */
	static char randomLetter() {
		return (new SecureRandom()).nextBoolean() ?
				randomLowercaseLetter() : randomUppercaseLetter();
	}
	
	
	/**
	 * Generates a random integer between min and max
	 * @param min Minimum value
	 * @param max Maximum value
	 * @return Random integer
	 */
	static int randomInteger(int min, int max) {
		return (min+(new SecureRandom()).nextInt(max-min+1));
	}
	
	
	/**
	 * Generates a random password
	 * TODO: add way to control which characters you want in the password (e.g. uppercase, lowercase, numbers, etc..)
	 * @param length How much characters to randomly generate
	 * @return Random password
	 */
	static String generatePassword(int length) {
		SecureRandom random = new SecureRandom();
		String password = "";
		while (length --> 0) {
			password += (random.nextBoolean() ? 
					randomLetter() : ""+randomInteger(0, 9));
		}
		return password;
	}
	
}
