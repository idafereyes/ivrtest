package com.vectorsf.jvoiceframework.flow.render;

import java.util.List;

import com.vectorsf.jvoiceframework.core.bean.End;
import com.vectorsf.jvoiceframework.core.bean.Input;
import com.vectorsf.jvoiceframework.core.bean.Output;
import com.vectorsf.jvoiceframework.core.bean.Record;
import com.vectorsf.jvoiceframework.core.bean.Transfer;

/**
 * Interfaz a cumplir por los renderers IVR
 * @author dmartina
 */
public interface Renderer {
	String render(List<Object> states, String flowURL);
    String render(Output output, String flowURL);
    String render(Input prompt, String flowURL);
    String render(Transfer transfer, String flowURL);
    String render(Record record, String flowURL);
    String render(End end, String flowURL);
    String renderStartPage();
    String renderEndPage();
    String getView();
}
