package com.vectorsf.jvoiceframework.isban.logger.events;

import java.util.List;

import com.vectorsf.jvoiceframework.isban.logger.enums.DialogueReturnCode;
import com.vectorsf.jvoiceframework.isban.logger.enums.EventType;
import com.vectorsf.jvoiceframework.isban.logger.enums.RecAvailable;
import com.vectorsf.jvoiceframework.isban.logger.enums.RecDetected;

public class Dialogue extends IVREvent{

	private static final long serialVersionUID = 3255846643150454317L;
	
	public Dialogue(String callId, String serviceId, EventType eventType, String date) {
		super(callId, serviceId, eventType, date);
		// TODO Auto-generated constructor stub
	}
	private String dialogueID;
	private RecAvailable recAvailable;
	private RecDetected recDetected;
	private int tries;
	private DialogueReturnCode returnCode;
	private String userInput;
	private List<EventParam> recParams;
	
	public String getDialogueID() {
		return dialogueID;
	}
	public void setDialogueID(String dialogueID) {
		this.dialogueID = dialogueID;
	}
	public RecAvailable getRecAvailable() {
		return recAvailable;
	}
	public void setRecAvailable(RecAvailable recAvailable) {
		this.recAvailable = recAvailable;
	}
	public RecDetected getRecDetected() {
		return recDetected;
	}
	public void setRecDetected(RecDetected recDetected) {
		this.recDetected = recDetected;
	}
	public int getTries() {
		return tries;
	}
	public void setTries(int tries) {
		this.tries = tries;
	}
	public DialogueReturnCode getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(DialogueReturnCode returnCode) {
		this.returnCode = returnCode;
	}
	public String getUserInput() {
		return userInput;
	}
	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}
	public List<EventParam> getRecParams() {
		return recParams;
	}
	public void setRecParams(List<EventParam> recParams) {
		this.recParams = recParams;
	}
	
	
	
	
}
