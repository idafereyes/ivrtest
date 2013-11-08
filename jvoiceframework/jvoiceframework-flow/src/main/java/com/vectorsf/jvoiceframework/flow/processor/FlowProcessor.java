package com.vectorsf.jvoiceframework.flow.processor;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.webflow.execution.RequestContext;

import com.vectorsf.jvoiceframework.core.bean.End;
import com.vectorsf.jvoiceframework.core.bean.Input;
import com.vectorsf.jvoiceframework.core.bean.Output;
import com.vectorsf.jvoiceframework.core.bean.Record;
import com.vectorsf.jvoiceframework.core.bean.Transfer;
import com.vectorsf.jvoiceframework.flow.render.Renderer;

/**
 * Procesador de estados IVR. De momento procesar un estado es apilarlo.
 * Los estados se eliminan una vez renderizados
 * 
 * @author dmartina
 */

public interface FlowProcessor  {

	/**
	 * Devuelve la pila de estados procesados pendientes de renderizar 
	 * @return
	 */
    public List<Object> getStates();
    
    /**
     * Establece pila de estados procesados pendientes de renderizar 
     * @param states
     */
    public void setStates(List<Object> states);
    
    /**
     * Devuelve el renderer utilizado para generar el código de la vista
     * @return
     */
    public Renderer getRenderer();
    
    /**
     * Establece el renderer utilizado para generar el código de la vista
     * @param renderer
     */
    public void setRenderer(Renderer renderer);
    
    /**
     * Apila un estado de tipo Input
     * @param renderer
     */
    public void process(Input input);
    
    /**
     * Apila un estado de tipo output
     * @param output
     */
    public void process(Output output);
    
    /**
     * Apila un estado de tipo transfer
     * @param transfer
     */
    public void process(Transfer transfer);
    
    /**
     * Apila un estado de tipo Record
     * @param record
     */
    public void process(Record record);
    
    /**
     * Apila un estado de tipo End
     * @param end
     */
    public void process(End end);
    
    /**
     * Genera el código de la vista en base a los estados apilados usando el renderer asignado. 
     * Al finalizar elimina estos estados de la pila
     * @param flowURL El renderer neceista esta URL (identificación de la ejecución del flujo) para generar la vista
     * @return
     */
    public String render(String flowURL);

}
