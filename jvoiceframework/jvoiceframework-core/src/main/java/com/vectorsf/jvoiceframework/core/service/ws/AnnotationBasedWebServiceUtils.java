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
	 * Busca la clase ciente WS (Con la anotación @WebService) correspondiente al interfaz a partir del targetnamespace
	 * @param portInterfaceClass
	 * @return
	 */
	@Cacheable(value="wsClients", key="#portInterfaceClass.getCanonicalName()")
	public Class<? extends javax.xml.ws.Service> getWSClientClass(Class<?> portInterfaceClass) throws WebServiceProviderException {
		
		
		String resultClassName = null;

		logger.debug(AnnotationBasedWebServiceUtilsMessages.DEBUG_GET_CLIENT_CLASS, portInterfaceClass.getCanonicalName());

		// Buscamos una clase anotada como @WebService en el mismo paquete que el interfaz del puerto solicitado y con el mismo targetNamespace		
		WebService annotation = portInterfaceClass.getAnnotation(WebService.class);
		Class<? extends javax.xml.ws.Service> clientClass = null;
		if (annotation != null) {
			clientClass = getWSClientClass(portInterfaceClass.getPackage(), annotation.targetNamespace());		
			resultClassName = clientClass.getCanonicalName();
		}
		else {
			logger.error(AnnotationBasedWebServiceUtilsMessages.ERROR_NO_WS_ANNOTATION_IN_PORT_INTERFACE, portInterfaceClass.getCanonicalName());
			throw new WebServiceProviderException();
		}

		logger.debug(AnnotationBasedWebServiceUtilsMessages.DEBUG_GET_CLIENT_CLASS_RETURN, portInterfaceClass.getCanonicalName(), resultClassName);
		return clientClass;
	}

	/**
	 * Busca una clase anotada como @WebService en un paquete y con un targetNamespace determinados 
	 * @param clientPackage
	 * @param targetNamespace
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Class<? extends javax.xml.ws.Service> getWSClientClass(Package clientPackage, String targetNamespace) {
		
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
