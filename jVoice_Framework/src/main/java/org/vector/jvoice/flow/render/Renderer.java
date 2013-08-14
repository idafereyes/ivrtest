package org.vector.jvoice.flow.render;

import org.vector.jvoice.flow.bean.Input;
import org.vector.jvoice.flow.bean.Prompt;

/**
 * Interfaz a cumplir por los renderers IVR
 * @author dmartina
 */
public interface Renderer {
	public String render(Prompt prompt, String flowURL);
	public String render(Input prompt, String flowURL);
}
