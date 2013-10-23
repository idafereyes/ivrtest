package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Blind Transfer component used at jVoice framework.
 * Represents a blind transfer block which ask the interpreter to connect the caller to another entity 
 * (e.g. telephone line or another voice application)
 *  
 * @author idafereyes
 */

@Component("blindTransfer")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BlindTransfer extends Transfer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3078618487004082809L;
	
	/**
	 * Events defined by the framework for blind transfer type.
	 * In addition to events defined for all transfer types.
	 */
	static public final String TRANSFERRED_EVENT = "transferred";

}
