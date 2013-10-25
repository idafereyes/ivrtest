package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("jVoiceArchEnd")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class End implements Serializable {

	private static final long serialVersionUID = 3318811710264139314L;
    
}
