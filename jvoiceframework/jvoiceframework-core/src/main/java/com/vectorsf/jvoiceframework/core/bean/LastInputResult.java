package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Represents the last result of the Input Component.
 */
@Component("lastInputResult")
@Scope("flow")
public class LastInputResult implements Serializable {

    private static final long serialVersionUID = -8072514116803473643L;

    /**
     * The input interpretation.
     */
    private String interpretation;
    /**
     * The recognition utterance
     * Represents the phrase that the user has said exactly.
     */
    private String utterance;
    /**
     * The confidence level.
     * Represents the probability that the recognizer assigns to the match.
     */
    private String confidence;
    /**
     * The mode of the recognition. It can be <i>dtmf</i> or <i>voice</i>.
     */
    private String inputmode;

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
