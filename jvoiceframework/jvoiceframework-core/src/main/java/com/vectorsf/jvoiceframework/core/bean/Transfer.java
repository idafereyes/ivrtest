package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Transfer component used at jVoice framework.
 * Represents a transfer block which ask the interpreter to connect the caller to another entity 
 * (e.g. telephone line or another voice application)
 * 
 * @author idafereyes
 */

@Component("transfer")
public class Transfer implements Serializable {

	private static final long serialVersionUID = 2983400074451108607L;
	
	public Transfer(){
		eventsList = new ArrayList<String>();
		properties = new HashMap<String, String>();
	}

	/**
	 * The URI of the destination (telephone, IP telephony address)
	 */
	private String dest;

	/**
	 * The type of transfer. The value can be "bridge", "blind", or "consultation".
	 */
	private String type;
	
	/**
	 * The URI of audio source to play while the transfer attempt is in progress.
	 */
	private String transferaudio;
	
	/**
	 * The time to wait while trying to connect the call before returning the noanswer event.
	 * Takes its value from the bean that stores the app configuration defaults, 
	 * although it can be given other value later. 
	 */	
	@Value("#{appConfigDefaults.transferConnectiontimeout}")
	private String timeout;
	
	/**
	 * The time that the transferred call is allowed to last, or 0s if no limit is imposed. 
	 * Only applies for bridge transfer type.
	 * Takes its value from the bean that stores the app configuration defaults, 
	 * although it can be given other value later. 
	 */	
	@Value("#{appConfigDefaults.transferMaxtime}")
	private String maxtime;
	
	/**
	 * VXML properties to enhance transfer functionality provided.
	 * Represented as key(property)/value.
	 */	
	private Map<String,String> properties;

	/**
	 * List of events to be controlled during the transfer.
	 */	
	private List<String> eventsList;

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTransferaudio() {
		return transferaudio;
	}

	public void setTransferaudio(String transferaudio) {
		this.transferaudio = transferaudio;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getMaxtime() {
		return maxtime;
	}

	public void setMaxtime(String maxtime) {
		this.maxtime = maxtime;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public List<String> getEventsList() {
		return eventsList;
	}

	public void setEventsList(List<String> eventsList) {
		this.eventsList = eventsList;
	}


	
}
