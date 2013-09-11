package com.vectorsf.jvoiceframework.flow.render;

import java.io.Serializable;
import java.util.Map;

public class RenderKit implements Serializable {

	private static final long serialVersionUID = 4303710671209461629L;
	
	protected Map<String, String> renderers;

	public Map<String, String> getRenderers() {
		return renderers;
	}

	public void setRenderers(Map<String, String> renderers) {
		this.renderers = renderers;
	}

	public void setRenderers(Object renderers) {
		this.renderers = (Map<String, String>) renderers;
	}

	
}
