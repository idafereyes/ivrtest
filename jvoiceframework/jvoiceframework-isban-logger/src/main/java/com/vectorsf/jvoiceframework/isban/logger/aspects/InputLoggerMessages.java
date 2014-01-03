package com.vectorsf.jvoiceframework.isban.logger.aspects;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("com.vectorsf.jvoiceframework.isban.logger.aspects.InputLoggerMessages")
@LocaleData(value={@Locale(value="es_ES", charset="ISO8859_1")})
public enum InputLoggerMessages {
	DEBUG_ASPECT_INPUT_LOGGER_INIT,
	DEBUG_VIEW_STATE_AND_MODEL_CHECKED,
	DEBUG_LAST_INPUT_RESULT_CHECKED,
	DEBUG_SPEECH_EVENT,
	DEBUG_SPEECH_EVENT_ATTR,
	DEBUG_DIALOGUE_EVENT,
	DEBUG_DIALOGUE_EVENT_ATTR,
	DEBUG_ASPECT_INPUT_LOGGER_END,
	
}
