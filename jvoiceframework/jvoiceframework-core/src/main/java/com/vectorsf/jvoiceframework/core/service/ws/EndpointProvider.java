package com.vectorsf.jvoiceframework.core.service.ws;

import javax.xml.namespace.QName;

/**
 * Interfaz para el servicio proveedor de endpoints para web services
 */
public interface EndpointProvider {
	
	/**
	 * Devuelve el endpoint asociado al nombre cualificado de un servicio web
	 * @param qname
	 * @return Devuelve null si no se encuentra el nombre del servicio
	 * @throws EndpointProviderException
	 */
	public String getEndpoint(QName qname);
	
	/**
	 * Devuelve el endpoint asociado al nombre y namespace de un servicio web
	 * @param nameSpace
	 * @param name
	 * @return Devuelve null si no se encuentra el nombre del servicio
	 * @throws EndpointProviderException
	 */
	public String getEndpoint(String nameSpace, String name);
}
