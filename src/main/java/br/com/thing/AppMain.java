package br.com.thing;

import java.net.SocketException;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import br.com.thing.context.ContextInitialized;
import br.com.thing.utils.IpAddressUtils;

@SpringBootApplication
public class AppMain {
	private static final Logger logger = LoggerFactory.getLogger(AppMain.class);

	public static void main(String[] args) throws UnknownHostException, SocketException {
		ApplicationContext appContext = SpringApplication.run(ContextInitialized.class, args);

		String mqtt = appContext.getEnvironment().getProperty("server.mosquitto.enabled");
		String applicationName = appContext.getEnvironment().getProperty("spring.application.name");
		String contextPath = appContext.getEnvironment().getProperty("server.servlet.context-path");
		String port = appContext.getEnvironment().getProperty("server.port");
		String hostAddress = IpAddressUtils.getinstance().getIpExternal();

		logger.info("\n|------------------------------------------------------------" + "\n|    "
					+ "Application '" + applicationName + "' is running! Access URLs:" + "\n|   "
					+ "Local:      http://127.0.0.1:" + port + contextPath + "\n|   "
					+ "External:   http:/" + hostAddress + ":" + port + contextPath
					+ "\n|------------------------------------------------------------");

	}

}
