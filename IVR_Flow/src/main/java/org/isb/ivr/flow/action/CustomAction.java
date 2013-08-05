package org.isb.ivr.flow.action;

import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.Action;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

@Component("customAction") // Por defecto @Scope("singleton")
public class CustomAction implements Action {
	
	public Event execute(RequestContext context) {
		
	   	
		
       return new Event(this , "customEvent");
    }

}
