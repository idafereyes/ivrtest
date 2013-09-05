package com.vectorsf.jvoiceframework.core.log;

import java.util.Locale;

import org.slf4j.cal10n.LocLogger;
import org.slf4j.cal10n.LocLoggerFactory;

import ch.qos.cal10n.IMessageConveyor;
import ch.qos.cal10n.MessageConveyor;

/**
 * This Class return a new ExtendeLocLogger object, to log application
 * 
 * @author mvinuesa
 * 
 */
final class ExtendedLocLoggerFactory {

	/**
	 * Private constructor
	 */
	private ExtendedLocLoggerFactory() {
	}

	/**
	 * Return a ExtendeLocLogger new instance.
	 * 
	 * @param clazz
	 * @return ExtendedLocLogger
	 */
	public static ExtendedLocLogger getExtendedLocLogger(Class<?> clazz) {
		IMessageConveyor messageConveyor = new MessageConveyor(Locale.getDefault());
		LocLoggerFactory locFactory = new LocLoggerFactory(messageConveyor);
		LocLogger locLogger = locFactory.getLocLogger(clazz);
		return new ExtendedLocLogger(locLogger);
	}

}
