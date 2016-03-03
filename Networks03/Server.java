package Networks03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

/**
 * Listens at port 6052 for client connection. Services each client in a separate thread.
 * Client will pass an IP address through the socket and server will close the connection.
 * If the address is valid, the server will perform a DNS lookup and reply with the IP
 * address and close the connection. If the address is not valid, the server will simply
 * close the connection.
 *
 * @author George
 *         2/7/16
 */

public class Server implements Runnable {
    Socket csock; // client socket
    static String port;
    static String host;
    static String resource;

    public Server(Socket csock) {
        this.csock = csock;
    }

    private void request(String req) {
        try {
            // parse strings by splitting along the "/" character and allocating accordingly
            String bits[] = req.split("/");
            port = bits[2];
            host = bits[3];
            resource = bits[4];
            for (int i = 5; i < bits.length; i++) {
                resource = resource + "/" + bits[i];
            }
        }
        catch (ArrayIndexOutOfBoundsException ae) {

        }
        catch (Exception e) {
            System.out.println("There was an error with your request");
            e.printStackTrace();
        }
    }

    public void run() {
        // running on a new thread
        if (csock.isConnected()) {
            try {
                // creates a print stream for the socket that autoflushes the stream
                PrintStream print = new PrintStream(csock.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(csock.getInputStream()));

                // prints the request to the print writer
                String input;
                if ((input = in.readLine()) != null) {
                    request(input);
                    print.println("GET /" + resource + " HTTP/1.1\r\nHost: " + host + "\r\n" + "Connection: close\r\n\r\n");
                }
                csock.close();
            } catch (Exception e) {
                System.out.println("Something went wrong");
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }// end run

    public static void main(String[] args) throws IOException {
        final int SOCKET = 8080;
        ServerSocket ssock = null; // server socket. Just like it sounds
        //try to open the server socket
        try {
            ssock = new ServerSocket(SOCKET);
        } catch (IOException e) {
            System.out.println("Error: could not create socket on " + SOCKET + ".");
            e.printStackTrace();
            System.exit(-1);
        }
        while (true) {
            try {
                Socket clisock = ssock.accept();
                new Thread(new Server(clisock)).start();
            } catch (Exception e) {
                System.out.println("Error creating client socket");
                e.printStackTrace();
                System.exit(-1);
            }
        } // end while
    } // end main
} // end Server
