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

public abstract class AbstractRenderer {
	
    protected abstract String render(Output output, String flowURL);
    protected abstract String render(Input prompt, String flowURL);
    protected abstract String render(BlindTransfer blindTx, String flowURL);
    protected abstract String render(ConsultationTransfer consultationTx, String flowURL);
    protected abstract String render(BridgeTransfer bridgeTx, String flowURL);
    protected abstract String render(Record record, String flowURL);
    protected abstract String render(End end, String flowURL);
    protected abstract String renderStartPage();
    protected abstract String renderEmptyPage(String flowURL);
    protected abstract String renderEndPage();
    
    private String view;
	
    public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}
	
	public String render(List<Object> states, String flowURL) {
	      
		StringBuilder code = new StringBuilder();
		
		if ((states != null) && (!states.isEmpty())){
			
			code.append(renderStartPage());			
			
			for (Object element: states){
			  if (element instanceof Input) {
			      code.append(render((Input)element, flowURL));
			  }else if (element instanceof Output) {
			      code.append(render((Output)element, flowURL));            
			  }else if (element instanceof Transfer) {
			      code.append(render((Transfer) element, flowURL));
			  }else if (element instanceof End) {
			      code.append(render((End) element, flowURL));
			  }else if (element instanceof Record) {
				  code.append(render((Record) element, flowURL));
			  } 
			}
		  
			code.append(renderEndPage());
		} else {
			//En caso de que la lista de estados esté vacía renderizamos 
			//una página vacia con un enlace al siguiente estado
			//para que no falle.
			code.append(renderEmptyPage(flowURL));
		}
      
      return code.toString();
	}
	
	public String render(Transfer transfer, String flowURL) {
		
		StringBuilder renderCode = new StringBuilder();
		
		if (transfer instanceof BlindTransfer){
			renderCode.append(render((BlindTransfer)transfer, flowURL));
		}else if (transfer instanceof ConsultationTransfer){
			renderCode.append(render((ConsultationTransfer) transfer, flowURL));
		}else if (transfer instanceof BridgeTransfer){
			renderCode.append(render((BridgeTransfer) transfer, flowURL));			
		}
		return renderCode.toString();
	}


}
