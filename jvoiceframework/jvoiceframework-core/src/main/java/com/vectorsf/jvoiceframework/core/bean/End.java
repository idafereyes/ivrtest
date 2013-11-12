package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * End Component used at jVoice framework.
 * Using this component, the application says to the platform that have to
 * finish the application.
 * Every application must have at least one End Component.
 *
 */
@Component("jVoiceArchEnd")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class End implements Serializable {

	private static final long serialVersionUID = 3318811710264139314L;
    
}
