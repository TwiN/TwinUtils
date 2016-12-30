package org.twinnation.twinutilities;

/**
 * Network-related utility class
 */
public final class NetUtils {

	/** Prevents instantiation of this utility class */
	private NetUtils() {}
	
	
	/**
	 * Checks if String <i>ip</i> is a valid IPv4 or a valid IPv6
	 * @param ip String to check
	 * @return Whether <i>ip</i> is a valid IP
	 */
	public static boolean isValidIp(String ip) {
		return isValidIPv4(ip) || isValidIPv6(ip);
	}
	
	
	/**
	 * Checks if String <i>ip</i> is a valid IPv4
	 * @param ip String to check
	 * @return Whether <i>ip</i> is a valid IPv4
	 */
	public static boolean isValidIPv4(String ip) {
		return ip.matches("\\A(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)"
				+ "(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}\\z");
	}
	
	
	/**
	 * Checks if String <i>ip</i> is a valid IPv6
	 * @param ip IP to check
	 * @return Whether <i>ip</i> is a valid IPv6
	 */
	public static boolean isValidIPv6(String ip) {
		return ip.toUpperCase()
				.matches("\\A(?:[0-9A-F]{1,4}:){7}[0-9A-F]{1,4}\\z");
	}
}
