package com.vectorsf.jvoiceframework.flow.render;

import java.util.List;

import com.vectorsf.jvoiceframework.core.bean.BlindTransfer;
import com.vectorsf.jvoiceframework.core.bean.BridgeTransfer;
import com.vectorsf.jvoiceframework.core.bean.ConsultationTransfer;
import com.vectorsf.jvoiceframework.core.bean.End;
import com.vectorsf.jvoiceframework.core.bean.Input;
import com.vectorsf.jvoiceframework.core.bean.Output;
import com.vectorsf.jvoiceframework.core.bean.Record;
import com.vectorsf.jvoiceframework.core.bean.Transfer;
import com.vectorsf.jvoiceframework.core.log.Logger;

/**
 * Interfaz a cumplir por los renderers IVR
 * @author dmartina
 */
public interface Renderer {
	String render(List<Object> states, String flowURL, String contextPath);
    String render(Output output, String flowURL, String contextPath);
    String render(Input prompt, String flowURL, String contextPath);
    String render(Transfer transfer, String flowURL, String contextPath);
    String render(BlindTransfer blindTx, String flowURL, String contextPath);
    String render(ConsultationTransfer consultationTx, String flowURL, String contextPath);
    String render(BridgeTransfer bridgeTx, String flowURL, String contextPath);
    String render(Record record, String flowURL, String contextPath);
    String render(End end, String flowURL);
    String renderStartPage();
    String renderEndPage();
    String renderEmptyPage(String flowURL);
    String getView();
    Logger getLogger();
    void setLogger(Logger logger);

}
