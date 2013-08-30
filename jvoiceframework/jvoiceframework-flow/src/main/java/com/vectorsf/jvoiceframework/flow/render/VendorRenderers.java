package com.vectorsf.jvoiceframework.flow.render;

import java.io.Serializable;
import java.util.Map;

public class VendorRenderers implements Serializable {

	private static final long serialVersionUID = 8609377557923534235L;

	private String name;
	
	private Map<String, IRenderer> renderers;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, IRenderer> getRenderers() {
		return renderers;
	}

	public void setRenderers(Map<String, IRenderer> renderers) {
		this.renderers = renderers;
	}
	
}
