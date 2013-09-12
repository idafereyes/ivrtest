package com.vectorsf.jvoiceframework.flow.render;

import java.io.Serializable;
import java.util.Map;

public class RenderKitTable implements Serializable {

    private static final long serialVersionUID = 8609377557923534235L;
    
    private Map<String, RenderKit> renderKits;

    public Map<String, RenderKit> getRenderKits() {
        return renderKits;
    }

    public void setRenderKits(Map<String, RenderKit> renderKits) {
        this.renderKits = renderKits;
    }
}
