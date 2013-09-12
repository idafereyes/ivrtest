package com.vectorsf.jvoiceframework.flow.render;

import com.vectorsf.jvoiceframework.core.bean.Element;


/**
 * Interfaz a cumplir por los renderers IVR
 * @author dmartina
 */
public interface Renderer {

    String render(Element element, String flowUrl);
    
}
