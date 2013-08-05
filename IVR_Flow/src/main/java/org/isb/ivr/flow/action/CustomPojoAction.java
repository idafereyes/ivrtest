package org.isb.ivr.flow.action;

import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;
@Component("customPojoAction")
public class CustomPojoAction {
	// Podemos devolver lo que queramos y pasar parámetros
	public Event doit(String event, RequestContext context) {
       return new Event(this , event);
    }

}
