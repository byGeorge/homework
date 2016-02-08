package Networks02;

import java.io.IOException;
import java.net.Socket;

/**
 * Client will pass an IP address to the server.
 *
 * Created by george on 2/7/16.
 */
public class Client {
    static int SOCKET = 6052;
    public static void main(String args[]) throws IOException {
        // I totally stole this from Greg's program. Makes sure 1 argument is passed
        if (args.length != 1) {
            System.err.println("Usage: java Client <IP name>");
            System.exit(0);
        }
        Socket csock = null;
        try {
            csock = new Socket("localhost", SOCKET);
        }
        catch (IOException e) {
            System.out.println("Error connecting to host");
            e.printStackTrace();
            System.exit(-1);
        }
        finally {
            if (csock != null) {
                csock.close();
            }
        }
    }

}
