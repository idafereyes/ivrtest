package com.vectorsf.jvoiceframework.core.log;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
public final class ExtendedLocLoggerFactory {
	
	private static IMessageConveyor messageConveyor = new MessageConveyor(Locale.getDefault());
	private static LocLoggerFactory locFactory = new LocLoggerFactory(messageConveyor);
	private static Map<Class<?>, Logger> loggerCache = new ConcurrentHashMap<Class<?>, Logger>();

	/**
	 * Private constructor
	 */
	private ExtendedLocLoggerFactory() {
		
	}

	/**
	 * Return a ExtendeLocLogger instance.
	 * 
	 * @param clazz
	 * @return ExtendedLocLogger
	 */
	public static Logger getLogger(Class<?> clazz) {
		Logger logger = loggerCache.get(clazz);
		if (logger==null) {
			LocLogger locLogger = locFactory.getLocLogger(clazz);
			loggerCache.put(clazz, new ExtendedLocLogger(locLogger));
			logger = loggerCache.get(clazz);	
		}
		return logger;
	}

}
