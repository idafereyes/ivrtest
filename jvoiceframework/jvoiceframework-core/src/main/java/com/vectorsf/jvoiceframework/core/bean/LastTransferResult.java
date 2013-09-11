package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * Bean where jVoice framework stores the information related to a transfer attempt to another entity.
 * 
 * @author idafereyes
 */

@Component("lastTransferResult")
public class LastTransferResult implements Serializable {

	private static final long serialVersionUID = 4212121618463154203L;

	/**
	 * Represents the event that the transfer attempt have fired.
	 */
	private String event;
	
	/**
	 * Indicates the time the call have been transferred to another entity.
	 * Only applies to bridge transfers.
	 */
	private String duration;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}


}
