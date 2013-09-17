package com.vectorsf.jvoiceframework.testapp.locution;
import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("com.vectorsf.jvoiceframework.testapp.locution.Wellcome")
@LocaleData(defaultCharset="UTF8", value={@Locale(value="es_ES", charset="ISO8859_1"), @Locale(value="en_EN")})

public enum Wellcome {
	BIENVENIDA_RETAIL,
	BIENVENIDA_PYMES_RETAIL,
	BIENVENIDA_ULINE_RETAIL;
}
