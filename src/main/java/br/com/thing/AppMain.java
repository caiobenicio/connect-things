package br.com.thing;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import br.com.thing.utils.AppContext;

@SpringBootApplication
public class AppMain {

    private static final Logger LOGGER = LogManager.getLogger(AppMain.class);

    public static void main(String[] args) throws UnknownHostException {
        ApplicationContext app = SpringApplication.run(AppContext.class, args);

        String applicationName = app.getEnvironment().getProperty("spring.application.name");
        String contextPath = app.getEnvironment().getProperty("server.contextPath");
        String port = app.getEnvironment().getProperty("server.port");
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        String brokerMqtt = app.getEnvironment().getProperty("broker.mqtt");

        LOGGER.info("\n|------------------------------------------------------------" +
                "\n| Application '" + applicationName + "' is running! Access URLs:" +
                "\n|   Local:     	   	http://127.0.0.1:" + port + contextPath +
                "\n|   External:   	   	http://" + hostAddress + ":" + port + contextPath +
                "\n|   BROKER MQTT: "+	brokerMqtt  							+
                "\n|------------------------------------------------------------");
    }

}
