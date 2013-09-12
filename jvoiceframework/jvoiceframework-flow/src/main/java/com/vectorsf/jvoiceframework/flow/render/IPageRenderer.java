package com.vectorsf.jvoiceframework.flow.render;

import java.util.List;

import com.vectorsf.jvoiceframework.core.bean.Element;

/**
 * Interfaz a cumplir por los renderers IVR
 * @author dmartina

 */
public interface IPageRenderer {

    String render(List<Element> states, String flowUrl, RenderKit renderkit);
    
}
