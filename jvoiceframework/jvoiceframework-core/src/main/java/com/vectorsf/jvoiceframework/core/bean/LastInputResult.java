package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("lastInputResult")
@Scope("flow")
public class LastInputResult implements Serializable {

    private static final long serialVersionUID = -8072514116803473643L;

    private String event;
    
    private String interpretation;
    private String utterance;
    private String confidence;
    private String inputmode;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }

    public String getUtterance() {
        return utterance;
    }

    public void setUtterance(String utterance) {
        this.utterance = utterance;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    public String getInputmode() {
        return inputmode;
    }

    public void setInputmode(String inputmode) {
        this.inputmode = inputmode;
    }

}
