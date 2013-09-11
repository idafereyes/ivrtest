package com.vectorsf.jvoiceframework.flow.processor;

import java.io.Serializable;
import java.util.List;

import com.vectorsf.jvoiceframework.core.bean.Element;
import com.vectorsf.jvoiceframework.core.bean.Input;
import com.vectorsf.jvoiceframework.core.bean.Output;
import com.vectorsf.jvoiceframework.core.bean.Prompt;
import com.vectorsf.jvoiceframework.core.bean.Record;
import com.vectorsf.jvoiceframework.core.bean.Transfer;
import com.vectorsf.jvoiceframework.flow.render.Renderer;
import com.vectorsf.jvoiceframework.flow.render.RenderKitTable;

/**
 * Procesador de estados IVR. De momento procesar un estado es apilarlo.
 * Los estados se eliminan una vez renderizados
 * 
 * @author dmartina
 */

public class FlowProcessor implements Serializable {

	private static final long serialVersionUID = -8138696103238359798L;
	
	private RenderKitTable renderKitTable;
	
	private List<Element> states;
	
	private String renderkit;
	
	public List<Element> getStates() {
		return states;
	}

	public void setStates(List<Element> states) {
		this.states = states;
	}

//	/**
//	 * Renderizador de estados
//	 */
//	private Renderer renderer; 
//
//	public void process(Element component) {
//		 states.add(component);
//	}
//	 
//	public Renderer getRenderer() {
//		return renderer;
//	}
//
//	public void setRenderer(Renderer renderer) {
//		this.renderer = renderer;
//	}

	public RenderKitTable getRenderKitTable() {
		return renderKitTable;
	}

	public void setRenderKitTable(RenderKitTable renderKitTable) {
		this.renderKitTable = renderKitTable;
	}
	
	public String getRenderkit() {
		return renderkit;
	}

	public void setRenderkit(String renderkit) {
		this.renderkit = renderkit;
	}

	public void process(Record record) {
		 states.add(record);
	}

	/**
	 * Renderiza y elimina los estados
	 * @param flowURL
	 * @return
	 */
	public String render(String flowURL){
		StringBuilder code = new StringBuilder();
		
		System.out.println(renderkit);
		for (Element element: states){
			
		}
		states.clear();
		return code.toString();
		
	}

}
