package com.vectorsf.jvoiceframework.isban.logger.exceptions;

public class ParseException extends Exception {

	private static final long serialVersionUID = -1548411593228993481L;

	public ParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParseException(String message) {
		super(message);
	}
	
}
