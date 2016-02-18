package Security04;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Asks the user for a public key pair (e,n) and a single character to be
 * encoded. The character (m) will be entered from the keyboard and it will
 * be used to compute c = m^e mod n. This encoded value (c) will then be
 * displayed to the user as an integer.
 *
 * Created by george on 2/17/16.
 */
public class Encode {
    public Encode() {
        int e = 0, n = 0;
        char m;
        BigInteger p;
        String user;
        Scanner input = new Scanner(System.in);
        boolean valid = false;
        // user input loop (in case the user doesn't know how to follow instructions)
        while (!valid) {
            // getting user input
            System.out.println("First, I'll need your public key info. What is the smaller of your two (positive) primes?");
            e = input.nextInt();
            System.out.println("Great! Now I'll need the second, larger prime ");
            n = input.nextInt();
            // Oh look! the user can follow directions!
            if (e < n && e > 0 && n > 0) {
                valid = true;
            }
            // or not.
            else {
                System.out.println("Apparently, simple directions don't interest you. Let's try this again.");
            }
        } // end while
        // will keep computing until the user tells it to stop
        while (valid) {
            System.out.println("Input a character to encode. Typing multiple characters at once is ineffective.");
            System.out.println("Type \"exit\" if you want to exit.");
            // because Java does not have a nextChar method, which is kinda idiotic
            // the try block is to catch the exception thrown by the user not entering a character
            try {
                user = input.nextLine();
                if (user.equalsIgnoreCase("exit")) {
                    break;
                }
                m = user.charAt(0);
                p = BigInteger.valueOf(m).pow(e);
                System.out.println("Encoded character " + m + ": " + (p.remainder(BigInteger.valueOf(n))));
            }
            catch (StringIndexOutOfBoundsException oobe){
                System.out.println("Typing a character would be more effective");
            }
        }
    }

    public static void main(String[] args){
        Encode foo = new Encode();
    }
}
