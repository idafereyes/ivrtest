package com.vectorsf.jvoiceframework.core.service.ws;

import java.util.Set;

import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.handler.HandlerResolver;

import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.vectorsf.jvoiceframework.core.log.Log;
import com.vectorsf.jvoiceframework.core.log.Logger;

@Service("annotationBasedWebServiceUtils")
public class AnnotationBasedWebServiceUtils  {

	@Log
	private Logger logger;
	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	/**
	 * Inspecciona la anotación @WebService para obtener el nombre del puerto
	 * @param portInterfaceClass
	 * @return
	 */
	
	@Cacheable(value="wsPortNames", key="#portInterfaceClass.getCanonicalName()")
	public QName getPortName(Class<?> portInterfaceClass) {	
		logger.debug(AnnotationBasedWebServiceUtilsMessages.DEBUG_GET_PORT_NAME_FROM_INTERFACE, portInterfaceClass.getCanonicalName());
		WebService annotation = portInterfaceClass.getAnnotation(WebService.class);
		QName portName = new QName(annotation.targetNamespace(), annotation.name());
		logger.debug(AnnotationBasedWebServiceUtilsMessages.DEBUG_GET_PORT_NAME_FROM_INTERFACE_RETURN, portInterfaceClass.getCanonicalName(), portName.getNamespaceURI(), portName.getLocalPart());
		return portName;
	}
	
	/**
	 * Crea la instancia del cliente WS y le injecta los Resolve Handlers necesarios
	 * @param portInterfaceClass
	 * @return
	 */
	@Cacheable(value="wsClients", key="#portInterfaceClass.getCanonicalName()")
	public javax.xml.ws.Service getClientInstance(Class<?> portInterfaceClass, HandlerResolver handlerResolver) throws WebServiceProviderException {
		
		javax.xml.ws.Service client = null;
		String resultClassName = null;

		logger.debug(AnnotationBasedWebServiceUtilsMessages.DEBUG_GET_CLIENT_INSTANCE, portInterfaceClass.getCanonicalName());

		// Buscamos una clase anotada como @WebService en el mismo paquete que el interfaz del puerto solicitado y con el mismo targetNamespace		
		WebService annotation = portInterfaceClass.getAnnotation(WebService.class);
		
		if (annotation != null) {
			Class<? extends javax.xml.ws.Service> clientClass = getAnnotatedWSClientClass(portInterfaceClass.getPackage(), annotation.targetNamespace());					
			
			// Creamos la instancia del cliente WS, para poder pedir una instancia del port a partir del interfaz solicitado
			try {
				client = clientClass.newInstance();
				// Establecemos la cadena de handlers a aplicar en las llamadas (Logs, seguridad, etc.)
				// Estos handlers funcionarán de forma autónoma. Cada uno decide si debe aplicarse o no
				client.setHandlerResolver(handlerResolver);
				resultClassName = client.getClass().getCanonicalName();
			} catch (Exception e) {
				logger.error(AnnotationBasedWebServiceUtilsMessages.ERROR_CREATING_WS_CLIENT_INSTANCE, portInterfaceClass.getCanonicalName(), e);
				throw new WebServiceProviderException(e);
			}	
		}
		else {
			logger.error(AnnotationBasedWebServiceUtilsMessages.ERROR_NO_WS_ANNOTATION_IN_PORT_INTERFACE, portInterfaceClass.getCanonicalName());
			throw new WebServiceProviderException();
		}

		logger.debug(AnnotationBasedWebServiceUtilsMessages.DEBUG_GET_CLIENT_INSTANCE_RETURN, portInterfaceClass.getCanonicalName(), resultClassName);
		return client;
	}

	/**
	 * Busca una clase anotada como @WebService en un paquete y con un targetNamespace determinados 
	 * @param clientPackage
	 * @param targetNamespace
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Class<? extends javax.xml.ws.Service> getAnnotatedWSClientClass(Package clientPackage, String targetNamespace) {
		
		logger.debug(AnnotationBasedWebServiceUtilsMessages.DEBUG_GET_ANNOTATED_WS_CLIENT_CLASS, clientPackage.getName(), targetNamespace);

		Class<? extends javax.xml.ws.Service> resultClass = null;
		String resultClassName = null;
		
		Reflections reflections = new Reflections(clientPackage.getName(), new TypeAnnotationsScanner()); 
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(WebServiceClient.class);
		 
		for (Class<?> clientClass : annotated) {
			WebServiceClient annotation = clientClass.getAnnotation(WebServiceClient.class);
		    if (annotation.targetNamespace().equals(targetNamespace)) {
		    	resultClass = (Class<? extends javax.xml.ws.Service>) clientClass;
		    	resultClassName = resultClass.getCanonicalName();
		    	break;
		    }
		}
		
		logger.debug(AnnotationBasedWebServiceUtilsMessages.DEBUG_GET_ANNOTATED_WS_CLIENT_CLASS_RETURN, targetNamespace, resultClassName);
		return resultClass;
	}

	
}
