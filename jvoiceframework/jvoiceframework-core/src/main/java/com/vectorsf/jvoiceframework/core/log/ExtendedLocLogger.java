package com.vectorsf.jvoiceframework.core.log;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.cal10n.LocLogger;

/**
 * Class <b>ExtendedLocLogger</b>, it allows to print StackTrace (Throwable). 
 * 
 * @author mvinuesa
 * @see org.slf4j.Logger
 */
public class ExtendedLocLogger implements Logger {
	
	private LocLogger locLogger;
	
	public ExtendedLocLogger(LocLogger locLogger) {
		this.locLogger = locLogger;
	}
	
	/**
	 * @param th - {@link Throwable}
	 * @return stackTrace
	 */
	private String getStackTrace(Throwable th) {
		
		StringWriter strWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(strWriter);
		th.printStackTrace(printWriter);
		
		return strWriter.toString();
	}

	public void trace(Enum<?> key, Object... args) {

		if (locLogger.isTraceEnabled()) {
			locLogger.trace(key, args);
			for (Object arg : args) {
				if (arg instanceof Throwable) {
					locLogger.trace(getStackTrace((Throwable) arg));
				}
			}
		}
	}

	public void debug(Enum<?> key, Object... args) {

		if (locLogger.isDebugEnabled()) {
			locLogger.debug(key, args);
			for (Object arg : args) {
				if (arg instanceof Throwable) {
					locLogger.debug(getStackTrace((Throwable) arg));
				}
			}
		}
	}

	
	public void info(Enum<?> key, Object... args) {

		if (locLogger.isInfoEnabled()) {
			locLogger.info(key, args);
			for (Object arg : args) {
				if (arg instanceof Throwable) {
					locLogger.info(getStackTrace((Throwable) arg));
				}
			}
		}
	}

	
	public void warn(Enum<?> key, Object... args) {
		
		if (locLogger.isWarnEnabled()) {
			locLogger.warn(key, args);
			for (Object arg : args) {
				if (arg instanceof Throwable) {
					locLogger.warn(getStackTrace((Throwable) arg));
				}
			}
		}
	}

	
	public void error(Enum<?> key, Object... args) {

		if (locLogger.isErrorEnabled()) {
			locLogger.error(key, args);
			for (Object arg : args) {
				if (arg instanceof Throwable) {
					locLogger.error(getStackTrace((Throwable) arg));
				}
			}
		}
	}

	public boolean isTraceEnabled() {

		return locLogger.isTraceEnabled();
	}

	public boolean isTraceEnabled(Marker marker) {

		return locLogger.isTraceEnabled(marker);
	}

	
	public void trace(String msg) {
		
		locLogger.trace(msg);
	}

	
	public void trace(String format, Object arg) {
		
		locLogger.trace(format, arg);
	}

	
	public void trace(String format, Object arg1, Object arg2) {
		
		locLogger.trace(format, arg1, arg2);
	}

	
	public void trace(String format, Object... args) {

		locLogger.trace(format, args);
	}

	
	public void trace(String msg, Throwable t) {
		
		locLogger.trace(msg, t);
	}

	
	public void trace(Marker marker, String msg) {
		
		locLogger.trace(marker, msg);
	}

	
	public void trace(Marker marker, String format, Object arg) {
		
		locLogger.trace(marker, format, arg);
	}

	
	public void trace(Marker marker, String format, Object arg1, Object arg2) {
		
		locLogger.trace(marker, format, arg1, arg2);
	}

	
	public void trace(Marker marker, String format, Object... args) {
		
		locLogger.trace(marker, format, args);
	}

	
	public void trace(Marker marker, String msg, Throwable t) {
		
		locLogger.trace(marker, msg, t);
	}

	
	public boolean isDebugEnabled() {

		return locLogger.isDebugEnabled();
	}

	public boolean isDebugEnabled(Marker marker) {

		return locLogger.isDebugEnabled(marker);
	}

	public void debug(String msg) {

		locLogger.debug(msg);
	}

	
	public void debug(String format, Object arg) {
		
		locLogger.debug(format, arg);
	}

	
	public void debug(String format, Object arg1, Object arg2) {
		
		locLogger.debug(format, arg1, arg2);
	}

	
	public void debug(String format, Object... argArray) {
		
		locLogger.debug(format, argArray);
	}

	
	public void debug(String msg, Throwable t) {
		
		locLogger.debug(msg, t);
	}
	
	public void debug(Marker marker, String msg) {
		
		locLogger.debug(marker, msg);
	}
	
