package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;

public class Grammar implements Serializable {

	private static final long serialVersionUID = -7601268978376750070L;

	private String src;
	private String type;
	
	public String getSrc() {
		return src;
	}
	
	public void setSrc(String src) {
		this.src = src;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
}
