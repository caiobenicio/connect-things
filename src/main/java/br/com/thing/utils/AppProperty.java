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

	@Value("${broker.mqtt}")
	private String brokerMqtt;

	@Value("${mosquitto.enabled}")
	private String brokerEnabled;

	@Value("${security.password.secret}")
	private String secret;

	public AppProperty() {

	}

	public static AppProperty getinstance() {
		return instance == null ? new AppProperty() : instance;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppDescription() {
		return appDescription;
	}

	public void setAppDescription(String appDescription) {
		this.appDescription = appDescription;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getBrokerMqtt() {
		return brokerMqtt;
	}

	public void setBrokerMqtt(String brokerMqtt) {
		this.brokerMqtt = brokerMqtt;
	}

	public String getSecret() {
		return secret;
	}

	public Boolean getBrokerEnabled() {
		return Boolean.valueOf(brokerEnabled);
	}

	public void setBrokerEnabled(String brokerEnabled) {
		this.brokerEnabled = brokerEnabled;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

}