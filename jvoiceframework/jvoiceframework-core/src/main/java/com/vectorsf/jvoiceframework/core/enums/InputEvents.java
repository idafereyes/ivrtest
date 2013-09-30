package com.vectorsf.jvoiceframework.core.enums;

/**
 * This enumeration represents all possible events at Input element.
 * 
 * @author sergio.milla
 */

public enum InputEvents {
	
	MATCH ("match"),
	MAXATTEMPTS ("maxattempts"),
	MAXNOINPUT ("maxnoinput"),
	MAXNOMATCH ("maxnomatch"),
	HANGUP ("hangup"),
	NORESOURCE ("noresource"),
	ERROR ("error");
	
	private String name;
	
	private InputEvents(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
