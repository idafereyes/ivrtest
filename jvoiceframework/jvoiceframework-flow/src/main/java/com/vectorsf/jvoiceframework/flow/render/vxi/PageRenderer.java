package com.vectorsf.jvoiceframework.flow.render.vxi;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.vectorsf.jvoiceframework.core.bean.Element;
import com.vectorsf.jvoiceframework.flow.render.IPageRenderer;
import com.vectorsf.jvoiceframework.flow.render.RenderKit;

@Component
public class PageRenderer 
        implements IPageRenderer, Serializable {

    private static final long serialVersionUID = 7098172662709401250L;
    
    @Override
    public String render(List<Element> states, String flowUrl, RenderKit renderkit) {
        StringBuilder code = new StringBuilder();
        
        code.append("<vxml>");
        for (Element element: states){
            code.append(renderkit.getRenderer(element).render(element, flowUrl));
        }
        code.append("</vxml>");
        return code.toString();
    }

}
