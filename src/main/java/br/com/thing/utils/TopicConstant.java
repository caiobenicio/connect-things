package br.com.thing.utils;

public final class TopicConstant {

	// DISPOSITIVO
	
	public static String DISPOSITIVO_SALA_LUZ = "casa/sala/luz";
	public static String DISPOSITIVO_SALA_SOM = "casa/sala/som";
	public static String DISPOSITIVO_SALA_TV = "casa/sala/tv";
	
	public static String DISPOSITIVO_COZINHA_LUZ = "casa/cozinha/luz";
	
	public static String DISPOSITIVO_QUARTO_LUZ = "casa/quarto/luz";
	public static String DISPOSITIVO_QUARTO_TV = "casa/quarto/tv";
	
	public static String DISPOSITIVO_BANHEIRO_LUZ = "casa/banheiro/luz";
	
	public static String DISPOSITIVO_VARANDA_LUZ = "casa/varanda/luz";
	
	public static String DISPOSITIVO_GARANGEM_LUZ = "casa/garagem/luz";
	
	
	// SENSOR
	
	public static String SENSOR_GARAGEM_PRESENCA = "casa/garagem/presenca";
	public static String SENSOR_SALA_TEMPERATURA = "appweb/wemosd1/casa/sala/temperatura";
	public static String SENSOR_SALA_UMIDADE = "appweb/wemosd1/casa/sala/umidade";
	
	// TOPIC BASE MQTT 
	
	public static String APPWEB_MQTT = "appweb/mqtt/+/+";
	
	private TopicConstant() {}
}
