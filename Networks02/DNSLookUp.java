package Networks02;
/**
 * A simple program demonstrating DNS lookup
 * returns a string if URL is found, or "unknown host" if not found.
 *
 * Usage:
 *	java DNSLookUp <IP Name>
 *
 * @author Greg Gagne
 * slightly modified by George
 */

import java.net.*;

public class DNSLookUp
{
	String str;

	public DNSLookUp(String url) {

		InetAddress hostAddress;

		try {
			hostAddress = InetAddress.getByName(url);
			str = hostAddress.getHostAddress();
		}
		catch (UnknownHostException uhe) {
			str = ("Unknown host");
		}
	}
	public String toString() {
		return str;
	}
}
