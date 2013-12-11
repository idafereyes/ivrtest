package com.vectorsf.jvoiceframework.core.service.ws;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("com.vectorsf.jvoiceframework.core.service.ws.XMLBasedEndpointProviderMessages")
@LocaleData(value={@Locale(value="es_ES", charset="ISO8859_1")})
public enum XMLBasedEndpointProviderMessages {
	DEBUG_INITIALIZE,
	DEBUG_ENDPOINT_ADDED,
	DEBUG_INITIALIZE_RETURN,
	DEBUG_GET_ENDPOINT,
	DEBUG_GET_ENDPOINT_RETURN,
	WARN_NO_ENDPOINTS_RESOURCE_DEFINED
}
