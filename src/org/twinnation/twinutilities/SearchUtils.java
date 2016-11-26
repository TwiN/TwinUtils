package org.twinnation.twinutilities;


public class SearchUtils {
	
	private SearchUtils() {}
	
	/**
	 * Methode qui cherche un element dans une liste.
	 * NOTE: 1.0 (double) != 1 (int)...
	 * @param haystack Liste a chercher
	 * @param needle Element a trouver
	 * @return vrai si needle est dans haystack
	 */
	public static boolean isInList(Object[] haystack, Object needle) {
		for(Object o: haystack) {
			if (o.equals(needle)) { return true; }
		}
		return false;
	}
	
	
	/**
	 * Methode qui cherche une chaine de caractere dans une autre chaine de
	 * caracteres.
	 * @param haystack Mot a chercher
	 * @param needle Chaine de caracteres a trouver
	 * @return
	 */
	public static boolean isInString(String haystack, String needle) {
		return (haystack.indexOf(needle) >= 0);
	}
	
	
	/**
	 * Methode qui teste si un objet est null.
	 * @param o Object
	 * @return vrai si o est null
	 */
	public static boolean isNull(Object o) {
		if (o == null || (o+"").equalsIgnoreCase("null")) {
			return true;
		}
		return false;
	}
}
