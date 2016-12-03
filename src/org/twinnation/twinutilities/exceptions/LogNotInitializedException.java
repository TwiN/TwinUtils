package org.twinnation.twinutilities.exceptions;


public class LogNotInitializedException extends RuntimeException {
	
	private static final long serialVersionUID = 733612939030343702L;
	
	private static final String EXCEPTION_INFO = 
			"LogUtils has not been initialized";
	
	
	public LogNotInitializedException() {
		super(EXCEPTION_INFO);
	}

	
	public LogNotInitializedException(String message) {
		super(message);
	}

	
	public LogNotInitializedException(Throwable cause) {
		super(cause);
	}

	
	public LogNotInitializedException(String message, Throwable cause) {
		super(EXCEPTION_INFO, cause);
	}
}
