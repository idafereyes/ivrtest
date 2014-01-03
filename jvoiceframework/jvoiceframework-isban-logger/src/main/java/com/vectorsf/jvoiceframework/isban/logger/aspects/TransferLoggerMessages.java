package com.vectorsf.jvoiceframework.isban.logger.aspects;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("com.vectorsf.jvoiceframework.isban.logger.aspects.TransferLoggerMessages")
@LocaleData(value={@Locale(value="es_ES", charset="ISO8859_1")})
public enum TransferLoggerMessages {
	DEBUG_ASPECT_TRANSFER_LOGGER_INIT,
	DEBUG_ASPECT_TRANSFER_LOGGER_END,
	
}
