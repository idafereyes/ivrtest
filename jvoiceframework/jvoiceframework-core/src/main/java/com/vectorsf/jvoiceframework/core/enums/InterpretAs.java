package com.vectorsf.jvoiceframework.core.enums;

/**
 * This enumeration represents all possible types
 * of text interpretation when played by a TTS engine.
 * 
 * @author idafereyes
 */

public enum InterpretAs {

	BOOLEAN ("boolean"),
	DATE ("date"),
	DIGITS ("digits"),
	CURRENCY ("currency"),
	NUMBER ("number"),
	PHONE ("phone"),
	TIME ("time");
	
	private String name;
	
	private InterpretAs(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
}
