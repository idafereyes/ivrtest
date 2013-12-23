package com.vectorsf.jvoiceframework.core.service.ws;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.vectorsf.jvoiceframework.core.log.Log;
import com.vectorsf.jvoiceframework.core.log.Logger;

@Service("endpointProvider")
public class XMLBasedEndpointProvider implements EndpointProvider {
	
	@Log
	private Logger logger;

	// Colección de endpoints. Clave = nombre cualificado del ws, Valor = URL del endpoint
	private Map<QName, Endpoint> endpoints = new HashMap<QName, Endpoint>();

	// Fichero desde donde se extraen los endpoints asociados a los ws
	private String resource;
	
	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}
	
	@PostConstruct
	public void initialize() throws JAXBException, IOException {		
		logger.debug(XMLBasedEndpointProviderMessages.DEBUG_INITIALIZE, resource);
		
		if (resource != null) {
		 	JAXBContext jc = JAXBContext.newInstance(EndpointCollection.class);	
		 	Resource endpointsResource = new ClassPathResource(resource);
			InputStream is = endpointsResource.getInputStream();
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			 
			EndpointCollection endpointCollection = (EndpointCollection) unmarshaller.unmarshal(is);
			if (endpointCollection != null  && endpointCollection.getValues() != null ) {
				for (Endpoint endpoint : endpointCollection.getValues()) {
					endpoints.put(new QName(endpoint.getNamespace(), endpoint.getName()), endpoint);
					logger.debug(XMLBasedEndpointProviderMessages.DEBUG_ENDPOINT_ADDED, endpoint.getNamespace(), endpoint.getName(), endpoint.getUrl());
				}
			}	
		}
		else{
			logger.warn(XMLBasedEndpointProviderMessages.WARN_NO_ENDPOINTS_RESOURCE_DEFINED, endpoints.size());
		}
		logger.debug(XMLBasedEndpointProviderMessages.DEBUG_INITIALIZE_RETURN, endpoints.size());
	}
	
	@Override
	public String getEndpoint(QName qname) {
		if(logger.isDebugEnabled()){
			if (qname == null) {
				logger.debug(XMLBasedEndpointProviderMessages.DEBUG_GET_ENDPOINT, null, null);
			}
			else {
				logger.debug(XMLBasedEndpointProviderMessages.DEBUG_GET_ENDPOINT, qname.getNamespaceURI(), qname.getLocalPart());
			}
		}	
		String url = null;
		if(endpoints.get(qname) != null){
			url = endpoints.get(qname).getUrl();
		}
		logger.debug(XMLBasedEndpointProviderMessages.DEBUG_GET_ENDPOINT_RETURN, qname.getNamespaceURI(), qname.getLocalPart(), url);
		return url;
	}

	@Override
	public String getEndpoint(String nameSpace, String name) {
		String url = null;
		QName qname = new QName(nameSpace, name);
		if(endpoints.get(qname) != null){
			url = endpoints.get(qname).getUrl();
		}
		return url;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
		
	}
}