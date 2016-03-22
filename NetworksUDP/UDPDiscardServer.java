package NetworksUDP;
/**
 * A discard server using UDP.
 *
 * Usage: 
 *	java UDPDiscardServer [ <port #> ]
 *
 * @author Greg Gagne 
 */
import java.util.concurrent.Callable;

/**
 * Created by george on 2/10/16.

public class Version1 {
    class Summation implements
    {
        private int upper;

        public Summation(int upper) {
            this.upper = upper;
        }

        public Integer call() {
            int sum = 0;
            for (int i = 1; i <= upper; i++)
                sum += i;

            return new Integer(sum);
        }
    }
}*/
import java.net.*;
import java.io.*;

public class UDPDiscardServer
{
    static final int DEFAULT_PORT = 2999;
    static final int PACKET_SIZE = 1024;

	public static void main(String[] args) throws IOException {
		byte[] buffer = new byte[PACKET_SIZE];

		int port = DEFAULT_PORT;

		DatagramSocket server = null;

		if (args.length > 0) 	// a port was provided
			port = Integer.parseInt(args[0]);

		try {
            // construct the UDP socket
			server = new DatagramSocket(port);

            // construct the packet used for communication
			DatagramPacket thePacket = new DatagramPacket(buffer,buffer.length);

			while (true) {
                // wait to receive a datagram
				server.receive(thePacket);

                System.out.println("received a packet");

				// get the packet contents
				byte[] data = thePacket.getData();

				/**
				 * The following just shows what we can 
				 * extract from the datagram we have received.
				 */
				int offset = thePacket.getOffset();
				int size = thePacket.getLength();
				InetAddress address = thePacket.getAddress();
				int remotePort = thePacket.getPort();

				// get the String equivalent of the packet 
				System.out.println("offset = " + offset + " size = " + size);
				String message = new String(data, offset, size);

				// log what we read from client datagrams
				System.out.println("<"+address+">:<"+remotePort+">:<"+message+">");

				/**
				 * Restore the length of the buffer
				 *
				 * IMPORTANT STEP
				 */
				thePacket.setLength(buffer.length);
			}
		}
		catch (SocketException se) {
			System.err.println(se);
		}
		catch (IOException ioe) {
			System.err.println(ioe);
		}
		finally {
			if (server != null)
				server.close();
		}
	}
}
