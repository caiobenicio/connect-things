package br.com.thing.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;

public class IpAddress {

	static String ip = "/10.1.9.168";

	private static IpAddress instance = new IpAddress();
	public static IpAddress getinstance() {
		return instance == null ? new IpAddress() : instance;
	}

	public IpAddress() {
		//findIpExternal();
	}

	private void findIpExternal() {

		Enumeration<NetworkInterface> nets;
		try {
			nets = NetworkInterface.getNetworkInterfaces();
			for (NetworkInterface netint : Collections.list(nets))
				displayInterfaceInformation(netint);
		} catch (SocketException | UnknownHostException e) {
			e.printStackTrace();
		}

	}

	public static void displayInterfaceInformation(NetworkInterface netint)
			throws SocketException, UnknownHostException {
		Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();

		for (InetAddress inetAddress : Collections.list(inetAddresses)) {

			if (inetAddress.isSiteLocalAddress() && !inetAddress.equals(InetAddress.getLocalHost())) {
				System.out.println(inetAddress.toString());
				ip = inetAddress.toString();
			}

		}
	}

	public String getIpExternal() {
		return ip.replaceFirst("/", "");
	}

	public String getIpWithTcp() {
		return ip.replaceFirst("/", "tcp://") + ":1883";
	}

}
