package com.vectorsf.jvoiceframework.isban.logger.events;

import com.vectorsf.jvoiceframework.isban.logger.enums.EventType;

public class Info extends IVREvent {

	private static final long serialVersionUID = 3255846643150154317L;
	
	public Info(String callId, String serviceId, EventType eventType, String date) {
		super(callId, serviceId, eventType, date);
		// TODO Auto-generated constructor stub
	}

	private String info;

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	

}
