package com.vectorsf.jvoiceframework.flow.render;

import com.vectorsf.jvoiceframework.core.bean.End;
import com.vectorsf.jvoiceframework.core.bean.Input;
import com.vectorsf.jvoiceframework.core.bean.Output;
import com.vectorsf.jvoiceframework.core.bean.Prompt;
import com.vectorsf.jvoiceframework.core.bean.Record;
import com.vectorsf.jvoiceframework.core.bean.Transfer;
import com.vectorsf.jvoiceframework.core.bean.Element;

/**
 * Interfaz a cumplir por los renderers IVR
 * @author dmartina

 */
public interface Renderer {

    String render(Element element, String flowUrl);
    
}
