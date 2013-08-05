package org.isb.ivr.flow.bean;

import java.io.Serializable;

public class Input implements Serializable{

	private static final long serialVersionUID = -5942501816585768384L;
	
	private String value;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
