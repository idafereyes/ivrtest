package com.vectorsf.jvoiceframework.testapp.locution;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("com.vectorsf.jvoiceframework.testapp.locution.Transfer")
@LocaleData(defaultCharset="UTF8", value={@Locale(value="es_ES", charset="ISO8859_1")})
public enum Transfer {
	TRANSFER_INFO_COMUN,
	TRANSFER,
	TRANSFER_EXTERNO;

}
