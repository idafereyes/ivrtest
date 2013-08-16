package com.vectorsf.jvoiceframework.flow.processor;

import java.io.Serializable;
import java.util.List;

import com.vectorsf.jvoiceframework.core.bean.Input;
import com.vectorsf.jvoiceframework.core.bean.Output;
import com.vectorsf.jvoiceframework.core.bean.Prompt;
import com.vectorsf.jvoiceframework.flow.render.Renderer;

/**
 * Procesador de estados IVR. De momento procesar un estado es apilarlo.
 * Los estados se eliminan una vez renderizados
 * 
 * @author dmartina
 */

public class FlowProcessor implements Serializable {

	private static final long serialVersionUID = -8138696103238359798L;
	
	
	private List<Object> states;
	
	public List<Object> getStates() {
		return states;
	}

	public void setStates(List<Object> states) {
		this.states = states;
	}

	/**
	 * Renderizador de estados
	 */
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
	
	public void process(Output output) {
		 states.add(output);
	}
	
	/**
	 * Renderiza y elimina los estados
	 * @param flowURL
	 * @return
	 */
	public String render(String flowURL){
		
		String code = new String();
		for (Object element: states){
			if (element instanceof Input) {
				code += this.renderer.render((Input)element, flowURL);
			}
			else if (element instanceof Prompt) {
				code += this.renderer.render((Prompt)element, flowURL);
			}else if (element instanceof Output) {
				code += this.renderer.render((Output)element, flowURL);			
			}

		
		}
		states.clear();
		return code;
		
	}
}
