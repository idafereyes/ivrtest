package com.vectorsf.jvoiceframework.core.enums;

/**
 * This enumeration represents all possible events at Input element.
 * 
 * @author sergio.milla
 */

public enum InputVars {
	
	/**
	 * Counter for global attempts (No Input + No Match Attempts)
	 */
	ATTEMPTS ("attempts"),
	
	/**
	 * Counter for no input attempts
	 */
	NOINPUTATTEMPTS ("noInputAttempts"),
	
	/**
	 * Counter for no match attempts
	 */
	NOMATCHATTEMPTS ("noMatchAttempts"),
	
	/**
	 * Max number of global attempts
	 */
	MAXATTEMPTS ("maxAttempts"),
	
	/**
	 * Max number of No Input Attempts
	 */
	MAXNOINPUTATTEMPTS ("maxNoInputAttempts"),
	
	/**
	 * Max number of No Match Attempts
	 */
	MAXNOMATCHATTEMPTS ("maxNoMatchAttempts"),
	
	/**
	 * Recognition return code used internally in the Input Component
	 * to identify the if a NOINPUT or NOMATCH has been thrown.
	 */
	RETURNCODE("returnCode");

	
	private String name;
	
	private InputVars(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
