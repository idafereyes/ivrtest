package com.vectorsf.jvoiceframework.core.service.ws;

/**
 * Interfaz para el servicio proveedor de clientes para servicios web. 
 */
public interface WebServiceProvider {	
	
	<T> T getClient(Class<T> clazz) throws WebServiceProviderException; 
}
