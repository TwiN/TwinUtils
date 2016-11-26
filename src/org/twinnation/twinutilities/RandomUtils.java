package org.twinnation.twinutilities;


public class RandomUtils {
	
	private RandomUtils() {}
	
	/**
	 * Methode qui genere un char aleatoire minuscule
	 * @return lettre minuscule
	 */
	public static char randomLowercaseLetter() {
		return (char)((int)'a'+Math.random()*((int)'z'-(int)'a'+1));
	}
	
	
	/**
	 * Methode qui genere un char aleatoire majuscule
	 * @return lettre majuscule
	 */
	public static char randomUppercaseLetter() {
		return (char)((int)'A'+Math.random()*((int)'Z'-(int)'A'+1));
	}
	
	
	/**
	 * Methode qui genere un char aleatoire
	 * @return lettre
	 */
	public static char randomLetter() {
		char[] c = {randomLowercaseLetter(), randomUppercaseLetter()};
		return c[(int)(Math.random()+0.5)];
	}
	
	
	/**
	 * Methode qui genere un nombre entier entre min et max.
	 * @param min Nombre entier minimal
	 * @param max Nombre entier maximal
	 * @return int
	 */
	public static int randomInteger(int min, int max) {
		return (int)((Math.random()*(max-min+1)+min));
	}
	
}
