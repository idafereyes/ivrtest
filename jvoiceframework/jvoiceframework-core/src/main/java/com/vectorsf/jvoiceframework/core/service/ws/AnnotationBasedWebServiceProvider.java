package com.vectorsf.jvoiceframework.core.service.ws;

import javax.inject.Inject;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.vectorsf.jvoiceframework.core.log.Log;
import com.vectorsf.jvoiceframework.core.log.Logger;

@Service("webServiceProvider")
public class AnnotationBasedWebServiceProvider implements WebServiceProvider {

	@Log
	private Logger logger;
	
	@Inject
	private EndpointProvider endpointProvider; // Nos aisla de la obtención de los endposints de los WS Ports
	
	@Inject
	private HandlerChainProvider handlerChainProvider; // Nos aisla de la cadena de Handlers a aplicar en cada llamada
	
	@Inject
	private AnnotationBasedWebServiceUtils annotationBasedWebServiceUtils;
	
	public AnnotationBasedWebServiceUtils getAnnotationBasedWebServiceUtils() {
		return annotationBasedWebServiceUtils;
	}

	public void setAnnotationBasedWebServiceUtils(
			AnnotationBasedWebServiceUtils annotationBasedWebServiceUtils) {
		this.annotationBasedWebServiceUtils = annotationBasedWebServiceUtils;
	}

	public EndpointProvider getEndpointProvider() {
		return endpointProvider;
	}

	public void setEndpointProvider(EndpointProvider endpointProvider) {
		this.endpointProvider = endpointProvider;
	}
	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public HandlerChainProvider getHandlerChainProvider() {
		return handlerChainProvider;
	}

	public void setHandlerChainProvider(HandlerChainProvider handlerChainProvider) {
		this.handlerChainProvider = handlerChainProvider;
	}

	/**
	 * Obtiene el endpoint a aprtir del proveedor de endpoints
	 * @param clazz
	 * @param endpoint
	 * @return
	 * @throws WebServiceProviderException
	 */
	private String getEndpoint(String namespace, String endpoint) throws WebServiceProviderException {
		
		logger.debug(AnnotationBasedWebServiceProviderMessages.DEBUG_GET_ENDPOINT, namespace, endpoint);
		
		String endpointURL = endpointProvider.getEndpoint(new QName(namespace, endpoint));
		
		if (endpointURL == null)  {
			logger.error(AnnotationBasedWebServiceProviderMessages.ERROR_NO_ENDPOINT_FOUND, namespace, endpoint);
			throw new WebServiceProviderException();
		}
		
		logger.debug(AnnotationBasedWebServiceProviderMessages.DEBUG_GET_ENDPOINT_RETURN, namespace, endpoint, endpointURL);
		return endpointURL; 
	}

	@Override
	public <T> T getClient(Class<T> interfaceClass, String endpoint) throws WebServiceProviderException {
		T port = null;
		
		if (interfaceClass != null && endpoint != null)  {
			logger.debug(AnnotationBasedWebServiceProviderMessages.DEBUG_GET_CLIENT, interfaceClass.getCanonicalName());
			
			// Creamos la instancia del cliente WS para obtener el port que nos piden
			javax.xml.ws.Service client = getClientInstance(interfaceClass);
			
			// Creamos el puerto WS a devolver
			port = (T) client.getPort(interfaceClass);

			// Obtenemos el endpoint asociado al port
			String endpointURL = getEndpoint(client.getServiceName().getNamespaceURI(), endpoint);

			// Establecemos el endpoint con el servicio proveedor de endpoints
			BindingProvider bindingProvider = (BindingProvider) port;
			bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);	
			
		}
		else {
			logger.error(AnnotationBasedWebServiceProviderMessages.ERROR_GET_CLIENT_NULL_PARAMS);
			throw new WebServiceProviderException();
		}
		logger.debug(AnnotationBasedWebServiceProviderMessages.DEBUG_GET_CLIENT_RETURN, interfaceClass.getCanonicalName());
		return port;
	}

	/**
	 * Crea la instancia del cliente WS y le injecta los Resolve Handlers necesarios
	 * @param portInterfaceClass
	 * @return
	 */
	public javax.xml.ws.Service getClientInstance(Class<?> portInterfaceClass) throws WebServiceProviderException {
		
		javax.xml.ws.Service client = null;
		String resultClassName = null;

		logger.debug(AnnotationBasedWebServiceProviderMessages.DEBUG_GET_CLIENT_INSTANCE, portInterfaceClass.getCanonicalName());

		
		Class<? extends javax.xml.ws.Service> clientClass = annotationBasedWebServiceUtils.getWSClientClass(portInterfaceClass);					
		
		// Creamos la instancia del cliente WS, para poder pedir una instancia del port a partir del interfaz solicitado
		try {
			client = clientClass.newInstance();
			// Establecemos la cadena de handlers a aplicar en las llamadas (Logs, seguridad, etc.)
			// Estos handlers funcionarán de forma autónoma. Cada uno decide si debe aplicarse o no
			client.setHandlerResolver(handlerChainProvider);
			resultClassName = client.getClass().getCanonicalName();
		} catch (Exception e) {
			logger.error(AnnotationBasedWebServiceProviderMessages.ERROR_CREATING_WS_CLIENT_INSTANCE, portInterfaceClass.getCanonicalName(), e);
			throw new WebServiceProviderException(e);
		}	
		
		logger.debug(AnnotationBasedWebServiceProviderMessages.DEBUG_GET_CLIENT_INSTANCE_RETURN, portInterfaceClass.getCanonicalName(), resultClassName);
		return client;
	}
	

}
