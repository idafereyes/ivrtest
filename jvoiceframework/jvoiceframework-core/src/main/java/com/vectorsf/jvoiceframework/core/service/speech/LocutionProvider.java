package com.vectorsf.jvoiceframework.core.service.speech;

import java.util.Locale;

/**
 * Iterfaz para el servicio proveedor de locuciones
 * @author dmartina
 */
public interface LocutionProvider {
	
	/**
	 * Devuelve una frase a partir de su clave. Se utiliza un locale calculado por el servicio
	 * @param key Clave para la frase
	 * @param args Argumentos para componer la frase de forma dinamica
	 * @return Frase asociada a la clave y los argumentos proporcionados
	 */
	public <E extends Enum<?>> String getWording(E key, Object... args);
	
	/**
	 * Devuelve una frase a partir de su clave. Se utiliza el locale pasado como parámetro
	 * @param key Clave para la frase
	 * @param locale Locale para internacionalización
	 * @param args Argumentos para componer la frase de forma dinamica
	 * @return Frase asociada a la clave el locale y los argumentos porporcionados
	 */
	public <E extends Enum<?>> String getWording(E key, Locale locale, Object... args);
	
	/**
	 * Devuelve el nombre del recurso de una grabación audio. Se seleccionará el recurso asociado al locale calculado por el servicio
	 * @param key Clave para el recurso de audio
	 * @return Nombre del recurso de audio
	 */
	public <E extends Enum<?>> String getAudioSource(E key);
	
	/**
	 * Devuelve el nombre del recurso de una grabación audio
	 * @param key  Clave para el recurso de audio
	 * @param locale SE seleccionará el recurso asociado a este locale
	 * @return Nombre del recurso de audio
	 */
	public <E extends Enum<?>> String getAudioSource(E key, Locale locale);
}
