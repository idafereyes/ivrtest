package com.vectorsf.jvoiceframework.flow.render;

import com.vectorsf.jvoiceframework.core.bean.Input;
import com.vectorsf.jvoiceframework.core.bean.Output;
import com.vectorsf.jvoiceframework.core.bean.Prompt;
import com.vectorsf.jvoiceframework.core.bean.Transfer;

/**
 * Interfaz a cumplir por los renderers IVR
 * @author dmartina
 */
public interface Renderer {
	String render(Prompt prompt, String flowURL);
	String render(Output output, String flowURL);
	String render(Input prompt, String flowURL);
	String render(Transfer transfer, String flowURL);
}
