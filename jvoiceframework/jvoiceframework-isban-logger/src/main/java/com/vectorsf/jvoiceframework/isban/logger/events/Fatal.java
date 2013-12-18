package com.vectorsf.jvoiceframework.isban.logger.events;

import com.vectorsf.jvoiceframework.isban.logger.enums.EventType;

public class Fatal extends IVREvent {

	private static final long serialVersionUID = 3255846643150154317L;
	
	public Fatal(String callId, String serviceId, EventType eventType, String date) {
		super(callId, serviceId, eventType, date);
		// TODO Auto-generated constructor stub
	}

	private String description;
	private String currentClassName;
	
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCurrentClassName() {
		return currentClassName;
	}

	public void setCurrentClassName(String currentClassName) {
		this.currentClassName = currentClassName;
	}
	
	

}
