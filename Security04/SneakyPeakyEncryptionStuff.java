package Security04;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;

/**
 * Takes a file and encrypts it using Java's encryption libraries.
 * Created by george on 2/18/16.
 */
public class SneakyPeakyEncryptionStuff {
    public SneakyPeakyEncryptionStuff() {
        try {
            key = generateKey(); // generates key
            // generates and initializes the cipher with the key
            c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, key);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static final String ALGORITHM = "DES"; // kind of encryption used
    // This totally looks like a secret code. it is.
    private static final byte[] keyValue = new byte[]
            { 's', 'N', 'e', 'a', 'K', 'Y', 'p', 'e', 'A', 'k', 'Y', 's', 'T', 'u', 'F', 'F'};
    private Key key; // secret key
    Cipher c; // this will be the encryption method used

    /** encrypts a file*/
    public void encrypt(String in) {
        // streams! w00t!
        FileInputStream innie;
        FileOutputStream outie;

        try {
            File infile = new File(in);
            // is the input file a real file?
            if (!infile.exists() && in.contains(".txt")) {
                System.out.println("Error: File to copy does not exist or is not a .txt file. \n Please try your call again.");
                System.exit(0);
            }
            // does the output file already exist?
            File encfile = new File("encrypted_" + in);
            if (encfile.isFile()) {
                System.out.println("Error: Destination file already exists.");
                System.exit(0);
            } // end try

            // shiny. OPEN THE STREAMS!!!
            innie = new FileInputStream(infile);
            outie = new FileOutputStream(encfile);

            // Takes the input stream, encodes it, and writes to the output stream
            new BASE64Encoder().encodeBuffer(innie, outie);

            // closes the streams
            innie.close();
            outie.close();
        }
        // because a try block needs to be caught
        catch (Exception e) {
            System.out.println("This isn't working. This is why:");
            e.printStackTrace();
        }
        System.out.println("Encrypted File Created as: encrypted_" + in);
    }

    /** decrypts a file */
    public void decrypt(String in) {
        // streams! w00t!
        FileInputStream innie;
        FileOutputStream outie;

        try {
            File infile = new File(in);
            // is the input file a real file?
            if (!infile.exists() && in.contains(".txt")) {
                System.out.println("Error: File to copy does not exist or is not a .txt file. \n Please try your call again.");
                System.exit(0);
            }
            // does the output file already exist?
            File decfile = new File("decrypted_" + in);
            if (decfile.isFile()) {
                System.out.println("Error: Destination file already exists.");
                System.exit(0);
            } // end try

            // shiny. OPEN THE STREAMS!!!
            innie = new FileInputStream(infile);
            outie = new FileOutputStream(decfile);

            // Takes the input stream, decodes it, and writes to the output stream
            new BASE64Decoder().decodeBuffer(innie, outie);

            // closes the streams
            innie.close();
            outie.close();
        }
        // because a try block needs to be caught
        catch (Exception e) {
            System.out.println("This isn't working. This is why:");
            e.printStackTrace();
        }
        System.out.println("Encrypted File Created as: decrypted_" + in);
    }

    private Key generateKey() throws Exception {
        key = new SecretKeySpec(keyValue, ALGORITHM); //
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        key = keyFactory.generateSecret(new DESKeySpec(keyValue));
        return key;
    }

    public static void main(String[]args){
        SneakyPeakyEncryptionStuff shiny = new SneakyPeakyEncryptionStuff();
        shiny.encrypt("secret.txt");
        shiny.decrypt("encrypted_secret.txt");
    }
}
