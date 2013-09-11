package com.vectorsf.jvoiceframework.flow.render;

import java.io.Serializable;

import com.vectorsf.jvoiceframework.core.bean.Element;

public class InputRenderer extends Renderer implements Serializable {

	private static final long serialVersionUID = -6226173850974162941L;

	@Override
	public String startRender(Element component, String flowUrl) {
		
		return "hola";
	}

}
