package com.vectorsf.statistics.events;

import java.util.List;

import com.vectorsf.statistics.enums.EventType;

public class InitCall extends IVREvent{

	private static final long serialVersionUID = 3255846643150453317L;
	
	public InitCall(String callId, String serviceId, EventType eventType, String date) {
		super(callId, serviceId, eventType, date);
		// TODO Auto-generated constructor stub
	}
	private static final String name="INIT_CALL";
	private String ani;
	private String dnis;
	private List<EventParam> additionalParams;
	
	
	
	public static String getName() {
		return name;
	}
	public String getAni() {
		return ani;
	}
	public void setAni(String ani) {
		this.ani = ani;
	}
	public String getDnis() {
		return dnis;
	}
	public void setDnis(String dnis) {
		this.dnis = dnis;
	}
	public List<EventParam> getAdditionalParams() {
		return additionalParams;
	}
	public void setAdditionalParams(List<EventParam> additionalParams) {
		this.additionalParams = additionalParams;
	}
	
	
	
	
}
