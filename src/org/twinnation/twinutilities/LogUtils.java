package org.twinnation.twinutilities;

import org.twinnation.twinutilities.exceptions.LogNotInitializedException;

public final class LogUtils {
	
	private static final String LEVEL_LOG = "LOGGING";
	private static final String LEVEL_INFO = "INFORMA";
	private static final String LEVEL_WARN = "WARNING";
	private static final String LEVEL_ERROR = " ERROR ";
	
	private static final int MAX_LOG_FILE_SIZE_IN_KB = 1000;
	
	private static int logFileCounter = 1;
	private static String loggingFile = "logs";
	private static String currentLoggingFile;
	private static boolean isInitialized = false;
	private static boolean isSavingToFile = false;
	
	
	private LogUtils() {}
	
	
	/**
	 * Initializes the logging utility
	 */
	public static void init() {
		isInitialized = true;
		currentLoggingFile = loggingFile;
	}
	
	
	/**
	 * Initializes the logging utility
	 * @param saveLogToFile Saves logs to file or not
	 */
	public static void init(boolean saveLogToFile) {
		isSavingToFile = saveLogToFile;
		init();
	}
	
	
	/**
	 * Initializes the logging utility
	 * @param fName
	 * @param saveLogToFile Saves logs to file or not
	 */
	public static void init(String fName, boolean saveLogToFile) {
		loggingFile = fName;
		isSavingToFile = saveLogToFile;
		init();
	}
	
	
	/**
	 * Logs a message with severity level "LOG"
	 * @param message Message to log
	 */
	public static void log(String message) {
		doLog(LEVEL_LOG, message);
	}
	
	
	/**
	 * Logs a message with severity level "INFORMATION"
	 * @param message Message to log
	 */
	public static void info(String message) {
		doLog(LEVEL_INFO, message);
	}
	
	
	/**
	 * Logs a message with severity level "WARNING"
	 * @param message Message to log
	 */
	public static void warn(String message) {
		doLog(LEVEL_WARN, message);
	}
	
	
	/**
	 * Logs a message with severity level "ERROR"
	 * @param message Message to log
	 */
	public static void error(String message) {
		doLog(LEVEL_ERROR, message);
	}
	
	
	/**
	 * Logs the message with the given severity level
	 * @param level Severity level
	 * @param message Message to log
	 * @throws LogNotInitializedException If LogUtils.init hasn't been called
	 */
	private static void doLog(String level, String message) throws LogNotInitializedException {
		if (FileUtils.getFileSizeInKb(currentLoggingFile) > MAX_LOG_FILE_SIZE_IN_KB) {
			currentLoggingFile = loggingFile+""+ConversionUtils.zeroPad(4, ""+logFileCounter++);
		}
		if (!isInitialized) {
			throw new LogNotInitializedException();
		} else {
			message = DateTimeUtils.getTimestamp()+" ["+level+"] -> "+message;
			System.err.println(message);
			if (isSavingToFile) {
				FileUtils.writeInFile(message, currentLoggingFile, true);
			}
		}
	}
}
