package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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
     * Specifies the name of the file.
     */
    private String fileName;
    
    /**
     * The recording file
     */
    private MultipartFile temprecording;
    
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
    private boolean maxtime;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public boolean isMaxtime() {
        return maxtime;
    }

    public void setMaxtime(boolean maxtime) {
        this.maxtime = maxtime;
    }

	public MultipartFile getTemprecording() {
		return temprecording;
	}

	public void setTemprecording(MultipartFile temprecording) {
		this.temprecording = temprecording;
	}

}
