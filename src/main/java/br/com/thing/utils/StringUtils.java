package br.com.thing.utils;

import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {

	public static boolean notNullOrEmpty(String str){
		
		return str != null && !str.isEmpty();
	}
	
	// Remove toda a acentuação da string substituindo por caracteres simples sem acento.
	public static String unaccent(String src) {
		return Normalizer
				.normalize(src, Normalizer.Form.NFD)
				.replaceAll("[^\\p{ASCII}]", "");
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
}
