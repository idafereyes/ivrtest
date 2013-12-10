package com.vectorsf.statistics.events;

import java.util.List;

import com.vectorsf.statistics.enums.EventType;

public class InitService extends IVREvent{

	
	private static final long serialVersionUID = 3255846643150424317L;
	
	public InitService(String callId, String serviceId, EventType eventType, String date) {
		super(callId, serviceId, eventType, date);
		// TODO Auto-generated constructor stub
	}

	private List<EventParam> inputParams;

	public List<EventParam> getInputParams() {
		return inputParams;
	}

	public void setInputParams(List<EventParam> inputParams) {
		this.inputParams = inputParams;
	}
	
	

}
