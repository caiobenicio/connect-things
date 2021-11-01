package br.com.thing.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;

public class IpAddressUtils {

	static String ip = "/127.0.0.1";

	private static IpAddressUtils instance = new IpAddressUtils();
	public static IpAddressUtils getinstance() {
		return instance == null ? new IpAddressUtils() : instance;
	}

	public IpAddressUtils() {
		findIpExternal();
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
			//	System.out.println(inetAddress.toString());
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
