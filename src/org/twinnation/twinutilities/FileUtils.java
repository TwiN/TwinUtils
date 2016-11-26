package org.twinnation.twinutilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;


public final class FileUtils {
	
	private FileUtils() {}
	
	/**
	 * Methode qui converti un fichier en chaine de caractere.
	 * @param fullPath Chemin complet
	 * @return donnees dans le fichier passe en parametre
	 */
	public static String getFileContents(String fullPath) {
		String contents = "";
		try {
			contents = new String(Files.readAllBytes(Paths.get(fullPath)));
		} catch (IOException e) {
			System.out.println("Unable to convert file to string: "
					+ e.getMessage());
		}
		return contents;
	}
	
	
	/**
	 * Methode qui ecrit dans un fichier
	 * @param data Donnees a mettre dans le fichier
	 * @param fName Nom du fichier
	 * @param append true pour ajouter, false pour ecraser.
	 * @return vrai si le fichier existe
	 */
	public static boolean writeInFile(String data, String fName, boolean append) {
		try {
			PrintWriter out = 
					new PrintWriter(new BufferedWriter(
					new FileWriter(fName, append)));
			out.println(data);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (new File(fName).exists());
	}
	

}
