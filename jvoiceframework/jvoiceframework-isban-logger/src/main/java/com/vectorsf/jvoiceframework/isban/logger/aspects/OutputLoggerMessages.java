package com.vectorsf.jvoiceframework.isban.logger.aspects;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("com.vectorsf.jvoiceframework.isban.logger.aspects.OutputLoggerMessages")
@LocaleData(value={@Locale(value="es_ES", charset="ISO8859_1")})
public enum OutputLoggerMessages {
	DEBUG_ASPECT_OUTPUT_LOGGER_INIT,
	DEBUG_ASPECT_OUTPUT_LOGGER_END,
	
}
