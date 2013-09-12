package com.vectorsf.jvoiceframework.flow.render;

import java.io.Serializable;
import java.util.Map;

import com.vectorsf.jvoiceframework.core.bean.Element;

public class RenderKit implements Serializable {

    private static final long serialVersionUID = 4303710671209461629L;
    
    private Map<String, String> renderers;

    public Map<String, String> getRenderers() {
        return renderers;
    }

    public void setRenderers(Map<String, String> renderers) {
        this.renderers = renderers;
    }

    public void setRenderers(Object renderers) {
        this.renderers = (Map<String, String>) renderers;
    }

    public Renderer getRenderer(Element element) {
        String rendererClass = getRenderers().get(element.getClass().getName());
        Class<?> cls;
        Object renderer = null;
        try {
            cls = Class.forName(rendererClass);
            renderer = (Object) cls.newInstance();
        } catch (ClassNotFoundException e) {
            //TODO Meter un log
            return null;
        } catch (InstantiationException e) {
            //TODO Meter un log
            return null;
        } catch (IllegalAccessException e) {
            //TODO Meter un log
            return null;
        }
        if(renderer instanceof Renderer) {
            return (Renderer) renderer;
        }
        return null;
    }
}
