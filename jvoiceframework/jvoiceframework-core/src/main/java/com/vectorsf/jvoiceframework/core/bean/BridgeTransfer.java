package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Bridge Transfer component used at jVoice framework.
 * Represents a bridge transfer block which ask the interpreter to connect the caller to another entity 
 * (e.g. telephone line or another voice application)
 *  
 * @author idafereyes
 */
@Component("jVoiceArchBridgeTransfer")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BridgeTransfer extends Transfer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4036016705639826571L;
	
	/**
	 * Events defined by the framework for bridge transfer type.
	 * In addition to events defined for all transfer types.
	 */
	public static final String NOANSWER_EVENT = "noanswer";
	public static final String BUSY_EVENT = "busy";
	public static final String NETWORK_BUSY_EVENT = "network_busy";
	public static final String MAXTIME_DISCONNECT_EVENT = "maxtime_disconnect";
	public static final String NETWORK_DISCONNECT_EVENT = "network_disconnect";
	public static final String FAR_END_DISCONNECT_EVENT = "far_end_disconnect";

	/**
	 * The time to wait while trying to connect the call before returning the noanswer event.
	 * Takes its value from the bean that stores the app configuration defaults, 
	 * although it can be given other value later. 
	 */	
	@Value("#{appConfigDefaults.transferConnectiontimeout}")
	private String timeout;

	/**
	 * The time that the transferred call is allowed to last, or 0s if no limit is imposed. 
	 * Takes its value from the bean that stores the app configuration defaults, 
	 * although it can be given other value later. 
	 */	
	@Value("#{appConfigDefaults.transferMaxtime}")
	private String maxtime;

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getMaxtime() {
		return maxtime;
	}

	public void setMaxtime(String maxtime) {
		this.maxtime = maxtime;
	}

}
