package com.vectorsf.jvoiceframework.core.service.ws;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("com.vectorsf.jvoiceframework.core.service.ws.AnnotationBasedWebServiceProviderMessages")
@LocaleData(value={@Locale(value="es_ES", charset="ISO8859_1")})
public enum AnnotationBasedWebServiceProviderMessages {
	DEBUG_GET_ENDPOINT,
	DEBUG_GET_ENDPOINT_RETURN,
	DEBUG_GET_CLIENT,
	DEBUG_GET_CLIENT_RETURN,
	ERROR_GET_CLIENT_NULL_CLASS,
	ERROR_NO_ENDPOINT_FOUND
}
