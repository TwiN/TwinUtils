package org.twinnation.twinutilities;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;


public final class ConversionUtils {
	
	/** Prevents instantiation of this utility class */
	private ConversionUtils() {}
	
	
	/**
	 * Converts a char array to a String
	 * @return String 
	 */
	public static String chars2String(char[] c) {
		String result = "";
		int x = 0;
		while (x <= c.length-1) { result += ""+c[x++]; }
		return result;
	}
	
	
	/**
	 * Converts a string to a Document
	 * @param contents String to convert
	 * @return Document object containing contents 
	 */
	public static Document string2Document(String contents) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Document result = null;
		try {
			builder = factory.newDocumentBuilder();
			result = builder.parse(new InputSource(new StringReader(contents)));
			result.getDocumentElement().normalize();
		} catch (Exception e) {
			System.out.println("Unable to convert string to Document: "
					+ e.getMessage());
		}
		return result;
	}
	
	
	/**
	 * Converts a String array into a string separated by the separator passed
	 * as parameter
	 * @param separator String to put between every element of the array
	 * @param array List to convert
	 * @return String of the array with a separator between each elements
	 */
	public static String implode(String separator, String[] array) {
		if (array == null) { return ""; }
		String result = "";
		for (int i = 0; i<array.length; i++) {
			result += array[i]+""+(array.length-1==i?"":separator);
		}
		return result;
	}
	
	
	/**
	 * Pads a number with 0 until it has the expected amount of digits. 
	 * If the numToPad has more than the amount of digit expected, 
	 * no change will be made to the String.
	 * @param digitExpected Number of digits expected
	 * @param numToPad Number to pad with 0s
	 * @return Padded number
	 */
	public static String zeroPad(int digitExpected, String numToPad) {
		if (numToPad.length()==digitExpected) { return numToPad; }
		String result = "";
		while ((digitExpected---numToPad.length()) > 0) { result += "0"; }
		return result+numToPad;
	}
	
	
	/**
	 * Removes extra decimals.
	 * @param decimalExpected Maximum number of decimal 
	 * @param num Number to test decimal
	 * @return number with <= decimalExpected
	 */
	public static double fixedDecimal(int decimalExpected, double num) {
		String[] fullNum = (""+(num)).split("\\.", 2);
		String decimal = zeroPad(decimalExpected, fullNum[1]).substring(0, decimalExpected);
		return Double.parseDouble(((int)num)+"."+decimal);
	}

	
	/**
	 * Convert special HTML characters into their HTML entity equivalent
	 * @param s String to sanitize
	 * @return Sanitized/safe string
	 */
	public static String htmlspecialchars(String s) {
		String result = "";
		for (int i = 0; i<s.length(); i++) {
			char c = s.charAt(i);
			switch(c) {
				case '<': result += "&lt;"; break;
				case '>': result += "&gt;"; break;
				case '&': result += "&amp;"; break;
				case '"': result += "&quot;"; break;
				case '\'': result += "&apos;"; break;
				default: result += c;
			} 
		}
		return result;
	}
}
