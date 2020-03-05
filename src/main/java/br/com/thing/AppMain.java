package br.com.thing;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import br.com.thing.mqtt.InitMqtt;
import br.com.thing.utils.AppContext;

@SpringBootApplication
public class AppMain {

    private static final Logger LOGGER = LogManager.getLogger(AppMain.class);
    static String ip = null;

    public static void main(String[] args) throws UnknownHostException, SocketException {
        ApplicationContext app = SpringApplication.run(AppContext.class, args);

        String applicationName = app.getEnvironment().getProperty("spring.application.name");
        String contextPath = app.getEnvironment().getProperty("server.contextPath");
        String port = app.getEnvironment().getProperty("server.port");
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        
        LOGGER.info("\n|------------------------------------------------------------" +
                "\n| Application '" + applicationName + "' is running! Access URLs:" +
                "\n|   Local:      http://127.0.0.1:" + port + contextPath +
                "\n|   External:   http://" + hostAddress + ":" + port + contextPath +
                "\n|------------------------------------------------------------");
        
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets))
        	displayInterfaceInformation(netint);
        
        if(ip != null)
        	InitMqtt.getinstance().connect(ip.replaceFirst("/", "tcp://")+ ":1883");
        
    }
   
	public static void displayInterfaceInformation(NetworkInterface netint) throws SocketException, UnknownHostException {
		Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
		
		for (InetAddress inetAddress : Collections.list(inetAddresses)) {
			
			if (inetAddress.isSiteLocalAddress() && !inetAddress.equals(InetAddress.getLocalHost())) {
				System.out.println(inetAddress.toString());
				ip = inetAddress.toString();
			}
			
		}
	}

}
