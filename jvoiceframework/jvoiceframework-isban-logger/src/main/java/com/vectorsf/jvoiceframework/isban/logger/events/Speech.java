package com.vectorsf.jvoiceframework.isban.logger.events;

import com.vectorsf.jvoiceframework.isban.logger.enums.EventType;

public class Speech extends IVREvent {


	private static final long serialVersionUID = 3255246643150454317L;
	
	public Speech(String callId, String serviceId, EventType eventType, String date) {
		super(callId, serviceId, eventType, date);
		// TODO Auto-generated constructor stub
	}

	private String speechID;
	private String type;
	private String text;

	public String getSpeechID() {
		return speechID;
	}
	public void setSpeechID(String speechID) {
		this.speechID = speechID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	

}
