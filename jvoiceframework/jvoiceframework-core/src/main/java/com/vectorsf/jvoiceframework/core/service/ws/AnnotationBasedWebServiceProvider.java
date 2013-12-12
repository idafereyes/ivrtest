package com.vectorsf.jvoiceframework.core.service.ws;

import javax.inject.Inject;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

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
	 * Calcula el endpoint de un puerto WS usando el servicio de registro de endpoints
	 * @param portInterfaceClass
	 * @return
	 */
	private String getEndpoint(Class<?> portInterfaceClass) throws WebServiceProviderException {
		// Extraemos el nombre cualificado de las anotaciones del interfaz del puerto WS para buscar su endpoint
		logger.debug(AnnotationBasedWebServiceProviderMessages.DEBUG_GET_ENDPOINT, portInterfaceClass.getCanonicalName());
		QName portName = annotationBasedWebServiceUtils.getPortName(portInterfaceClass);	
		String endpoint = endpointProvider.getEndpoint(portName);
		
		if (endpoint == null)  {
			logger.error(AnnotationBasedWebServiceProviderMessages.ERROR_NO_ENDPOINT_FOUND, portName.getNamespaceURI(), portName.getLocalPart());
			throw new WebServiceProviderException();
		}
		
		logger.debug(AnnotationBasedWebServiceProviderMessages.DEBUG_GET_ENDPOINT_RETURN, portInterfaceClass.getCanonicalName(), endpoint);
		return endpoint; 
	}
	

	@Override
	public <T> T getClient(Class<T> portInterfaceClass) throws WebServiceProviderException {
		T port = null;
		
		if (portInterfaceClass != null)  {
			logger.debug(AnnotationBasedWebServiceProviderMessages.DEBUG_GET_CLIENT, portInterfaceClass.getCanonicalName());
			
			// Creamos la instancia del cliente WS para obtener el port que nos piden
			javax.xml.ws.Service client = annotationBasedWebServiceUtils.getClientInstance(portInterfaceClass, handlerChainProvider);
			
			// Creamos el puerto WS a devolver
			port =  (T) client.getPort(portInterfaceClass);
			
			// Obtenemos el endpoint asociado al port
			String endpoint = getEndpoint(portInterfaceClass);

			// Establecemos el endpoint con el servicio proveedor de endpoints
			BindingProvider bindingProvider = (BindingProvider) port;
			bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);	
			
		}
		else {
			logger.error(AnnotationBasedWebServiceProviderMessages.ERROR_GET_CLIENT_NULL_CLASS);
			throw new WebServiceProviderException();
		}
		logger.debug(AnnotationBasedWebServiceProviderMessages.DEBUG_GET_CLIENT_RETURN, portInterfaceClass.getCanonicalName());
		return port;
	}
	
}
