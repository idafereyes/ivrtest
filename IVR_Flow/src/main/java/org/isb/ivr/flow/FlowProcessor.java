package org.isb.ivr.flow;

import java.io.Serializable;
import java.util.List;

public class FlowProcessor implements Serializable {

	private static final long serialVersionUID = -8138696103238359798L;
	
	private List<Object> states;
	public List<Object> getStates() {
		return states;
	}

	public void setStates(List<Object> states) {
		this.states = states;
	}

	private Renderer renderer; 

	public void process(Prompt prompt) {
		 states.add(prompt);
	}
	 
	public Renderer getRenderer() {
		return renderer;
	}

	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}

	public void process(Input input) {
		 states.add(input);
	}
	
	public String render(String flowURL){
		
		String code = new String();
		for (Object element: states){
			if (element instanceof Input) {
				code += this.renderer.render((Input)element, flowURL);
			}
			else if (element instanceof Prompt) {
				code += this.renderer.render((Prompt)element, flowURL);
			}
		
		}
		states.clear();
		return code;
		
	}
}
