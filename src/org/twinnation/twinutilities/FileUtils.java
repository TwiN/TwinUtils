package org.twinnation.twinutilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;


public final class FileUtils {
	
	/** Prevents instantiation of this utility class */
	private FileUtils() {}
	
	
	/**
	 * Puts the content of a file in a String
	 * @param fullPath Path to the file 
	 * @return Content of the file
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
	 * Function that gets the size of a file in bytes
	 * @param fullPath Path of the file
	 * @return File size in bytes
	 */
	public static long getFileSizeInByte(String fullPath) {
		File f = new File(fullPath);
		return (f.length());
	}
	
	
	/**
	 * Function that gets the size of a file in KB
	 * @param fullPath Path of the file
	 * @return File size in KB
	 */
	public static double getFileSizeInKb(String fullPath) {
		return ConversionUtils.fixedDecimal(3, (double)getFileSizeInByte(fullPath)/1024d);
	}
	
	
	/**
	 * Function that gets the size of a file in MB
	 * @param fullPath Path of the file
	 * @return File size in MB
	 */
	public static double getFileSizeInMb(String fullPath) {
		return ConversionUtils.fixedDecimal(3, (double)getFileSizeInKb(fullPath)/1024d);
	}
	
	
	/**
	 * Writes data a file
	 * @param data Data to put in file
	 * @param fName File name
	 * @param append Appends to the end of the file or not
	 * @return true if the file exists
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
	
	
	/**
	 * Gets the extension of a file or a path
	 * @param fileNameOrPath Name of the file or path toward the file
	 * @return The extension of the file
	 */
	public static String getExtension(String fileNameOrPath) {
		return fileNameOrPath.substring(fileNameOrPath.lastIndexOf('.')+1);
	}
	
}
