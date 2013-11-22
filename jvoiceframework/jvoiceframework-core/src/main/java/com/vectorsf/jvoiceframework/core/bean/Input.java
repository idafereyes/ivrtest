package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Represents an Input Component used at jVoice framework.
 * 
 * @author dmartina
 */

@Component("jVoiceArchInput")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Input implements Serializable{

    private static final long serialVersionUID = -5942501816585768384L;

	public static final String MATCH_EVENT = "match";
	public static final String MAXATTEMPTS_EVENT = "maxattempts";
	public static final String MAXNOINPUT_EVENT = "maxnoinput";
	public static final String MAXNOMATCH_EVENT = "maxnomatch";
	public static final String HANGUP_EVENT = "hangup";
	public static final String ERROR_EVENT = "error";
    public static final String NORESOURCE_EVENT = "noresource";
    
    private static final String ARRAY_LIST_ANNOTATION = "#{new java.util.ArrayList()}";
    
    /**
     * Name of the input
     */
    private String name;
    
    /**
     * Number of max attempts (including no input & no match attempts)
     */
    @Value("#{appConfigDefaults.inputMaxAttempts}")
    private int maxAttempts;
    
    /**
     * Number of max attempts for No Input event
     */
    @Value("#{appConfigDefaults.inputNoInputAttempts}")
    private int maxNoInput;
    
    /**
     * Number of max attempts for No Match event
     */
    @Value("#{appConfigDefaults.inputNoMatchAttempts}")
    private int maxNoMatch;
    
    /**
     * Indicates globally when prompts can be interrupted
     */
    @Value("#{appConfigDefaults.inputBargein}")
    private boolean bargein;
    
    /**
     * This is the time that a user has in order to say something
     * before the ASR throws a No Input event.
     */
    @Value("#{appConfigDefaults.timeout}")
    private String timeout;
    
    /**
     * This is the time between two consecutive keypressed digits
     * to end the recognition. 
     */
    @Value("#{appConfigDefaults.interdigittimeout}")
    private String interdigittimeout;
    
    /**
     * The minimum confidence that a recognition must have in order
     * to be treated like a Match by the ASR.
     * Recognitions with a lower confidence will be treated as a 
     * No Match
     */
    @Value("#{appConfigDefaults.confidence}")
    private String confidence;
    
    /**
     * Map of vxml properties
     */
    @Value("#{new java.util.HashMap()}")
    private Map<String, String> properties;
    
    /**
     * List of grammars
     */
    @Value(ARRAY_LIST_ANNOTATION)
    private List<Grammar> grammars;
    
    /**
     * List of audios for each event
     */
    @Value(ARRAY_LIST_ANNOTATION)
    private List<AudioItem> mainAudios;
    @Value(ARRAY_LIST_ANNOTATION)
    private List<AudioItem> noMatchAudios;
    @Value(ARRAY_LIST_ANNOTATION)
    private List<AudioItem> noInputAudios;
    @Value(ARRAY_LIST_ANNOTATION)
    private List<AudioItem> matchAudios;    

    /**
     * List of customEvents (defined by the user) to be controlled at this input.
     */
    @Value(ARRAY_LIST_ANNOTATION)
    private List<String> customEvents;

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

    public List<AudioItem> getMatchAudios() {
		return matchAudios;
	}

	public void setMatchAudios(List<AudioItem> matchAudios) {
		this.matchAudios = matchAudios;
	}

	public List<String> getCustomEvents() {
		return customEvents;
	}

	public void setCustomEvents(List<String> customEvents) {
		this.customEvents = customEvents;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getInterdigittimeout() {
		return interdigittimeout;
	}

	public void setInterdigittimeout(String interdigittimeout) {
		this.interdigittimeout = interdigittimeout;
	}

	public String getConfidence() {
		return confidence;
	}

	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}
}
