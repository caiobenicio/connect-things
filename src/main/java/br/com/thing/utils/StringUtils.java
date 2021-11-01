package br.com.thing.utils;

import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringUtils {

	public static boolean isNullOrEmpty(String str) {
		return str == null || str.isEmpty();
	}
	
	// Remove toda a acentuação da string substituindo por caracteres simples sem acento.
	public static String unaccent(String src) {
		return Normalizer.normalize(src, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
	
	public static String formatOMSDate(String dateStr){
        String result = null;
        try { 
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd"); 
            Date date = sdf.parse(dateStr); 
            result = sdf2.format(date);
        } catch (ParseException e) { 
           return null;
        }
        
        return result;
    }
	
	public static String formatDate(String date) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
		return sdf2.format(sdf.parse(date));
	}

	public static String lastDay(String date) throws ParseException {

		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, Integer.valueOf(date.split("-")[1]) - 1);
		c.set(Calendar.YEAR, Integer.valueOf(date.split("-")[0]));
		int ultimodia = c.getActualMaximum(Calendar.DAY_OF_MONTH);

		return String.valueOf(ultimodia);
	}

	public static String returnOnlyNumber(String data) {
		if (!isNullOrEmpty(data))
			return data.replaceAll("[^0-9]", "");
		return "";
	}
}
