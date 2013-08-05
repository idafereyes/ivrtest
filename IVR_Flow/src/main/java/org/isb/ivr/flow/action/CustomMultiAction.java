package org.isb.ivr.flow.action;

import org.springframework.webflow.action.MultiAction;
import org.springframework.webflow.execution.Action;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

public class CustomMultiAction extends MultiAction {
	
	public Event doit(String event, RequestContext context) {
       return new Event(this , event);
    }

}
