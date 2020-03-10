package br.com.thing;

import java.net.SocketException;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import br.com.thing.utils.AppContext;
import br.com.thing.utils.IpAddress;

@SpringBootApplication
public class AppMain {

   // private static final Logger LOGGER = LogManager.getLogger(AppMain.class);
    private static final Logger logger = LoggerFactory.getLogger(AppMain.class);

    public static void main(String[] args) throws UnknownHostException, SocketException {
        ApplicationContext app = SpringApplication.run(AppContext.class, args);

        String applicationName = app.getEnvironment().getProperty("spring.application.name");
        String contextPath = app.getEnvironment().getProperty("server.contextPath");
        String port = app.getEnvironment().getProperty("server.port");
        String hostAddress = IpAddress.getinstance().getIpExterno();
        
        logger.info("\n|------------------------------------------------------------" +
                "\n| Application '" + applicationName + "' is running! Access URLs:" +
                "\n|   Local:      http://127.0.0.1:" + port + contextPath +
                "\n|   External:   http:/" + hostAddress + ":" + port + contextPath +
                "\n|------------------------------------------------------------");

        
    }
   
}
