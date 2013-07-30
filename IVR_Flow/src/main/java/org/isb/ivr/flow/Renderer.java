package org.isb.ivr.flow;

public interface Renderer {
	public String render(Prompt prompt, String flowURL);
	public String render(Input prompt, String flowURL);
}
