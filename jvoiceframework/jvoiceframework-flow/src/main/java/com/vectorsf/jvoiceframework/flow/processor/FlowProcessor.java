package com.vectorsf.jvoiceframework.flow.processor;

import java.io.Serializable;
import java.util.List;

import com.vectorsf.jvoiceframework.core.bean.Element;
import com.vectorsf.jvoiceframework.flow.render.RenderKit;
import com.vectorsf.jvoiceframework.flow.render.RenderKitTable;
import com.vectorsf.jvoiceframework.flow.render.vxi.PageRenderer;

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
    
    private RenderKitTable renderkitTable;
    
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
        String code = "";
        RenderKit renderKit = renderkitTable.getRenderKits().get(renderkitParam);
        
        String pageRendererClass = renderKit.getRenderers().get("com.vectorsf.jvoiceframework.core.bean.Page");
        Class<?> cls;
        Object pageRenderer = null;
        try {
            cls = Class.forName(pageRendererClass);
            pageRenderer = (Object) cls.newInstance();
        } catch (ClassNotFoundException e) {
            //TODO Meter un log
            return "";
        } catch (InstantiationException e) {
            //TODO Meter un log
            return "";
        } catch (IllegalAccessException e) {
            //TODO Meter un log
            return "";
        }
        
        code = ((PageRenderer) pageRenderer).render(states, flowURL, renderKit);
        
        states.clear();
        return code;
    }
    
}
