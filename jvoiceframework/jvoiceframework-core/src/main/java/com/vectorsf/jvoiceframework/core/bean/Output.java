package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

@Component("output")
public class Output implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4392125518171018331L;
		
	/**
	 * Specifies whether a user can interrupt the output block using speech or DTMF input.
	 */
	//TODO Revisar si conviene que sea de tipo boolean o String
	private boolean bargein;
	
	/**
	 * Indicates if the output block must be sent immediately to the VXML interpreter.
	 */
	//TODO Revisar si conviene que sea de tipo boolean o String
	private boolean flush;
	
	/**
	 * Specifies whether the output block must be able to catch a VXML connection.disconnect.hangup event.
	 */
	//TODO Revisar si conviene que sea de tipo boolean o String
	private boolean catchHangup;
	
	/**
	 * List of prompts to play in the output block.
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
