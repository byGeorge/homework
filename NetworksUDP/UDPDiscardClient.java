/**
 * A discard client using UDP. This client sends data to a UDP server
 * The UDP server reads - and discarads - its contents.
 *
 * Usage: java UDPDiscardClient [ <host> <port> ]
 *
 * @author Greg Gagne 
 */

import java.net.*;
import java.io.*;

public class UDPDiscardClient
{
    static final int DEFAULT_PORT = 2999;
    static final String DEFAULT_HOST = "127.0.0.1";

	public static void main(String[] args) throws IOException {

		DatagramSocket theSocket = null;
		BufferedReader userInput = null;

		String hostName = DEFAULT_HOST;
		int port = DEFAULT_PORT;

		if (args.length == 2) {		// a hostname and port was provided
			hostName = args[0];
			port = Integer.parseInt(args[1]);
		}

		try {
			InetAddress server = InetAddress.getByName(hostName);

			userInput = new BufferedReader(new InputStreamReader(System.in));
			theSocket = new DatagramSocket();

			boolean done = false;
			
			while (!done) {
                System.out.print(">> ");
                // read what the client enters
				String line = userInput.readLine();

                if (line.equals("."))
					done = true;
				else {
					// get the byte equivalent of what the user entered
					byte[] data = line.getBytes();

					// construct the packet
					DatagramPacket thePacket =
						new DatagramPacket(data,data.length,server,port);

					// send the packet over the socket
					theSocket.send(thePacket);
				}
			}
		}
		catch (UnknownHostException uhe) {
			System.err.println(uhe);
		}
		catch (SocketException se) {
			System.err.println(se);
		}
		catch (IOException ioe) {
			System.err.println(ioe);
		}
		finally {
			if (theSocket != null)
				theSocket.close();

			if (userInput != null)
				userInput.close();
		}
	}
}
