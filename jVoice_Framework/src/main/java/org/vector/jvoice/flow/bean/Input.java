package org.vector.jvoice.flow.bean;

import java.io.Serializable;

/**
 * Representa un estado input IVR
 * @author dmartina
 */
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
