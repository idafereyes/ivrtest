package com.vectorsf.jvoiceframework.flow.processor;

import java.io.Serializable;
import java.util.List;

import com.vectorsf.jvoiceframework.core.bean.Element;
import com.vectorsf.jvoiceframework.flow.render.RenderKit;
import com.vectorsf.jvoiceframework.flow.render.RenderKitTable;

/**
 * Procesador de estados IVR. De momento procesar un estado es apilarlo.
 * Los estados se eliminan una vez renderizados
 * 
 * @author dmartina
 */

public class FlowProcessor implements Serializable {

	private static final long serialVersionUID = -8138696103238359798L;
	
	private List<Element> states;
	
	private String renderkitParam;
	
	RenderKitTable renderkitTable;
	
	public List<Element> getStates() {
		return states;
	}

	public void setStates(List<Element> states) {
		this.states = states;
	}

	public void process(Element component) {
		 states.add(component);
	}

	public String getRenderkitParam() {
		return renderkitParam;
	}

	public void setRenderkitParam(String renderkitParam) {
		this.renderkitParam = renderkitParam;
	}

	public RenderKitTable getRenderkitTable() {
		return renderkitTable;
	}

	public void setRenderkitTable(RenderKitTable renderkitTable) {
		this.renderkitTable = renderkitTable;
	}

	/**
	 * Renderiza y elimina los estados
	 * 
	 * @param flowURL
	 * @return
	 */
	public String render(String flowURL){
		StringBuilder code = new StringBuilder();
		RenderKit renderKit = renderkitTable.getRenderKits().get(renderkitParam);
		
		
		for (Element element: states){
			String elementClassName = element.getClass().getName();
			String rendererClassName = renderKit.getRenderers().get(elementClassName);
			
		}
		
		states.clear();
		return code.toString();
	}
}
