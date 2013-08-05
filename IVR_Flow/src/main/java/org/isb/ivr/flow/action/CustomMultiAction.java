package org.isb.ivr.flow.action;

import org.springframework.stereotype.Component;
import org.springframework.webflow.action.MultiAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;
@Component("customMultiAction")
public class CustomMultiAction extends MultiAction {
	
	public Event doit(String event, RequestContext context) {
       return new Event(this , event);
    }

}
