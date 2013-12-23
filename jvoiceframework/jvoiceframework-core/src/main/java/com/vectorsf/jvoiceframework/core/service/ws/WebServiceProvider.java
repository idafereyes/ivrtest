package com.vectorsf.jvoiceframework.core.service.ws;


/**
 * Interfaz para el servicio proveedor de clientes para servicios web. 
 */
public interface WebServiceProvider {	
	
	/**
	 * Crea una instancia del cliente del servicio web cumpliendo con el interfaz del servicio, e injecta la URL del endpoint indicado en el parámetro.
	 * El cliente generado tendrá asociados los resolve handlers disponibles (Logging, seguridad etc...)
	 * 
	 * @param clazz Interfaz del cliente WS
	 * @param endpoint Nombre del endpoint a utilizar. 
	 * @return
	 * @throws WebServiceProviderException
	 */
	<T> T getClient(Class<T> clazz, String endpoint) throws WebServiceProviderException; 
	
}
