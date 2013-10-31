package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;
import java.util.Locale;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component("jVoiceArchUser")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class User implements Serializable {

	private static final long serialVersionUID = -3614942114649284940L;
	
	@Value("#{appConfigDefaults.userLocale}")
	private Locale locale;
	
	//TODO Se le debe dar valor en el punto de entrada, no aquí.
	private String jVoiceCallId = UUID.randomUUID().toString();

	public Locale getLocale() {
		return locale;
	}
	
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getJVoiceCallId() {
		return jVoiceCallId;
	}

	public void setJVoiceCallId(String jVoiceCallId) {
		this.jVoiceCallId = jVoiceCallId;
	}

}
