package org.twinnation.twinutilities;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;


public final class CryptUtils {
	
	private CryptUtils() {}
	
	/**
	 * Methode qui encode une une liste de byte
	 * @param b Liste de byte
	 * @return String encode en base 64
	 */
	public static String base64encode(byte[] b) {
		return Base64.getEncoder().encodeToString(b);
	}
	
	
	/**
	 * Encode une String en base64
	 * @param s String
	 * @return String encode en base 64
	 */
	public static String base64encode(String s) {
		return Base64.getEncoder().encodeToString(s.getBytes());
	}
	
	
	/**
	 * Methode qui decode une liste de byte encode en base 64
	 * @param b Liste de byte
	 * @return String decode
	 */
	public static String base64decode(byte[] b) {
		String decodedString = "";
		try {
			decodedString = new String(Base64.getDecoder().decode(b), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decodedString;
	}
	
	
	/**
	 * Methode qui decode une String encode en base 64
	 * @param s String encode en base64
	 * @return String decode
	 */
	public static String base64decode(String s) {
		String decodedString = "";
		try {
			decodedString = new String(Base64.getDecoder().decode(s.getBytes()), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decodedString;
	}
	
	
	/**
	 * Methode qui retourne le hash d'un mot de passe claire en utilisant
	 * l'algorythm SHA-512 ainsi qu'un salt.
	 * @param password Mot de passe
	 * @param salt Salt
	 * @return hashSalt
	 */
	public static String sha512Salted(String password, String salt) {
		StringBuilder sb = new StringBuilder();
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-512");
			md.update(salt.getBytes("UTF-8"));
			byte[] bytes = md.digest(password.getBytes("UTF-8"));
			for (int i = 0; i<bytes.length; i++){
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sb.toString()+":"+salt;
	}
	
	
	/**
	 * Methode qui genere un salt aleatoire
	 * @return salt aleatoire
	 */
	public static String getSalt() {
		byte[] salt = new byte[24];
		SecureRandom sr = new SecureRandom();
		sr.nextBytes(salt);
		return base64encode(salt);
	}
	
	
	/**
	 * Methode qui valide un mot de passe genere par sha512Salted
	 * @param password Mot de passe
	 * @param hashSalt hashSalt
	 * @return vrai si le mot de passe concorde avec le hashSalt
	 */
	public static boolean validateSha512Salted(String password, String hashSalt) {
		String salt = hashSalt.split(":")[1];
		return sha512Salted(password, salt).equalsIgnoreCase(hashSalt);
	}

}
