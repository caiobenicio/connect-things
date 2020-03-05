package br.com.thing.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

public class IpAddress {
	
	private static IpAddress instance = new IpAddress();
	public static IpAddress getinstance() {
		return instance == null? new IpAddress() : instance;
	}
	
	static void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
		System.out.printf("Display name: %s\n", netint.getDisplayName());
		System.out.printf("Name: %s\n", netint.getName());
		Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
		for (InetAddress inetAddress : Collections.list(inetAddresses)) {
			System.out.printf("InetAddress: %s\n", inetAddress);
		}
		System.out.printf("\n");
	}
	
}
//
//for (NetworkInterface netint : Collections.list(nets))
//    displayInterfaceInformation(netint);
//
