package org.twinnation.twinutilities.exceptions;

/**
 * Exception that is thrown when LogUtils is initialized with invalid
 * parameters
 */
public class LogNotLoggingException extends RuntimeException {

	private static final long serialVersionUID = 53139800248476715L;
	private static final String EXCEPTION_INFO = 
			"LogUtils is not saving logs to file nor "
			+ "is it printing the logs to console";
	
	
	public LogNotLoggingException() {
		super(EXCEPTION_INFO);
	}

	
	public LogNotLoggingException(String message) {
		super(message);
	}

	
	public LogNotLoggingException(Throwable cause) {
		super(cause);
	}

	
	public LogNotLoggingException(String message, Throwable cause) {
		super(EXCEPTION_INFO, cause);
	}
}

