package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * Bean where jVoice framework stores the information related to the last recording.
 * 
 * @author idafereyes
 */
@Component("lastRecordResult")
public class LastRecordResult implements Serializable{

    private static final long serialVersionUID = 3343902521553999489L;
    
    /**
     * Represents the event that the recording attempt have fired.
     */
    private String event;

    /**
     * Specifies the complete URI where the audio recording file is stored.
     */
    private String file;
    
    /**
     * The duration of the recording in milliseconds.
     */
    private String duration;
    
    /**
     * The size of the recording in bytes.
     */
    private String size;
    
    /**
     * Indicates the key the user has pressed to terminate the recording. If so, and if dtmfterm attribute was set to true.
     */
    private String termchar;
    
    /**
     * Indicates if the recording was terminated because the maxtime duration was reached.
     */
    private boolean maxsize;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTermchar() {
        return termchar;
    }

    public void setTermchar(String termchar) {
        this.termchar = termchar;
    }

    public boolean isMaxsize() {
        return maxsize;
    }

    public void setMaxsize(boolean maxsize) {
        this.maxsize = maxsize;
    }

}