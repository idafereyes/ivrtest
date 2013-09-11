package com.vectorsf.jvoiceframework.flow.render;

import java.io.Serializable;

import com.vectorsf.jvoiceframework.core.bean.Element;

/**
 * Interfaz a cumplir por los renderers IVR
 * @author dmartina
 */
public abstract class Renderer implements Serializable {
	
	private static final long serialVersionUID = -8011930092567626105L;

	public abstract String startRender(Element element, String flowUrl);
	
	public String endRender(Element element, String flowUrl) {
		return null;
	}
}
