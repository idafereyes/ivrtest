package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representa un estado input IVR
 * @author dmartina
 */
public class Input implements Component, Serializable{

	private static final long serialVersionUID = -5942501816585768384L;
	
	/**
	 * Name of the input
	 */
	private String name;
	
	/**
	 * Number of max attempts (including no input & no match attempts)
	 */
	private int maxAttempts;
	
	/**
	 * Number of max attempts for No Input event
	 */
	private int maxNoInput;
	
	/**
	 * Number of max attempts for No Match event
	 */
	private int maxNoMatch;
	
	/**
	 * Indicates globally when prompts can be interrupted
	 */
	private boolean bargein;
	
	/**
	 * Map of vxml properties
	 */
	private Map<String, String> properties;
	
	/**
	 * List of grammars
	 */
	private List<Grammar> grammars;
	
	/**
	 * List of audios for each event
	 */
	private List<AudioItem> mainAudios;
	private List<AudioItem> noMatchAudios;
	private List<AudioItem> noInputAudios;
	
	/**
	 * List of events
	 */
	private List<String> events;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxAttempts() {
		return maxAttempts;
	}

	public void setMaxAttempts(int maxAttempts) {
		this.maxAttempts = maxAttempts;
	}

	public int getMaxNoInput() {
		return maxNoInput;
	}

	public void setMaxNoInput(int maxNoInput) {
		this.maxNoInput = maxNoInput;
	}

	public int getMaxNoMatch() {
		return maxNoMatch;
	}

	public void setMaxNoMatch(int maxNoMatch) {
		this.maxNoMatch = maxNoMatch;
	}

	public boolean isBargein() {
		return bargein;
	}

	public void setBargein(boolean bargein) {
		this.bargein = bargein;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public List<Grammar> getGrammars() {
		return grammars;
	}

	public void setGrammars(List<Grammar> grammars) {
		this.grammars = grammars;
	}
	
	public List<AudioItem> getMainAudios() {
		return mainAudios;
	}

	public void setMainAudios(List<AudioItem> audioItems) {
		this.mainAudios = audioItems;
	}

	public List<AudioItem> getNoMatchAudios() {
		return noMatchAudios;
	}

	public void setNoMatchAudios(List<AudioItem> noMatchAudios) {
		this.noMatchAudios = noMatchAudios;
	}

	public List<AudioItem> getNoInputAudios() {
		return noInputAudios;
	}

	public void setNoInputAudios(List<AudioItem> noInputAudios) {
		this.noInputAudios = noInputAudios;
	}
	
	public List<String> getEvents() {
		return events;
	}

	public void setEvents(List<String> events) {
		this.events = events;
	}
	
	/**
	 * It returns null because is the root component
	 * 
	 * 
	 */
	@Override
	public Component getParent() {
		return null;
	}
}
