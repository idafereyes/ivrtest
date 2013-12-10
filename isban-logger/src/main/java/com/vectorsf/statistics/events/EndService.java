package com.vectorsf.statistics.events;

import java.util.List;

import com.vectorsf.statistics.enums.EventType;
import com.vectorsf.statistics.enums.ServiceReturnCode;

public class EndService extends IVREvent {
	
	private static final long serialVersionUID = 1255846643150454317L;
	
	public EndService(String callId, String serviceId, EventType eventType, String date) {
		super(callId, serviceId, eventType, date);
		// TODO Auto-generated constructor stub
	}

	private List<EventParam> additionalParams;	
	private ServiceReturnCode returnCode;
	private String userResponse;

	

	public List<EventParam> getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(List<EventParam> additionalParams) {
		this.additionalParams = additionalParams;
	}

	public ServiceReturnCode getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(ServiceReturnCode returnCode) {
		this.returnCode = returnCode;
	}

	public String getUserResponse() {
		return userResponse;
	}

	public void setUserResponse(String userResponse) {
		this.userResponse = userResponse;
	}
	
	
	
	
	
	
	
	
	

}
