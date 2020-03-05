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

import br.com.thing.utils.AppContext;

@SpringBootApplication
public class AppMain {

    private static final Logger LOGGER = LogManager.getLogger(AppMain.class);
    static List<String> ip = new ArrayList<String>();

    public static void main(String[] args) throws UnknownHostException, SocketException {
      //  ApplicationContext app = SpringApplication.run(AppContext.class, args);
        
    	Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets))
            displayInterfaceInformation(netint);

    }
    
    
    
	public static void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
		Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
		inetAddresses.asIterator();
		System.out.println(inetAddresses.asIterator());
		for (InetAddress inetAddress : Collections.list(inetAddresses)) {
			
			System.out.printf("InetAddress: %s\n", inetAddress);
		}
	}

}
