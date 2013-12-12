package com.vectorsf.jvoiceframework.core.service.ws;

import javax.xml.bind.annotation.XmlAttribute;

public class Endpoint {
	
	// WS Targenamespace
	private String namespace; 
	// WS Port Name
	private String name; 
	private String url; 
	
	@XmlAttribute(name = "namespace")
	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	@XmlAttribute(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute(name = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Endpoint(String namespace, String name, String url) {
		super();
		this.namespace = namespace;
		this.name = name;
		this.url = url;
	}

	public Endpoint() {
		super();
	}
}