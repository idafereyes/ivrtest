package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Bean where jVoice framework stores the information related to a transfer attempt to another entity.
 * 
 * @author idafereyes
 */

@Component("lastTransferResult")
@Scope("flow")
public class LastTransferResult implements Serializable {

	private static final long serialVersionUID = 4212121618463154203L;
	
	/**
	 * Indicates the time the call have been transferred to another entity.
	 * Only applies to bridge transfers.
	 */
	private String duration;

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}


}
