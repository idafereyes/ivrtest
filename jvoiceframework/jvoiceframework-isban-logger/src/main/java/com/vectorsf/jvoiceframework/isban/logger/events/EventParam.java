package com.vectorsf.jvoiceframework.isban.logger.events;

import java.io.Serializable;

import com.vectorsf.jvoiceframework.isban.logger.utils.LogUtils;

public class EventParam implements Serializable {
	
	private static final long serialVersionUID = -2529043454352239083L;
	
	private StringBuffer name_;
	private StringBuffer value_;
	private final String SEPARATOR = "=";
	
	public EventParam() {}
	public EventParam(String name, String value) {
		
		this.name_ = new StringBuffer(LogUtils.parseEventLogMessage(name));
		if (value != null)
			this.value_ = new StringBuffer(LogUtils.parseEventLogMessage(value));
		else
			this.value_= new StringBuffer();
	}
	
	public StringBuffer getName_() {
		return name_;
	}
	public void setName_(StringBuffer name_) {
		this.name_ = new StringBuffer (LogUtils.parseEventLogMessage(name_.toString()));
	}
	public StringBuffer getValue_() {
		return value_;
	}
	public void setValue_(StringBuffer value_) {
		this.value_ = new StringBuffer (LogUtils.parseEventLogMessage(value_.toString()));
	}

	@Override
	public String toString() {
		return (name_+SEPARATOR+value_).toString();
	}
}