package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("jVoiceArchData")
@Scope("session")
public class ArchData implements Serializable{

    private static final long serialVersionUID = 8229056264252945770L;

    @Value("false")
    private boolean initialized;
    private String ani;
    private String dnis;
    
    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

    public String getAni() {
        return ani;
    }
    
    public void setAni(String ani) {
        this.ani = ani;
    }
    
    public String getDnis() {
        return dnis;
    }
    
    public void setDnis(String dnis) {
        this.dnis = dnis;
    }
}
