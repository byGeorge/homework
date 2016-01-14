import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * This program copies a file. It takes two arguments, the file to copy, then the destination file (which
 * must be blank).
 *
 * Created by george on 1/12/16.
 */
public class Networks01 {
    public static void main (String args[]) {
        //error checking to ensure correct number of args and that the filenames are not the same
        // not that it needs to, the destination file must be blank and therefore won't be processed anyway
        if (args.length != 2) {
            System.out.println("Error: Requires two arguments. Like this: \nNetworks01 [file to copy] [destination]");
            System.exit(0);
        }
        else if (args[0].equals(args[1])) {
            System.out.println("DON'T CROSS THE STREAMS! Input and output files must be different");
            System.exit(0);
        }

        // streams! w00t!
        FileInputStream innie;
        FileOutputStream outie;

        try {
            File infile = new File(args[0]);
            // is the input file a real file?
            if (!infile.exists()) {
                System.out.println("Error: File to copy does not exist. \n Please try your call again.");
                System.exit(0);
            }
            // does the output file already exist?
            File outfile = new File(args[1]);
            if (outfile.isFile()) {
                System.out.println("Error: Destination file already exists.");
                System.exit(0);
            } // end try

            // shiny. OPEN THE STREAMS!!!
            innie = new FileInputStream(infile);
            outie = new FileOutputStream(outfile);

            //buff[] is a buffer. it is the thing that buffs. reading just keeps the place
            byte buff[] = new byte[1024];
            int reading;

            //takes the file a chunk (of 1024 bytes) at a time, puts it in the input stream, writes
            // it to the output stream until there isn't anything left to write (will return -1)
            while ((reading = innie.read(buff)) != -1) {
                outie.write(buff, 0, reading);
            }

            // closes the streams
            innie.close();
            outie.close();
        }
        // because a try block needs to be caught
        catch (Exception e) {
            System.out.println("This isn't working. This is why:");
            e.printStackTrace();
        }
        System.out.println("Files copied.");
    }
}
