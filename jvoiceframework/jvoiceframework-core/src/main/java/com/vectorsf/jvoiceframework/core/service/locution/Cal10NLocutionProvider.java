package com.vectorsf.jvoiceframework.core.service.locution;

import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.vectorsf.jvoiceframework.core.bean.UserLocale;
import com.vectorsf.jvoiceframework.core.log.ExtendedLocLogger;
import com.vectorsf.jvoiceframework.core.log.Log;

import ch.qos.cal10n.IMessageConveyor;
import ch.qos.cal10n.MessageConveyor;
import ch.qos.cal10n.MessageConveyorException;

import org.springframework.stereotype.Service;

@Service("locutionProvider")
public class Cal10NLocutionProvider implements LocutionProvider {
	
	@Log
	private ExtendedLocLogger logger;

	@Autowired
	private UserLocale userLocale;
	
	public ExtendedLocLogger getLogger() {
		return logger;
	}

	public void setLogger(ExtendedLocLogger logger) {
		this.logger = logger;
	}
	
	private ConcurrentMap<Locale, IMessageConveyor> conveyors = new ConcurrentHashMap<Locale, IMessageConveyor>();
	
	public UserLocale getUserLocale() {
		return userLocale;
	}

	public void setUserLocale(UserLocale userLocale) {
		this.userLocale = userLocale;
	}

	@Override
	public String getLocution(Enum<?> key, Object... args) throws LocutionProviderException {
		return this.getLocution(key, this.userLocale.getLocale(), args);
	}

	@Override
	public String getLocution(Enum<?> key, Locale locale, Object... args) throws LocutionProviderException {
		logger.debug(Cal10NLocutionProviderMessages.DEBUG_GET_LOCUTION, key, locale, args);
		IMessageConveyor messageConveyor = conveyors.get(locale);
		if (messageConveyor == null) {
			messageConveyor = new MessageConveyor(locale);
			conveyors.putIfAbsent(locale, messageConveyor);
		}		
		
		String message = null;	
		try {
			message = messageConveyor.getMessage(key, args);
		} catch (Exception mce) {
			logger.error(Cal10NLocutionProviderMessages.ERROR_GET_LOCUTION, mce);
			throw new LocutionProviderException(mce);
		}
		logger.debug(Cal10NLocutionProviderMessages.DEBUG_GET_LOCUTION_RETURN, message);
		return message;
	}

}
