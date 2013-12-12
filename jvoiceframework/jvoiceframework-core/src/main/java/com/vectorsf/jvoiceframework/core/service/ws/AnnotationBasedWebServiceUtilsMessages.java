package com.vectorsf.jvoiceframework.core.service.ws;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("com.vectorsf.jvoiceframework.core.service.ws.AnnotationBasedWebServiceUtilsMessages")
@LocaleData(value={@Locale(value="es_ES", charset="ISO8859_1")})
public enum AnnotationBasedWebServiceUtilsMessages {

	DEBUG_GET_PORT_NAME_FROM_INTERFACE,
	DEBUG_GET_PORT_NAME_FROM_INTERFACE_RETURN,
	DEBUG_GET_CLIENT_INSTANCE,
	DEBUG_GET_CLIENT_INSTANCE_RETURN,
	ERROR_NO_WS_ANNOTATION_IN_PORT_INTERFACE,
	ERROR_CREATING_WS_CLIENT_INSTANCE,
	DEBUG_GET_ANNOTATED_WS_CLIENT_CLASS,
	DEBUG_GET_ANNOTATED_WS_CLIENT_CLASS_RETURN
	
}
