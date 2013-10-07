package com.vectorsf.jvoiceframework.core.service.locution;

import java.util.Locale;

/**
 * Iterfaz para el servicio proveedor de locuciones
 * 
 * @author dmartina
 */
public interface LocutionProvider {
	
	/**
	 * Devuelve una frase a partir de su clave. Se utiliza un locale calculado por el servicio
	 * @param key Clave para la frase
	 * @param args Argumentos para componer la frase de forma dinamica
	 * @return Frase asociada a la clave y los argumentos proporcionados
	 */
	String getWording(Enum<?> key, Object... args) throws LocutionProviderException;
	
	/**
	 * Devuelve una frase a partir de su clave. Se utiliza el locale pasado como parámetro
	 * @param key Clave para la frase
	 * @param locale Locale para internacionalización
	 * @param args Argumentos para componer la frase de forma dinamica
	 * @return Frase asociada a la clave el locale y los argumentos porporcionados
	 */
	String getWording(Enum<?> key, Locale locale, Object... args) throws LocutionProviderException;
	
	String getAudioSrc(Enum<?> key) throws LocutionProviderException;

	String getAudioSrc(Enum<?> key, Locale locale) throws LocutionProviderException;
	
}
