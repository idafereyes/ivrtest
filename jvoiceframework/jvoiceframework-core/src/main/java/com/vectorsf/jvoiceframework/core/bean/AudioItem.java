package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * AudioItem component used at jVoice framework.
 * Represents a single prompt to be played.
 * 
 * @author sergiomilla and idafereyes
 */
@Component("audioItem")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AudioItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8118962803922087013L;

	
	/**
	 * The URI of the audio prompt to play.
	 */
	private String src;
	
	/**
	 * The wording of the synthesized speech (TTS) to play. If src attribute has a value as well, it will only work as a backup in case of the loading of the prerecorded file fails.
	 */
	private String wording;

	/**
	 * The javascript condition to play the audio item
	 */
	private String cond;
	
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getWording() {
		return wording;
	}

	public void setWording(String wording) {
		this.wording = wording;
	}

	public String getCond() {
		return cond;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}
	
}
