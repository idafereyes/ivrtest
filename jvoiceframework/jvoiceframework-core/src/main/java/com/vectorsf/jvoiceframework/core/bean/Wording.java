package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;
import java.util.Locale;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * Wording component used at jVoice framework.
 * Represents a text message with its locale and, optionally, the way the TTS should interpret it.
 *  This wording is useful when text should be played by TTS.
 * 
 * @author sergiomilla
 */
@Component("jVoiceArchWording")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Wording implements Serializable{

	private static final long serialVersionUID = 358441420465034388L;

	/**
	 * The text to be played.
	 */
	private String text;
	
	/**
	 * The locale of the text to be played.
	 */
	private Locale locale;
	
	/**
	 * Information about the type of the text that indicate how should be interpreted.
	 */
	private SayAs sayAs;
		
	public Wording() { }
	
	public Wording(String text, Locale locale) {
		this.text = text;
		this.locale = locale;
	}
	

	public Wording(String text, Locale locale, SayAs sayAs) {
		this.text = text;
		this.locale = locale;
		this.sayAs = sayAs;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public SayAs getSayAs() {
		return sayAs;
	}

	public void setSayAs(SayAs sayAs) {
		this.sayAs = sayAs;
	}
	
}
