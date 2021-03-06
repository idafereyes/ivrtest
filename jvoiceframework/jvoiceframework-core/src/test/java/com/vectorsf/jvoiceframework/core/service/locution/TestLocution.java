package com.vectorsf.jvoiceframework.core.service.locution;
import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("com.vectorsf.jvoiceframework.core.service.locution.TestLocution")
@LocaleData(defaultCharset="UTF8", value={@Locale(value="es_ES", charset="ISO8859_1"), @Locale(value="en_US")})
public enum TestLocution {
	TEST_WORDING_KEY,
	TEST_WORDING_KEY_WITH_ARGS,
	TEST_AUDIO_SRC_KEY
}
