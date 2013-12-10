package com.vectorsf.statistics.events;

import java.util.List;

import com.vectorsf.statistics.enums.CallReturnCode;
import com.vectorsf.statistics.enums.EventType;

public class EndCall extends IVREvent {

	private static final long serialVersionUID = 3255816643150454317L;
	
	public EndCall(String callId, String serviceId, EventType eventType, String date) {
		super(callId, serviceId, eventType, date);
		// TODO Auto-generated constructor stub
	}
	private String ani;
	private String dnis;
	private CallReturnCode returnCode;
	private List<EventParam> additionalParams;
	
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
	public CallReturnCode getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(CallReturnCode returnCode) {
		this.returnCode = returnCode;
	}
	public List<EventParam> getAdditionalParams() {
		return additionalParams;
	}
	public void setAdditionalParams(List<EventParam> additionalParams) {
		this.additionalParams = additionalParams;
	}

	
	


}
