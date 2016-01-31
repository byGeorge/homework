import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * This program copies a file. It takes two arguments, the file to copy, then the destination file (which
 * must be blank).
 *
 * and now... part deaux... this program will copy a file from a url
 *
 * Created by george on 1/12/16.
 */
public class Networks01v2 {
    public static void main (String args[]) throws IOException {
        final int BYTE = 1024;
        //error checking to ensure correct number of args and that the filenames are not the same
        // not that it needs to, the destination file must be blank and therefore won't be processed anyway
        if (args.length != 2) {
            System.out.println("Error: Requires two arguments. Like this: \nNetworks01v2 [url] [destination filename]");
            System.exit(1);
        }
        else if (args[0].equals(args[1])) {
            System.out.println("DON'T CROSS THE STREAMS! Input and output files must be different");
            System.exit(1);
        }

        // streams! w00t!
        InputStream innie = null;
        FileOutputStream outie = null;
        // and a URL
        URL source;

        try {
            // create and open a connection to the URL
            URLConnection conn = new URL(args[0]).openConnection();
            conn.connect();
            // does the output file already exist?
            File outfile = new File(args[1]);
            if (outfile.isFile()) {
                System.out.println("Error: Destination file already exists.");
                System.exit(1);
            } // end try

            // shiny. OPEN THE STREAMS!!!
            innie = conn.getInputStream();
            outie = new FileOutputStream(outfile);

            //buff[] is a buffer. it is the thing that buffs. reading just keeps the place
            byte buff[] = new byte[BYTE];
            int reading;

            //takes the file a chunk (of 1024 bytes) at a time, puts it in the input stream, writes
            // it to the output stream until there isn't anything left to write (will return -1)
            while ((reading = innie.read(buff)) != -1) {
                outie.write(buff, 0, reading);
            }
        }
        // catch block for invalid URL
        catch (IOException badURL) {
            System.out.println("Invalid URL.");
        }
        // because a try block sometimes just needs to be caught
        catch (Exception e) {
            System.out.println("This isn't working. This is why:");
            e.printStackTrace();
        }
        // you suggested this in class, so I implemented it. It's better programming.
        // closes the streams
        finally {
            if (innie != null) {
                innie.close();
            }
            if (outie != null) {
                outie.close();
            }
        }
    }
}
