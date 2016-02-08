package Networks02;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

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

        // while connection is open
        while (serving) {
            System.out.println("Waiting for connection from client");
            // try to connect to the client
            try {
                csock = ssock.accept();
                // if the client connects
                if (csock.isConnected()) {
                    System.out.println("Connected to " + csock.getInetAddress() + ".");
                    System.out.println("Escape character is '^]'.");
                    serving = false;

                    // streams! w00t!
                    OutputStream outie = csock.getOutputStream();
                    // creates a printwriter for the socket that autoflushes the stream
                    PrintWriter print = new PrintWriter(csock.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(csock.getInputStream()));

                    // prints the dns lookup to the print writer
                    String input;
                    if ((input = in.readLine()) != null) {
                        print.println(input);
                        print.println(new DNSLookUp(input));
                    }

                    //takes the file a chunk (BYTE bytes) at a time, puts it in the input stream, writes
                    // it to the output stream until there isn't anything left to write (will return -1)
                    csock.close();
                }
            }
            catch (UnknownHostException u) {
                System.out.println("Unknown host.");
                System.exit(0);
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
} // end Server
