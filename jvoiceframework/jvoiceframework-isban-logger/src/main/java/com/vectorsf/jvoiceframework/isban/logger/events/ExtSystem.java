package com.vectorsf.jvoiceframework.isban.logger.events;

import java.util.List;

import com.vectorsf.jvoiceframework.isban.logger.enums.EventType;
import com.vectorsf.jvoiceframework.isban.logger.enums.MessageDirection;

public class ExtSystem extends IVREvent {

	private static final long serialVersionUID = 3255846642350454317L;
	
	public ExtSystem(String callId, String serviceId, EventType eventType, String date) {
		super(callId, serviceId, eventType, date);
		// TODO Auto-generated constructor stub
	}
	private String systemID;
	private String systemType;
	private String uri;
	private String operation;
	private MessageDirection messageDirection;
	private String message;
	private String returnCode;
	private String returnDesc;
	private List<EventParam> timeout;
	
	public String getSystemID() {
		return systemID;
	}
	public void setSystemID(String systemID) {
		this.systemID = systemID;
	}
	public String getSystemType() {
		return systemType;
	}
	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public MessageDirection getMessageDirection() {
		return messageDirection;
	}
	public void setMessageDirection(MessageDirection messageDirection) {
		this.messageDirection = messageDirection;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnDesc() {
		return returnDesc;
	}
	public void setReturnDesc(String returnDesc) {
		this.returnDesc = returnDesc;
	}
	public List<EventParam> getTimeout() {
		return timeout;
	}
	public void setTimeout(List<EventParam> timeout) {
		this.timeout = timeout;
	}
}
