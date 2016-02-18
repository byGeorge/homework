package Security04;

import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Ask the user for a private key pair (d,n) and a single integer (c) to be
 * decoded. The program will then compute m = c^d mod n and display the result
 * as a character.
 * Created by george on 2/17/16.
 */
public class Decode {
    public Decode() {
        int d = 0, n = 0, c = 0;
        char m;
        BigInteger p;
        String user;
        Scanner input = new Scanner(System.in);
        boolean valid = false;
        // user input loop (in case the user doesn't know how to follow instructions)
        while (!valid) {
            // getting user input
            System.out.println("First, I'll need your public key info. What is the smaller of your two (positive) primes?");
            d = input.nextInt();
            System.out.println("Great! Now I'll need the second, larger prime ");
            n = input.nextInt();
            // Oh look! the user can follow directions!
            if (d < n && d > 0 && n > 0) {
                valid = true;
            }
            // or not.
            else {
                System.out.println("Apparently, simple directions don't interest you. Let's try this again.");
            }
        } // end while
        // will keep computing until the user tells it to stop
        while (valid) {
            System.out.println("Input an integer to decode, a letter to exit program.");
            try {
                c = input.nextInt();
                System.out. println(c+ " to the power of  "+d+" r "+n);
                p = (BigInteger.valueOf(c).pow(d)).remainder(BigInteger.valueOf(n));
                System.out.println("Decoded number: " + c + " is: " + ((char) p.intValue()));
            }
            catch (InputMismatchException ex){
                System.out.println("Thank you! come again!");
            }
        }
    }
    public static void main(String[] args){
        Decode foo = new Decode();
    }
}
