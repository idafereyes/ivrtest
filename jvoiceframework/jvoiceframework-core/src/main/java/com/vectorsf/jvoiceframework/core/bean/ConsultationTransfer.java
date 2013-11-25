package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Consultation Transfer component used at jVoice framework.
 * Represents a consultation transfer block which ask the interpreter to connect the caller to another entity 
 * (e.g. telephone line or another voice application)
 *  
 * @author idafereyes
 */
@Component("jVoiceArchConsultationTransfer")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ConsultationTransfer extends Transfer implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6560078506572278380L;

    /**
     * Events defined by the framework for consultation transfer type.
     * In addition to events defined for all transfer types.
     */
    public static final String TRANSFERRED_EVENT = "transferred";
    public static final String NOANSWER_EVENT = "noanswer";
    public static final String BUSY_EVENT = "busy";
    public static final String NETWORK_BUSY_EVENT = "network_busy";

    /**
     * The time to wait while trying to connect the call before returning the noanswer event.
     * Takes its value from the bean that stores the app configuration defaults, 
     * although it can be given other value later. 
     */    
    @Value("#{appConfigDefaults.transferConnectiontimeout}")
    private String timeout;

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

}
