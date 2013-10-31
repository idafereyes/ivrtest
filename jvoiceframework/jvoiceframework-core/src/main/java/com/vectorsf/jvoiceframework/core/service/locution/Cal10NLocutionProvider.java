package com.vectorsf.jvoiceframework.core.service.locution;

import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ch.qos.cal10n.IMessageConveyor;
import ch.qos.cal10n.MessageConveyor;

import com.vectorsf.jvoiceframework.core.bean.User;
import com.vectorsf.jvoiceframework.core.bean.Wording;
import com.vectorsf.jvoiceframework.core.log.ExtendedLocLogger;
import com.vectorsf.jvoiceframework.core.log.Log;

@Service("locutionProvider")
public class Cal10NLocutionProvider implements LocutionProvider {
	
	@Log
	private ExtendedLocLogger logger;

	@Autowired
	private User user;
	
	@Value("#{appConfigDefaults.audiosLocationPrefix}")
	private String locationPrefix;
	
	@Value("#{appConfigDefaults.audiosFormatSuffix}")
	private String formatSuffix;

	public ExtendedLocLogger getLogger() {
		return logger;
	}

	public void setLogger(ExtendedLocLogger logger) {
		this.logger = logger;
	}
	
	private ConcurrentMap<Locale, IMessageConveyor> conveyors = new ConcurrentHashMap<Locale, IMessageConveyor>();
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLocationPrefix() {
		return locationPrefix;
	}

	public void setLocationPrefix(String locationPrefix) {
		this.locationPrefix = locationPrefix;
	}

	public String getFormatSuffix() {
		return formatSuffix;
	}

	public void setFormatSuffix(String formatSuffix) {
		this.formatSuffix = formatSuffix;
	}
	
	@Override
	public Wording getWording(Enum<?> key, Object... args) throws LocutionProviderException {
		return this.getWording(key, this.user.getLocale(), args);
	}

	@Override
	public Wording getWording(Enum<?> key, Locale locale, Object... args) throws LocutionProviderException {
		logger.debug(Cal10NLocutionProviderMessages.DEBUG_GET_WORDING, key, locale, args);

		IMessageConveyor messageConveyor = conveyors.get(locale);
		if (messageConveyor == null) {
			messageConveyor = new MessageConveyor(locale);
			conveyors.putIfAbsent(locale, messageConveyor);
		}		
		
		String message = null;	
		try {
			message = messageConveyor.getMessage(key, args);
		} catch (Exception mce) {
			logger.error(Cal10NLocutionProviderMessages.ERROR_GET_WORDING, mce);
			throw new LocutionProviderException(mce);
		}

		logger.debug(Cal10NLocutionProviderMessages.DEBUG_GET_WORDING_RETURN, message);
		return new Wording(message, locale);
	}

	@Override
	public String getAudioSrc(Enum<?> key)
			throws LocutionProviderException {
		return this.getAudioSrc(key, this.user.getLocale());
	}

	@Override
	public String getAudioSrc(Enum<?> key, Locale locale)
			throws LocutionProviderException {
		logger.debug(Cal10NLocutionProviderMessages.DEBUG_GET_AUDIO_SRC, key, locale);
		IMessageConveyor messageConveyor = conveyors.get(locale);
		if (messageConveyor == null) {
			messageConveyor = new MessageConveyor(locale);
			conveyors.putIfAbsent(locale, messageConveyor);
		}		
		
		String audioName = null;	
		try {
			audioName = messageConveyor.getMessage(key);
		} catch (Exception mce) {
			logger.error(Cal10NLocutionProviderMessages.ERROR_GET_AUDIO_SRC, mce);
			throw new LocutionProviderException(mce);
		}
		
		//Para implementar la funcionalidad de multiplataforma se concatenan un prefijo con la ubicación del audio y un sufijo con su formato (ambos configurables).
		String src = locationPrefix + audioName + formatSuffix;
		
		logger.debug(Cal10NLocutionProviderMessages.DEBUG_GET_AUDIO_SRC_RETURN, src);
		return src;
	}

}
