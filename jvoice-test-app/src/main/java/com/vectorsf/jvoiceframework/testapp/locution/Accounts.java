package com.vectorsf.jvoiceframework.testapp.locution;
import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("com.vectorsf.jvoiceframework.testapp.locution.Accounts")
@LocaleData(defaultCharset="UTF8", value={@Locale(value="es_ES", charset="ISO8859_1"), @Locale(value="en_US")})
public enum Accounts {
	ACCOUNT_SELECTION_PT1_A,
	ACCOUNT_SELECTION_PT1_C,
	ACCOUNT_SELECTION_PT2_A,
	ACCOUNT_SELECTION_PT2_C,
	ACCOUNT_SELECTION_PT3_A,
	ACCOUNT_SELECTION_PT3_C,
	REMAINING_ACCOUNT_SELECTION,
	ACCOUNT_SELECTION_NM,
	ACCOUNT_SELECTION_NI,
	BALANCE;
}
