package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Output component used at jVoice framework.
 * Represents a group of audio items to be played.
 * They share some attributes specified in this class but each audio item can have different features.
 * 
 * @author idafereyes
 */

@Component("output")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Output implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4392125518171018331L;
    
    public Output(){
        audioItemsList = new ArrayList<AudioItem>();
		properties = new HashMap<String, String>();
    }
        
    /**
     * Specifies whether a user can interrupt the output block using speech or DTMF input.
     * Takes its value from the bean that stores the app configuration defaults, 
     * although it can be given other value later. 
     */
    @Value("#{appConfigDefaults.bargein}")
    private boolean bargein;
    
    /**
     * Indicates if the output block must be sent immediately to the VXML interpreter.
     * Takes its value from the bean that stores the app configuration defaults, 
     * although it can be given other value later. 
     */
    @Value("#{appConfigDefaults.flush}")
    private boolean flush;
    
    /**
     * Specifies whether the output block must be able to catch a VXML connection.disconnect.hangup event.
     * Takes its value from the bean that stores the app configuration defaults, 
     * although it can be given other value later. 
     */
    @Value("#{appConfigDefaults.catchHangup}")
    private boolean catchHangup;
    
    /**
     * List of audio items to play in the output block.
     * 
     */
    private List<AudioItem> audioItemsList;
    
    /**
     * VXML properties to enhance transfer functionality provided.
     * Represented as key(property)/value.
     */    
    private Map<String,String> properties;

    public boolean isBargein() {
        return bargein;
    }

    public void setBargein(boolean bargein) {
        this.bargein = bargein;
    }

    public boolean isFlush() {
        return flush;
    }

    public void setFlush(boolean flush) {
        this.flush = flush;
    }

    public boolean isCatchHangup() {
        return catchHangup;
    }

    public void setCatchHangup(boolean catchHangup) {
        this.catchHangup = catchHangup;
    }

    public List<AudioItem> getAudioItemsList() {
        return audioItemsList;
    }

    public void setAudioItemsList(List<AudioItem> audioItemsList) {
        this.audioItemsList = audioItemsList;
    }

    public Map<String,String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String,String> properties) {
        this.properties = properties;
    }  

}
