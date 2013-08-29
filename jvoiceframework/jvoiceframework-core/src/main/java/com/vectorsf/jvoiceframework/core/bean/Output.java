package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Output component used at jVoice framework.
 * Represents a group of audio items to be played.
 * They share some attributes specified in this class but each audio item can have different features.
 * 
 * @author idafereyes
 */

@Component("output")
public class Output implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4392125518171018331L;
		
	/**
	 * Specifies whether a user can interrupt the output block using speech or DTMF input.
	 * Takes its value from the bean that stores the app configuration defaults, 
	 * although it can be given other value later. 
	 */
	@Value("#{appConfigDefaults.bargein}")
	private boolean bargein;
	
	/**
	 * Indicates if the output block must be sent immediately to the VXML interpreter.
	 * Takes its value from the bean that stores the app configuration defaults, 
	 * although it can be given other value later. 
	 */
	@Value("#{appConfigDefaults.flush}")
	private boolean flush;
	
	/**
	 * Specifies whether the output block must be able to catch a VXML connection.disconnect.hangup event.
	 * Takes its value from the bean that stores the app configuration defaults, 
	 * although it can be given other value later. 
	 */
	@Value("#{appConfigDefaults.catchHangup}")
	private boolean catchHangup;
	
	/**
	 * List of audio items to play in the output block.
	 * 
	 */
	@Inject
	private List<AudioItem> audioItemsList;
	
	public boolean isBargein() {
		return bargein;
	}

	public void setBargein(boolean bargein) {
		this.bargein = bargein;
	}

	public boolean isFlush() {
		return flush;
	}

	public void setFlush(boolean flush) {
		this.flush = flush;
	}

	public boolean isCatchHangup() {
		return catchHangup;
	}

	public void setCatchHangup(boolean catchHangup) {
		this.catchHangup = catchHangup;
	}

	public List<AudioItem> getAudioItemsList() {
		return audioItemsList;
	}
	
	/**
	 * Adds an audioItem to the list.
	 * 
	 * @param src Value of audioItem src attribute.
	 * @param wording Value of audioItem wording attribute. 
	 */
	public void addAudioItem(String src, String wording){
		//TODO Se puede evitar hacer el new AucioItem()?
		AudioItem audioItem = new AudioItem();
		audioItem.setSrc(src);
		audioItem.setWording(wording);
		audioItemsList.add(audioItem);
	}

}
