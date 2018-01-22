package org.twinnation.twinutilities;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Cryptography-related utility class
 */
public interface CryptUtils {
	
	/**
	 * Encodes a byte array using base64
	 * @param b Byte array
	 * @return Base64 encoded string
	 */
	static String base64encode(byte[] b) {
		return Base64.getEncoder().encodeToString(b);
	}
	
	
	/**
	 * Encodes a String using base64
	 * @param s String to encode
	 * @return Base64 encoded string
	 */
	static String base64encode(String s) {
		return Base64.getEncoder().encodeToString(s.getBytes());
	}
	
	
	/**
	 * Decodes a base64 encoded byte array
	 * @param b Byte array 
	 * @return Decoded String
	 */
	static String base64decode(byte[] b) {
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
	static String base64decode(String s) {
		String decodedString = "";
		try {
			decodedString = new String(Base64.getDecoder().decode(s.getBytes()), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decodedString;
	}
	
	
	static String sha(int bits, String s) {
		String result = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-" + bits);
			byte[] passwordBytesArray = digest.digest(s.getBytes("UTF-8"));
			result = new BigInteger(1, passwordBytesArray).toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	static String sha512(String s) {
		return sha(512, s);
	}
	
	
	static String sha256(String s) {
		return sha(256, s);
	}
	
	
	/**
	 * Uses the SHA512 algorithm to create a salted hash for the given password 
	 * @param password Password
	 * @param salt Salt
	 * @return PasswordHash:PasswordSalt
	 */
	static String sha512Salted(String password, String salt) {
		return sha512(password) + ":" + salt;
	}
	
	
	/**
	 * Generates a secure salt
	 * @return Random salt
	 */
	static String getSalt() {
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
	static boolean validateSha512Salted(String password, String hashSalt) {
		String salt = hashSalt.split(":")[1];
		return sha512Salted(password, salt).equalsIgnoreCase(hashSalt);
	}
	
	
	/**
	 * Rotates the alphabetic characters in a string by 13 positions
	 * @param s String to encrypt with rot13
	 * @return Encrypted String
	 */
	static String rot13(String s) {
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
