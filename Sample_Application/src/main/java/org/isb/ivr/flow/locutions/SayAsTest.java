package org.isb.ivr.flow.locutions;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("org.isb.ivr.flow.locutions.SayAsTest")
@LocaleData(defaultCharset="UTF8", value={@Locale(value="es_ES", charset="ISO8859_1")})
public enum SayAsTest {
	SAY_BOOLEAN,
	SAY_DATE,
	SAY_DIGITS,
	SAY_CURRENCY,
	SAY_NUMBER,
	SAY_PHONE,
	SAY_TIME;

}

