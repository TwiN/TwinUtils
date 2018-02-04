package org.twinnation.twinutilities;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;


public class SearchUtilsTest {
	
	@Test
	public void getBetween() throws Exception {
		assertEquals("data", SearchUtils.getBetween("<div>data</div>", "<div>", "</div>"));
		assertEquals("div", SearchUtils.getBetween("<div>data</div>", "<", ">"));
		assertEquals("", SearchUtils.getBetween("<div>data</div>", "</div>", "<div>"));
	}
	
	
	@Test
	public void isInList() throws Exception {
		assertTrue(SearchUtils.isInList(Arrays.asList("this", "is", "a", "test").toArray(), "test"));
		assertFalse(SearchUtils.isInList(Arrays.asList("this", "is", "a", "test").toArray(), "bad element"));
	}
	
	
	@Test
	public void isInString() throws Exception {
		assertTrue(SearchUtils.isInString("I love plants", "plants"));
		assertTrue(SearchUtils.isInString("I love plants", "ant"));
		assertTrue(SearchUtils.isInString("I love plants", "love"));
		assertTrue(SearchUtils.isInString("I love plants", "I"));
		assertFalse(SearchUtils.isInString("I love plants", "what"));
		assertFalse(SearchUtils.isInString("I love plants", "PLANTS"));
		assertFalse(SearchUtils.isInString("I love plants", "i"));
	}
	
	
	@Test
	public void isNullOrEmpty() throws Exception {
		assertTrue(SearchUtils.isNullOrEmpty(null));
		assertTrue(SearchUtils.isNullOrEmpty(""));
		assertFalse(SearchUtils.isNullOrEmpty(1));
		assertFalse(SearchUtils.isNullOrEmpty("null"));
	}
	
	
	@Test
	public void isPalindrome() throws Exception {
		assertTrue(SearchUtils.isPalindrome("kayak"));
		assertTrue(SearchUtils.isPalindrome("Kayak"));
		assertFalse(SearchUtils.isPalindrome("Kayak", true)); // taking the case in consideration
		assertFalse(SearchUtils.isPalindrome("test"));
		assertFalse(SearchUtils.isPalindrome("John Doe"));
	}
	
}