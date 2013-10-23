package com.vectorsf.jvoiceframework.testapp.locution;
import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("com.vectorsf.jvoiceframework.testapp.locution.Menu")
@LocaleData(defaultCharset="UTF-8", value={@Locale(value="es_ES", charset="UTF-8"), @Locale(value="en_US")})
public enum Menu {
	MAIN_MENU_PT1,
	MAIN_MENU_PT1_SRC,
	MAIN_MENU_PT2,
	MAIN_MENU_PT2_SRC,
	MAIN_MENU_PT3,
	MAIN_MENU_PT3_SRC,
	MAIN_MENU_NI,
	MAIN_MENU_NI_SRC,
	MAIN_MENU_NM,
	MAIN_MENU_NM_SRC,
	ACCOUNTS_MENU_PT1,
	ACCOUNTS_MENU_PT2,
	ACCOUNTS_MENU_PT3,
	ACCOUNTS_MENU_NI,
	ACCOUNTS_MENU_NM,
	NO_MENU_BACK,
	NO_MAIN_MENU;
}
