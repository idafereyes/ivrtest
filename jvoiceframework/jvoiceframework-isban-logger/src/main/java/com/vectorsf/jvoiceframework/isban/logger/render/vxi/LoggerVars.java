package com.vectorsf.jvoiceframework.isban.logger.render.vxi;

public enum LoggerVars {
	
	INPUT_INFO ("inputInfo"),
	
	DIALOGUEID ("dialogueID"),

	RECAVAILABLE ("recAvailable"),
	
	RECDETECTED ("recDetected"),
			
	USERINPUT ("userInput"),
	
	RECPARAMS ("recParams");

	private String name;
	
	private LoggerVars(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}


}
