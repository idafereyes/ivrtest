package com.vectorsf.jvoiceframework.isban.logger.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	/**
	 * Metodo para obtener la fecha y hora actual con el formato yyyy-MM-dd HH:mm:ss.SSS
	 * No recibe parametros.
	 * @return String con la fecha-hora actual con el formato "yyyy-MM-dd HH:mm:ss.SSS"
	 */
	public String obtenerFechaHoraActual(){
		
		String fecha_actual = null;
		Date dateactual = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		fecha_actual = sdf.format(dateactual);
		return fecha_actual;
	}
	

	
	/**
	 * Metodo para obtener la fecha y hora actual con el formato dd/MM/yyyy HH:mm:ss.SSS
	 * No recibe parametros.
	 * @return String con la fecha-hora actual con el formato "dd/MM/yyyy HH:mm:ss.SSS"
	 */
	public String obtenerFechaHoraActual_1(){
		
		String fecha_actual = null;
		Date dateactual = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
		fecha_actual = sdf.format(dateactual);		
		return fecha_actual;
		
	}
	
	/**
	 * Metodo para obtener la fecha y hora actual con el formato dd/MM/yyyy HH:mm:ss
	 * No recibe parametros.
	 * @return String con la fecha-hora actual con el formato "dd/MM/yyyy HH:mm:ss"
	 */
	public String obtenerFechaHoraActual_2(){
		
		String fecha_actual = null;
		Date dateactual = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		fecha_actual = sdf.format(dateactual);
		return fecha_actual;
	}
	
	/**
	 * Metodo que comprueba que una fecha introducida es correcta. 
	 * Parámetro de entrada: ha de tener el siguiente formato: ddmmyyyy
	 * @return true o false en función de si es correcta o no
	 * @deprecated
	 */
	
	
	public static boolean checkDate(String fecha){

		int day= Integer.parseInt(fecha.substring(0, 2));
		int month= Integer.parseInt(fecha.substring(2,4));
		int year = Integer.parseInt(fecha.substring(4));
		
		if (month < 1 || month > 12 )
			return false;
		else if (day < 1)
			return false;		
		else if (month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
			
		
			if (day > 31 )
				return false;
		}
		else if (month==4 || month==6 || month==9 || month==11){
			if (day >30)
				return false;
		}
		else{
			//Febrero
			//Si es bisiesto
			if ((year % 4 == 0) && !(year % 100 == 0 && year % 400 != 0)){
				if (day >29)
					return false;
			}
			else{
				if (day > 28)
					return false;
			}
		}
		return true;
					
	}
	
	/**
	 * Metodo que comprueba si una fecha con formato ddmm es anterior al día actual 
	 * Parámetro de entrada: ha de tener el siguiente formato: ddmm
	 * @return true o false en función de si es anterior o no
	 */
	
	public static boolean isDateDdmmPreviousThanToday(String fecha){
		Calendar c = Calendar.getInstance();
		String day=fecha.substring(0, 2);
		String month=fecha.substring(2);
				
		if(Integer.parseInt(month)>(c.get(Calendar.MONTH)+1)){
			return false;

		}else if(Integer.parseInt(month)==(c.get(Calendar.MONTH)+1)){
		//Si es igual comprobamos el día
		if(Integer.parseInt(day)>=c.get(Calendar.DAY_OF_MONTH)){
			return false;
		}else{
			return true;
		}
		}else{
			return true;
		}

	}


}
