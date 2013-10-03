package com.vectorsf.jvoiceframework.testapp.locution;
import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("com.vectorsf.jvoiceframework.testapp.locution.Menu")
@LocaleData(defaultCharset="UTF8", value={@Locale(value="es_ES", charset="ISO8859_1"), @Locale(value="en_US")})
public enum Menu {
	MAIN_MENU_PT1,
	MAIN_MENU_PT2,
	MAIN_MENU_PT3,
	MAIN_MENU_NI,
	MAIN_MENU_NM,
	ACCOUNTS_MENU_PT1,
	ACCOUNTS_MENU_PT2,
	ACCOUNTS_MENU_PT3,
	ACCOUNTS_MENU_NI,
	ACCOUNTS_MENU_NM,
	NO_MENU_BACK,
	NO_MAIN_MENU;
}
