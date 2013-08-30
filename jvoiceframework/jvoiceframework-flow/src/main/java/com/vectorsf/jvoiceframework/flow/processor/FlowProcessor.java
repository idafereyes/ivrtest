package com.vectorsf.jvoiceframework.flow.processor;

import java.io.Serializable;
import java.util.List;

import com.vectorsf.jvoiceframework.core.bean.Component;
import com.vectorsf.jvoiceframework.core.bean.Input;
import com.vectorsf.jvoiceframework.core.bean.Output;
import com.vectorsf.jvoiceframework.core.bean.Prompt;
import com.vectorsf.jvoiceframework.flow.render.Renderer;
import com.vectorsf.jvoiceframework.flow.render.VendorRenderers;

/**
 * Procesador de estados IVR. De momento procesar un estado es apilarlo.
 * Los estados se eliminan una vez renderizados
 * 
 * @author dmartina
 */

public class FlowProcessor implements Serializable {

	private static final long serialVersionUID = -8138696103238359798L;
	
	private VendorRenderers vendorRenderers;
	
	private List<Component> states;
	
	public List<Component> getStates() {
		return states;
	}

	public void setStates(List<Component> states) {
		this.states = states;
	}

	/**
	 * Renderizador de estados
	 */
	private Renderer renderer; 

	public void process(Component component) {
		 states.add(component);
	}
	 
	public Renderer getRenderer() {
		return renderer;
	}

	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}
	
	/**
	 * Renderiza y elimina los estados
	 * @param flowURL
	 * @return
	 */
	public String render(String flowURL){
		StringBuilder code = new StringBuilder();
		
		for (Component component: states){
			
		}
		states.clear();
		return code.toString();
		
	}

	public VendorRenderers getVendorRenderers() {
		return vendorRenderers;
	}

	public void setVendorRenderers(VendorRenderers vendorRenderers) {
		this.vendorRenderers = vendorRenderers;
	}
}
