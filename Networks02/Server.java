package Networks02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Listens at port 6052 for client connection. Services each client in a separate thread.
 * Client will pass an IP address through the socket and server will close the connection.
 * If the address is valid, the server will perform a DNS lookup and reply with the IP
 * address and close the connection. If the address is not valid, the server will simply
 * close the connection.
 * @author George
 * 2/7/16
 */

public class Server {
    static int SOCKET = 6052;
    public static void main(String[] args) throws IOException {
        ServerSocket ssock = null; // name of the server socket
        Socket csock = null;
        boolean serving = true; // if the server is on, will continue to listen
        // try to open socket
        try {
            ssock = new ServerSocket(SOCKET);
        }
        catch (IOException e) {
            System.out.println("Error: could not create socket on " + SOCKET + ".");
            e.printStackTrace();
            System.exit(-1);
        }

        while (serving) {
            System.out.println("Waiting for connection from client");
            try {
                csock = ssock.accept();
                if (csock.isConnected()) {
                    System.out.println("Connected on port " + SOCKET);
                    serving = false;
                }
            }
            // if the channel could not be opened
            catch (IOException e) {
                System.out.println("Could not open connection to client");
                e.printStackTrace();
            }
            // closing down the streams
            finally {
                if (csock != null || !ssock.isClosed()) {
                    ssock.close();
                }
                if (csock != null || !csock.isClosed()) {
                    csock.close();
                }
            }
        }
    } // end main
} // end class
