package Networks03.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

/**
 * Client will pass an IP address to the server.
 * <p>
 * Created by george on 2/7/16.
 */
public class Client {
    static int SOCKET = 8080;

    public static void main(String args[]) throws IOException {
        // I totally stole this from Greg's program. Makes sure 1 argument is passed
        if (args.length != 1) {
            System.err.println("Usage: java Client <IP name>");
            System.exit(0);
        }

        Socket csock = null; // client socket
        // try to connect to the server
        try {
            csock = new Socket("localhost", SOCKET);
            // streams! w00t!
            BufferedReader in = new BufferedReader(new InputStreamReader(csock.getInputStream()));
            // creates a printwriter for the socket that autoflushes the stream
            PrintWriter print = new PrintWriter(csock.getOutputStream(), true);
            System.out.println("Requesting: " + args[0]);
            print.println(args[0]);
            String input;
            while ((input = in.readLine()) != null) {
                System.out.println(input);
            }
        } catch (ConnectException c) {
            System.out.println("Connection refused or host unavailable.");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Error connecting to server.");
            e.printStackTrace();
            System.exit(-1);
        }
        //close the streams
        finally {
            if (csock != null) {
                csock.close();
            }
        }
    }

}
