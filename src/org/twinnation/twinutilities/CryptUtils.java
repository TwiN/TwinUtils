package org.twinnation.twinutilities;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Cryptography-related utility class
 */
public final class CryptUtils {
	
	/** Prevents instantiation of this utility class */
	private CryptUtils() {}
	
	
	/**
	 * Encodes a byte array using base64
	 * @param b Byte array
	 * @return Base64 encoded string
	 */
	public static String base64encode(byte[] b) {
		return Base64.getEncoder().encodeToString(b);
	}
	
	
	/**
	 * Encodes a String using base64
	 * @param s String to encode
	 * @return Base64 encoded string
	 */
	public static String base64encode(String s) {
		return Base64.getEncoder().encodeToString(s.getBytes());
	}
	
	
	/**
	 * Decodes a base64 encoded byte array
	 * @param b Byte array 
	 * @return Decoded String
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
	 * Decodes a base64 encoded String
	 * @param s Base64 encoded String
	 * @return Decoded String
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
	 * Uses the SHA512 algorithm to create a salted hash for the given password 
	 * @param password Password
	 * @param salt Salt
	 * @return PasswordHash:PasswordSalt
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
	 * Generates a secure salt
	 * @return Random salt
	 */
	public static String getSalt() {
		byte[] salt = new byte[24];
		SecureRandom sr = new SecureRandom();
		sr.nextBytes(salt);
		return base64encode(salt);
	}
	
	
	/**
	 * Validates a password generated with CryptUtils.sha512Salted
	 * @param password Password
	 * @param hashSalt PasswordHash:PasswordSalt
	 * @return Whether the password matches or not
	 */
	public static boolean validateSha512Salted(String password, String hashSalt) {
		String salt = hashSalt.split(":")[1];
		return sha512Salted(password, salt).equalsIgnoreCase(hashSalt);
	}
	
	
	/**
	 * Rotates the alphabetic characters in a string by 13 positions
	 * @param s String to encrypt with rot13
	 * @return Encrypted String
	 */
	public static String rot13(String s) {
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if ((c >= 'a' && c <= 'm') || (c >= 'A' && c <= 'M')) {
				c += 13;
			} else if ((c >= 'n' && c <= 'z') || (c >= 'N' && c <= 'Z')) {
				c -= 13;
			}
			s = s.substring(0, i) + c + s.substring(i+1);
		}
		return s;
	}
	
}
