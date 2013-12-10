package com.vectorsf.statistics.events;

import java.util.List;

import com.vectorsf.statistics.enums.EventType;
import com.vectorsf.statistics.enums.SignalDirection;

public class Signalling extends IVREvent {

	private static final long serialVersionUID = 3255846643150454367L;
	
	public Signalling(String callId, String serviceId, EventType eventType, String date) {
		super(callId, serviceId, eventType, date);
		// TODO Auto-generated constructor stub
	}
	
//	public enum TransactionType {
//		SEND,
//		RECEIVE
//	}
	private SignalDirection signalDirection;
	private String signalType;
	private List<EventParam> signalData;
	
	
	public SignalDirection getSignalDirection() {
		return signalDirection;
	}
	public void setSignalDirection(SignalDirection signalDirection) {
		this.signalDirection = signalDirection;
	}
	public String getSignalType() {
		return signalType;
	}
	public void setSignalType(String signalType) {
		this.signalType = signalType;
	}
	public List<EventParam> getSignalData() {
		return signalData;
	}
	public void setSignalData(List<EventParam> signalData) {
		this.signalData = signalData;
	}

}
