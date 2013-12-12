package com.vectorsf.jvoiceframework.core.service.ws;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "endpoints")
@XmlAccessorType(XmlAccessType.FIELD)
public class EndpointCollection {

	@XmlElement(name = "endpoint")
	private List<Endpoint> values;

	public List<Endpoint> getValues() {
		return values;
	}

	public void setValues(List<Endpoint> values) {
		this.values = values;
	}
	 
}	
