package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("jVoiceArchGrammar")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Grammar implements Serializable {

    private static final long serialVersionUID = -7601268978376750070L;

    private String src;
    private String mode;
    
    public String getSrc() {
        return src;
    }
    
    public void setSrc(String src) {
        this.src = src;
    }

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
}
