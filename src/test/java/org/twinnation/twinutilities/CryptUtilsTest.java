package org.twinnation.twinutilities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class CryptUtilsTest {
	
	@Test
	public void sha512() throws Exception {
		assertEquals("ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff",
			  CryptUtils.sha512("test"));
	}
	
	
	@Test
	public void sha256() throws Exception {
		assertEquals("9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08", CryptUtils.sha256("test"));
	}
	
	
	@Test
	public void sha512WithRandomSalt() throws Exception {
		assertNotSame("Not salted properly.", CryptUtils.sha512("password"), CryptUtils.sha512WithRandomSalt("password"));
		assertTrue("The hash should be salted.", CryptUtils.sha512WithRandomSalt("password").contains(":"));
		assertNotEquals("The salt should be randomized, so the hash should be different.",
			  CryptUtils.sha512WithRandomSalt("password"), CryptUtils.sha512WithRandomSalt("password"));
	}
	
	
	@Test
	public void sha256WithRandomSalt() throws Exception {
		assertNotSame("Not salted properly.", CryptUtils.sha256("password"), CryptUtils.sha256WithRandomSalt("password"));
		assertTrue("The hash should be salted", CryptUtils.sha256WithRandomSalt("password").contains(":"));
		assertNotEquals("The salt should be randomized, so the hash should be different",
			  CryptUtils.sha256WithRandomSalt("password"), CryptUtils.sha256WithRandomSalt("password"));
	}
	
	
	@Test
	public void validateSha512WithSalt() throws Exception {
		String hashedPassword = CryptUtils.sha512WithRandomSalt("password");
	    assertTrue(CryptUtils.validateSha512WithSalt("password", hashedPassword));
		assertFalse(CryptUtils.validateSha512WithSalt("bad guess", hashedPassword));
	}
	
	
	@Test
	public void validateSha256WithSalt() throws Exception {
		String hashedPassword = CryptUtils.sha256WithRandomSalt("password");
		assertTrue(CryptUtils.validateSha256WithSalt("password", hashedPassword));
		assertFalse(CryptUtils.validateSha256WithSalt("bad guess", hashedPassword));
	}
	
	
	@Test
	public void getSalt() throws Exception {
		assertNotEquals("It should be near impossible to have the same salt twice in a row",
			  CryptUtils.getSalt(), CryptUtils.getSalt());
	}
	
	
	@Test
	public void rot13() throws Exception {
		assertEquals("TwinUtils", CryptUtils.rot13("GjvaHgvyf"));
		assertEquals("GjvaHgvyf", CryptUtils.rot13("TwinUtils"));
		assertEquals("Non-alphabetic characters should not change.", "Twin_Utils", CryptUtils.rot13("Gjva_Hgvyf"));
	}
	
	
	@Test
	public void base64encode() throws Exception {
	}
	
	
	@Test
	public void base64decode() throws Exception {
	}
	
	
	@Test
	public void base64encodeString() throws Exception {
	}
	
	
	@Test
	public void base64decodeString() throws Exception {
	}
	
}