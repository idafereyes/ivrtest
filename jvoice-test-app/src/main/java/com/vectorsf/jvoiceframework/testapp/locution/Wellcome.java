package com.vectorsf.jvoiceframework.testapp.locution;
import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("com.vectorsf.jvoiceframework.testapp.locution.Wellcome")
@LocaleData(defaultCharset="UTF8", value={@Locale(value="es_ES", charset="ISO8859_1")})
public enum Wellcome {
	WELLCOME_RETAIL,
	WELLCOME_PYME_RETAIL,
	WELLCOME_ULINE_RETAIL;
}
