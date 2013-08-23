package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;
import java.util.List;

public class LastResultInfo implements Serializable {

	private static final long serialVersionUID = -8072514116803473643L;

	private String event;
	
	private List<String> interpretation;
	private List<String> utterance;
	private List<String> confidence;
	private List<String> inputmode;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public List<String> getInterpretation() {
		return interpretation;
	}

	public void setInterpretation(List<String> interpretation) {
		this.interpretation = interpretation;
	}

	public List<String> getUtterance() {
		return utterance;
	}

	public void setUtterance(List<String> utterance) {
		this.utterance = utterance;
	}

	public List<String> getConfidence() {
		return confidence;
	}

	public void setConfidence(List<String> confidence) {
		this.confidence = confidence;
	}

	public List<String> getInputmode() {
		return inputmode;
	}

	public void setInputmode(List<String> inputmode) {
		this.inputmode = inputmode;
	}

	
}
