package com.vectorsf.statistics.events;

import com.vectorsf.statistics.enums.EventType;

public class Action extends IVREvent {

	private static final long serialVersionUID = 7194681123504178575L;
	
	public Action(String callId, String serviceId, EventType eventType, String date) {
		super(callId, serviceId, eventType, date);
		// TODO Auto-generated constructor stub
	}
	private String actionID;
	private String result;
	
	public String getActionID() {
		return actionID;
	}
	public void setActionID(String actionID) {
		this.actionID = actionID;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

}