	public void debug(Marker marker, String format, Object arg) {
		
		locLogger.debug(marker, format, arg);
	}

	public void debug(Marker marker, String format, Object arg1, Object arg2) {
		
		locLogger.debug(marker, format, arg1, arg2);
	}

	public void debug(Marker marker, String format, Object... argArray) {
		
		locLogger.debug(marker, format, argArray);
	}

	public void debug(Marker marker, String msg, Throwable t) {
		
		locLogger.debug(marker, msg, t);
	}
	
	public boolean isInfoEnabled() {
		
		return locLogger.isInfoEnabled();
	}
	
	public boolean isInfoEnabled(Marker marker) {
		
		return locLogger.isInfoEnabled(marker);
	}

	public void info(String msg) {
		
		locLogger.info(msg);
	}
	
	public void info(String format, Object arg) {
		
		locLogger.info(format, arg);
	}
	
	public void info(String format, Object arg1, Object arg2) {
		
		locLogger.info(format, arg1, arg2);
	}
	
	public void info(String format, Object... args) {
		
		locLogger.info(format, args);
	}

	public void info(String msg, Throwable t) {
		
		locLogger.info(msg, t);
	}
	
	public void info(Marker marker, String msg) {
		
		locLogger.info(marker, msg);
	}
	
	public void info(Marker marker, String format, Object arg) {
		
		locLogger.info(marker, format, arg);
	}
	
	public void info(Marker marker, String format, Object arg1, Object arg2) {
		
		locLogger.info(marker, format, arg1, arg2);
	}
	
	public void info(Marker marker, String format, Object... args) {
		
		locLogger.info(marker, format, args);
	}
	
	public void info(Marker marker, String msg, Throwable t) {
		
		locLogger.info(marker, msg, t);
	}
	
	public boolean isWarnEnabled() {
		
		return locLogger.isWarnEnabled();
	}
	
	public boolean isWarnEnabled(Marker marker) {
		
		return locLogger.isWarnEnabled(marker);
	}
	
	public void warn(String msg) {
		
		locLogger.warn(msg);
	}

	public void warn(String format, Object arg) {
		
		locLogger.warn(format, arg);
	}
	
	public void warn(String format, Object arg1, Object arg2) {
		
		locLogger.warn(format, arg1, arg2);
	}
	
	public void warn(String format, Object... args) {
		
		locLogger.warn(format, args);
	}
	
	public void warn(String msg, Throwable t) {
		
		locLogger.warn(msg, t);
	}
	
	public void warn(Marker marker, String msg) {
		
		locLogger.warn(marker, msg);
	}

	public void warn(Marker marker, String format, Object arg) {
		
		locLogger.warn(marker, format, arg);
	}

	public void warn(Marker marker, String format, Object arg1, Object arg2) {
		
		locLogger.warn(marker, format, arg1, arg2);
	}

	public void warn(Marker marker, String format, Object... args) {
		
		locLogger.warn(marker, format, args);
	}
	
	public void warn(Marker marker, String msg, Throwable t) {
		
		locLogger.warn(marker, msg, t);
	}

	public boolean isErrorEnabled() {
		
		return locLogger.isErrorEnabled();
	}
	
	public boolean isErrorEnabled(Marker marker) {
		
		return locLogger.isErrorEnabled(marker);
	}

	public void error(String msg) {
		
		locLogger.error(msg);
	}

	public void error(String format, Object arg) {
		
		locLogger.error(format, arg);
	}

	public void error(String format, Object arg1, Object arg2) {
		
		locLogger.error(format, arg1, arg2);
	}
	
	public void error(String format, Object... args) {
		
		locLogger.error(format, args);
	}
	
	public void error(String msg, Throwable t) {
		
		locLogger.error(msg, t);
	}

	public void error(Marker marker, String msg) {
		
		locLogger.error(marker, msg);
	}

	public void error(Marker marker, String format, Object arg) {
		
		locLogger.error(marker, format, arg);
	}

	public void error(Marker marker, String format, Object arg1, Object arg2) {
		
		locLogger.error(marker, format, arg1, arg2);
	}

	public void error(Marker marker, String format, Object... args) {
		
		locLogger.error(marker, format, args);
	}

	public void error(Marker marker, String msg, Throwable t) {
		
		locLogger.error(marker, msg, t);
	}
	
	public String getName() {
		
		return locLogger.getName();
	}
	
	public LocLogger getLocLogger() {
		return locLogger;
	}
}