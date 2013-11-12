package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Grammar Component used at jVoice framework.
 * 
 * This grammar specifies what ASR must recognize. It can be a builtin grmmar 
 * or a external grammar. By its mode, a grammar can be dtmf or voice.
 * 
 * Builtins grammar are pre-built inside the ASR engine. The user can use the
 * grammars without develop them.
 * 
 * External grammar are define through resource files with a specific format
 * depending of the ASR vendor. The user has to develop them.
 *
 *  
 */
@Component("jVoiceArchGrammar")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Grammar implements Serializable {

    private static final long serialVersionUID = -7601268978376750070L;

    /**
     * If a grammar is builtin, the builtin src attribute.
     * If a grammar is not builtin, the name of the grammar.
     */
    private String src;
    
    /**
     * Mode value. It can be <i>dtmf</i> or <i>voice</>
     */
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
