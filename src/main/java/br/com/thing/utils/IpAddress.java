package br.com.thing.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;

public class IpAddress {
	
	static String ip = null;
	
	private static IpAddress instance = new IpAddress();
	public static IpAddress getinstance() {
		return instance == null? new IpAddress() : instance;
	}
	
	public String findIp() throws SocketException, UnknownHostException {
		
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets))
        	displayInterfaceInformation(netint);
        
        if(ip.isEmpty())
        	return "tcp://127.0.1:1883";
        
		return ip.replaceFirst("/", "tcp://")+ ":1883";
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
	
	public String getIpExterno() {
		return ip.replace("tcp://", "").replace(":1883", "");
	}
	
}
