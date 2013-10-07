package com.vectorsf.jvoiceframework.core.service.locution;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("com.vectorsf.jvoiceframework.core.service.locution.Cal10NLocutionProviderMessages")
@LocaleData(value={@Locale(value="es_ES", charset="ISO8859_1")})
public enum Cal10NLocutionProviderMessages {
	DEBUG_GET_WORDING,
	DEBUG_GET_WORDING_RETURN,
	ERROR_GET_WORDING,
	
	DEBUG_GET_AUDIO_SRC,
	DEBUG_GET_AUDIO_SRC_RETURN,
	ERROR_GET_AUDIO_SRC
}
