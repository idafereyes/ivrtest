package com.vectorsf.jvoiceframework.flow.log;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("com.vectorsf.jvoiceframework.flow.log.LogFlowExecutionListenerMessages")
@LocaleData(@Locale(value="es_ES", charset="UTF8"))
public enum LogFlowExecutionListenerMessages {
	
	// INFO
	INFO_START_STATE_EXECUTION,
	INFO_END_STATE_EXECUTION,
	INFO_EVENT_TRANSITION,
	
	// ERROR
	ERROR_FLOW_EXECUTION_EXCEPTION
}
