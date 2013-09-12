package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Record component used at jVoice framework.
 * Represents an input item that collects a recording from the user.
 * 
 * @author idafereyes
 */
@Component("record")
public class Record implements Serializable{

    private static final long serialVersionUID = 1632187224952805670L;
    
    public Record(){
        audioItemsList = new ArrayList<AudioItem>();
        eventsList = new ArrayList<String>();
        properties = new HashMap<String, String>();        
    }
    
    /** Allows the developer to insert a ‘beep tone’ to indicate to the caller that the application has begun recording.
     * Takes its value from the bean that stores the app configuration defaults, 
     * although it can be given other value later. 
     */
    @Value("#{appConfigDefaults.recordBeep}")
    private boolean beep;
    
    /** Specifies whether the caller will be allowed to terminate the recording in progress with the press of a dtmf key.
     * If set to 'false', then the recording continues until a maxtime or finalsilence event is reached.
     * Takes its value from the bean that stores the app configuration defaults, 
     * although it can be given other value later. 
     */
    @Value("#{appConfigDefaults.recordDtmfterm}")
    private boolean dtmfterm;
    
    /** Specifies the maximum length of a recording in seconds (s) or milliseconds (ms).
     * Takes its value from the bean that stores the app configuration defaults, 
     * although it can be given other value later. 
     */
    @Value("#{appConfigDefaults.recordMaxtime}")
    private String maxtime;
    
    /** Defines the amount of time that a caller can remain silent while the recording is being executed before the interpreter decides that the caller has finished recording the intended message.
     * Takes its value from the bean that stores the app configuration defaults, 
     * although it can be given other value later. 
     */
    @Value("#{appConfigDefaults.recordFinalsilence}")
    private String finalsilence;
    
    /**
     * Specifies the name of the audio file to be stored containing the recording.
     * Takes its value from the bean that stores the app configuration defaults, 
     * although it can be given other value later. 
     */
    @Value("#{appConfigDefaults.recordFileName}")
    private String fileName;
    
    /**
     * Specifies the path where the audio file containing the recording is stored. 
     * Takes its value from the bean that stores the app configuration defaults, 
     * although it can be given other value later. 
     */
    @Value("#{appConfigDefaults.recordFilePath}")
    private String filePath;

    /**
     * Specifies whether the audio file containing the recording is kept stored after the call has finished.
     * Takes its value from the bean that stores the app configuration defaults, 
     * although it can be given other value later. 
     */
    @Value("#{appConfigDefaults.recordKeep}")
    private boolean keep;
    
    /**
     * List of audio items to play before the recording starts.
     */
    private List<AudioItem> audioItemsList; 

    /**
     * VXML properties to enhance record functionality provided.
     * Represented as key(property)/value.
     */    
    private Map<String,String> properties;

    /**
     * List of events to be controlled during the record.
     */    
    private List<String> eventsList;


    public boolean isBeep() {
        return beep;
    }

    public void setBeep(boolean beep) {
        this.beep = beep;
    }

    public boolean isDtmfterm() {
        return dtmfterm;
    }

    public void setDtmfterm(boolean dtmfterm) {
        this.dtmfterm = dtmfterm;
    }

    public String getMaxtime() {
        return maxtime;
    }

    public void setMaxtime(String maxtime) {
        this.maxtime = maxtime;
    }

    public String getFinalsilence() {
        return finalsilence;
    }

    public void setFinalsilence(String finalsilence) {
        this.finalsilence = finalsilence;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isKeep() {
        return keep;
    }

    public void setKeep(boolean keep) {
        this.keep = keep;
    }

    public List<AudioItem> getAudioItemsList() {
        return audioItemsList;
    }

    public void setAudioItemsList(List<AudioItem> audioItemsList) {
        this.audioItemsList = audioItemsList;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Map<String,String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String,String> properties) {
        this.properties = properties;
    }

    public List<String> getEventsList() {
        return eventsList;
    }

    public void setEventsList(List<String> eventsList) {
        this.eventsList = eventsList;
    }
    
}