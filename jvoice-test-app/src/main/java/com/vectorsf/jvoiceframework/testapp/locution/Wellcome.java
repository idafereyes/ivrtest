package com.vectorsf.jvoiceframework.testapp.locution;
import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("com.vectorsf.testapp.locution.Wellcome")
@LocaleData(defaultCharset="UTF8", value={@Locale(value="es_ES", charset="ISO8859_1"), @Locale(value="en_EN")})

public enum Wellcome {
	puerto_rico_es_US_SAN_BC_BIENVENIDA_RETAIL,
	puerto_rico_es_US_SAN_BC_BIENVENIDA_PYMES_RETAIL,
	puerto_rico_es_US_SAN_BC_BIENVENIDA_ULINE_RETAIL;
}
