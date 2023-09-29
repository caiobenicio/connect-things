package br.com.thing.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.yml")
public class AppProperty {
	private static AppProperty instance = new AppProperty();

	@Value("${spring.application.name}")
	private String appName;

	@Value("${spring.application.description}")
	private String appDescription;

	@Value("${spring.application.version}")
	private String appVersion;
	
	@Value("${security.password.secret}")
	private String secret;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddlAuto;
	
	@Value("${mqtt.enabled}")
	private String mqttEnabled;
	
	@Value("${mqtt.url}")
	private String mqttUrl;	
	
	public AppProperty() {}

	public static AppProperty getinstance() {
		return instance == null ? new AppProperty() : instance;
	}

	public String getAppName() {
		return appName;
	}

	public String getAppDescription() {
		return appDescription;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public String getSecret() {
		return secret;
	}

	public Boolean getMqttEnabled() {
		return Boolean.valueOf(mqttEnabled);
	}

	public String getMqttUrl() {
		return mqttUrl;
	}

	public String getDdlAuto() {
		return ddlAuto;
	}

}