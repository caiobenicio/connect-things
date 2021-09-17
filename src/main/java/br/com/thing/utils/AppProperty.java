package br.com.thing.utils;
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.context.annotation.PropertySource;
 import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.yml")
public class AppProperty {

    @Value("${security.password.secret}")
    private String secret;

    public AppProperty() {
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

}