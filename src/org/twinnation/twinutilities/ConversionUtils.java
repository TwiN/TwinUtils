package org.twinnation.twinutilities;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;


public final class ConversionUtils {
	
	private ConversionUtils() {}
	
	/**
	 * Methode qui transforme une liste de char en String
	 * @return str
	 */
	public static String chars2String(char[] c) {
		String str = "";
		int x = 0;
		while (x <= c.length-1) {
			str += ""+c[x++];
		}
		return str;
	}
	
	
	/**
	 * Methode qui converti une chaine de caractere en element de type Document.
	 * @param contents Chaine de caractere a convertir
	 * @return document normalisee
	 */
	public static Document string2Document(String contents) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Document document = null;
		try {
			builder = factory.newDocumentBuilder();
			document = builder.parse(new InputSource(new StringReader(contents)));
			document.getDocumentElement().normalize();
		} catch (Exception e) {
			System.out.println("Unable to convert string to Document: "
					+ e.getMessage());
		}
		return document;
	}
	
	
	/**
	 * Methode qui converti une liste en String avec un separateur entre chaque
	 * element de la liste.
	 * @param separator Separateur
	 * @param array Liste
	 * @return String avec un separateur entre chaque element de la liste
	 */
	public static String implode(String separator, String[] array) {
		if (array == null) {
			return "";
		}
		String str = "";
		for (int i = 0; i<array.length; i++) {
			str += array[i]+""+(array.length-1==i?"":separator);
		}
		return str;
	}
}
