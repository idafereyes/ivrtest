package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vectorsf.jvoiceframework.core.enums.InterpretAs;

@Component("sayAs")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SayAs implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1175318343992142220L;
	
	private InterpretAs interpretAs;
	private String format;
	
	public SayAs(){	}
	
	public SayAs(InterpretAs interpretAs){
		this.interpretAs = interpretAs;
		this.format = null;
	}
	
	public SayAs(InterpretAs interpretAs, String format){
		this.interpretAs = interpretAs;
		this.format = format;
	}
	
	public InterpretAs getInterpretAs() {
		return interpretAs;
	}
	public void setInterpretAs(InterpretAs interpretAs) {
		this.interpretAs = interpretAs;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}

}
