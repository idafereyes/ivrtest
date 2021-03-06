package org.vector.jvoice.flow.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component("audioItem")
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
	
}
