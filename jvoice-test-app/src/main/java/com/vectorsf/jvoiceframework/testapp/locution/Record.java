package com.vectorsf.jvoiceframework.testapp.locution;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("com.vectorsf.jvoiceframework.testapp.locution.Record")
@LocaleData(defaultCharset="UTF8", value={@Locale(value="es_ES", charset="ISO8859_1"), @Locale(value="en_US")})
public enum Record {
	
	GET_NAME,
	THANKS;

}
