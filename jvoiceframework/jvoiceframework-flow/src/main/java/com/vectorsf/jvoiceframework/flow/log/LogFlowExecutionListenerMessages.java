package com.vectorsf.jvoiceframework.flow.log;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("com.vectorsf.jvoiceframework.flow.log.LogFlowExecutionListenerMessages")
@LocaleData(@Locale(value="es_ES", charset="UTF8"))
public enum LogFlowExecutionListenerMessages {
	DEBUG_START_STATE_EXECUTION,
	DEBUG_END_STATE_EXECUTION,
	ERROR_FLOW_EXECUTION_EXCEPTION
}
