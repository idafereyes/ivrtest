package com.vectorsf.jvoiceframework.core.log;


/**
 * Interface used to log with internalization
 * 
 * @author mvinuesa
 *
 */
public interface Logger extends org.slf4j.Logger {

	/**
	 * Log in trace with enums
	 * @param key
	 * @param args
	 */
	void trace(Enum<?> key, Object... args);

	/**
	 * Log in debug with enums
	 * @param key
	 * @param args
	 */
	void debug(Enum<?> key, Object... args);

	/**
	 * Log in info with enums
	 * @param key
	 * @param args
	 */
	void info(Enum<?> key, Object... args);

	/**
	 * Log in warn with enums
	 * @param key
	 * @param args
	 */
	void warn(Enum<?> key, Object... args);

	/**
	 * Log in error with enums
	 * @param key
	 * @param args
	 */
	void error(Enum<?> key, Object... args);
}
