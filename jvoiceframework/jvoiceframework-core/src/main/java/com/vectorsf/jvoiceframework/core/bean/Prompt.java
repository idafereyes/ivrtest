package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Representa un estado Prompt IVR+
 * 
 * @author dmarina
 */
@Component("prompt")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Prompt implements Serializable{

	private static final long serialVersionUID = -5942501816585768384L;
	
	private String message;

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
