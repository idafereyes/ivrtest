package com.vectorsf.jvoiceframework.flow.render;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("vxiRenderKit")
@Scope("singleton")
public class VxiRenderKit extends RenderKit implements Serializable {

	private static final long serialVersionUID = -5837545911766889270L;

	public VxiRenderKit() {
		renderers.put("com.vectorsf.jvoiceframework.core.bean.Input", "com.vectorsf.jvoiceframework.flow.render.vxi.InputRenderer");
	}
}
