package com.vectorsf.jvoiceframework.core.service.speech;

import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.vectorsf.jvoiceframework.core.bean.UserLocale;

import ch.qos.cal10n.IMessageConveyor;
import ch.qos.cal10n.MessageConveyor;
import org.springframework.stereotype.Service;

@Service("speechProvider")
public class LocutionProviderCal10n implements LocutionProvider {
	
	@Autowired
	private UserLocale localeProvider;
	
	private ConcurrentMap<Locale, IMessageConveyor> wordingConveyors = new ConcurrentHashMap<Locale, IMessageConveyor>();
	private ConcurrentMap<Locale, IMessageConveyor> audioSourceConveyors = new ConcurrentHashMap<Locale, IMessageConveyor>();
	
	@Override
	public <E extends Enum<?>> String getWording(E key, Object... args) {
		return this.getWording(key, this.localeProvider.getLocale(), args);
	}

	@Override
	public <E extends Enum<?>> String getWording(E key, Locale locale, Object... args) {
		return this.getMessage(this.wordingConveyors, key, locale, args);
	}

	@Override
	public <E extends Enum<?>> String getAudioSource(E key) {
		return this.getAudioSource(key, this.localeProvider.getLocale());
	}

	@Override
	public <E extends Enum<?>> String getAudioSource(E key, Locale locale) {
		return this.getMessage(this.audioSourceConveyors, key, locale);
	}
	
	private <E extends Enum<?>> String getMessage(ConcurrentMap<Locale, IMessageConveyor> conveyors, E key, Locale locale, Object... args) {
		IMessageConveyor messageConveyor = conveyors.get(locale);
		if (messageConveyor == null) {
			messageConveyor = new MessageConveyor(locale);
			conveyors.putIfAbsent(locale, messageConveyor);
		}		
		return messageConveyor.getMessage(key, args);
	}
	
}
