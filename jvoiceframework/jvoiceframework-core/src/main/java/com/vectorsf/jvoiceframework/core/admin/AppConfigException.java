package com.vectorsf.jvoiceframework.core.admin;

/**
 * AppConfig Exception class
 * @author mvinuesa
 *
 */
public class AppConfigException extends RuntimeException {

	/** * */
	private static final long serialVersionUID = 5283319168022517378L;

	public AppConfigException() {
	}

	public AppConfigException(String message) {
		super(message);
	}

	public AppConfigException(Throwable cause) {
		super(cause);
	}

	public AppConfigException(String message, Throwable cause) {
		super(message, cause);
	}
}
