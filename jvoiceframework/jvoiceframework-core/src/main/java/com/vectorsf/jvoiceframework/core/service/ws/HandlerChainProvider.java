package com.vectorsf.jvoiceframework.core.service.ws;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

import org.springframework.stereotype.Component;

@Component("handlerChainProvider")
public class HandlerChainProvider implements HandlerResolver {

	@SuppressWarnings("rawtypes")
	private List<Handler> handlers = new ArrayList<Handler>();
	
	@SuppressWarnings("rawtypes")
	public List<Handler> getHandlers() {
		return handlers;
	}

	@SuppressWarnings("rawtypes")
	public void setHandlers(List<Handler> handlers) {
		this.handlers = handlers;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Handler> getHandlerChain(PortInfo portInfo) {
		return handlers;
	}	
}