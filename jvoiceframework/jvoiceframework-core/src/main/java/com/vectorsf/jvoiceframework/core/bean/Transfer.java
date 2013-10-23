package com.vectorsf.jvoiceframework.core.bean;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

/**
 * Transfer component used at jVoice framework.
 * Represents a transfer block which ask the interpreter to connect the caller to another entity 
 * (e.g. telephone line or another voice application)
 * 
 * @author idafereyes
 */
public abstract class Transfer {
	
	/**
	 * Events defined by the framework for all transfer types.
	 */
	static public final String HANGUP_EVENT = "hangup";
	static public final String ERROR_EVENT = "error";
	static public final String CONNECTIONERROR_EVENT = "connectionerror";
	static public final String UNKNOWN_EVENT = "unknown";
	static public final String NEAR_END_DISCONNECT_EVENT = "near_end_disconnect";
	
	/**
	 * The URI of the destination (telephone, IP telephony address)
	 */
	private String dest;
	
	/**
	 * The URI of audio source to play while the transfer attempt is in progress.
	 */
	private String transferaudio;
		
	/**
	 * VXML properties to enhance transfer functionality provided.
	 * Represented as key(property)/value.
	 */	
    @Value("#{new java.util.HashMap()}")
	private Map<String,String> properties;

	/**
	 * List of events, within events defined by the framework, to be controlled during the transfer.
	 */	
    @Value("#{new java.util.ArrayList()}")
    private List<String> events;

	/**
	 * List of events, within events defined by the user, to be controlled during the transfer.
	 */	
    @Value("#{new java.util.ArrayList()}")
    private List<String> customEvents;

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getTransferaudio() {
		return transferaudio;
	}

	public void setTransferaudio(String transferaudio) {
		this.transferaudio = transferaudio;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public List<String> getEvents() {
		return events;
	}

	public void setEvents(List<String> events) {
		this.events = events;
	}

	public List<String> getCustomEvents() {
		return customEvents;
	}

	public void setCustomEvents(List<String> customEvents) {
		this.customEvents = customEvents;
	}
	
}
