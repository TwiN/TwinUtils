package org.twinnation.twinutilities;

import org.junit.Test;

import static org.junit.Assert.*;


public class ConversionUtilsTest {
	
	@Test
	public void chars2String() throws Exception {
		char[] chars = {'T', 'w', 'i', 'n', 'U', 't', 'i', 'l', 's', ' ', 'R', 'o', 'c', 'k', 's', '!'};
		assertEquals("TwinUtils Rocks!", ConversionUtils.chars2String(chars));
	}
	
}