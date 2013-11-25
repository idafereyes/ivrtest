package com.vectorsf.jvoiceframework.core.service.locution;

import java.util.Locale;

import com.vectorsf.jvoiceframework.core.bean.Wording;

/**
 * Interfaz para el servicio proveedor de locuciones
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
	Wording getWording(Enum<?> key, Object... args) throws LocutionProviderException;
	
	/**
	 * Devuelve una frase a partir de su clave. Se utiliza el locale pasado como parámetro
	 * @param key Clave para la frase
	 * @param locale Locale para internacionalización
	 * @param args Argumentos para componer la frase de forma dinamica
	 * @return Frase asociada a la clave el locale y los argumentos porporcionados
	 */
	Wording getWording(Enum<?> key, Locale locale, Object... args) throws LocutionProviderException;
	
	/**
	 * Devuelve el nombre del audio a partir de su clave. Se utiliza un locale calculado por el servicio.
	 * @param key Clave para el nombre del audio.
	 * @return nombre del audio asociado a la clave.
	 */
	String getAudioSrcI18n(Enum<?> key, String module) throws LocutionProviderException;

	/**
	 * Devuelve el nombre del audio a partir de su clave y para el locale pasado como parámetro.
	 * @param key Clave para el nombre del audio.
	 * @param locale Locale para internacionalización
	 * @return nombre del audio asociado a la clave y el locale pasado como parámetro.
	 */
	String getAudioSrcI18n(Enum<?> key, String module, Locale locale) throws LocutionProviderException;
	
	String getAudioSrc(String src, String module) throws LocutionProviderException;
	
}
