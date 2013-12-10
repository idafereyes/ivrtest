package com.vectorsf.statistics.events;

import java.io.Serializable;

import com.vectorsf.statistics.enums.EventType;

public class IVREvent implements Serializable{
	
	private static final long serialVersionUID = 7194681170504178575L;
	
	private String callId;
	private String serviceId;
	private EventType eventType;
	private String date;
	

	public IVREvent(String callId, String serviceId, EventType eventType,
			String date) {
		super();
		this.callId = callId;
		this.serviceId = serviceId;
		this.eventType = eventType;
		this.date = date;
	}
	public String getCallId() {
		return callId;
	}
	public void setCallId(String callId) {
		this.callId = callId;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}	
	
	
	

}
