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
	public String render(Prompt prompt, String flowURL);
	public String render(Output output, String flowURL);
	public String render(Input prompt, String flowURL);
	public String render(Transfer transfer, String flowURL);
}
