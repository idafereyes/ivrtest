package org.isb.ivr.flow.render;

import org.isb.ivr.flow.bean.Input;
import org.isb.ivr.flow.bean.Prompt;

public interface Renderer {
	public String render(Prompt prompt, String flowURL);
	public String render(Input prompt, String flowURL);
}
