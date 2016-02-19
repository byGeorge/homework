package Security04;

import java.util.Scanner;

/**
 * Asks the user for the RSA secret information, that is, two primes (p and q) with p < q.
 * It will compute n, phi, e, and d and will display these values
 *
 * Created by george on 2/17/16.
 */
public class KeyGen {

    /**
     * This method collects p and q, then runs Calculate(p, q) and displays
     * n, phi, e, and d
     */
    public KeyGen(){
        int p = 0, q = 0;
        Scanner input = new Scanner(System.in);
        boolean valid = false;
        // user input loop (in case the user doesn't know how to follow instructions)
        while (!valid) {
            // getting user input
            System.out.println("First, I'll need your RSA info. What is the smaller of your two (positive) primes?");
            p = input.nextInt();
            System.out.println("Great! Now I'll need the second, larger prime ");
            q = input.nextInt();
            // Oh look! the user can follow directions!
            if (p < q && p > 0 && q > 0) {
                valid = true;
            }
            // or not.
            else {
                System.out.println("Apparently, simple directions don't interest you. Let's try this again.");
            }
        } // end while
        long[] calculated = Calculate(p, q);
        System.out.println("n: " + calculated[0] + ", phi: " + calculated[1] +
                ", e: " + calculated [2] + ", d: " + calculated[3]);
    }


    /**
     * Finds first common denominator (I think)
     * Next three methods stolen from SimpleCrypto
     */
    static long findfirstnocommon(long n) {
        long j;
        for(j = 2; j < n; j++)
            if(euclid(n,j) == 1) return j;
        return 0;
    }

    /**
     * m and n are two positive integers (not both 0) and
     * returns the largest integer that divides both m and n exactly
     */
    static long euclid(long m, long n) {
        while(m > 0) {
            long t = m;
            m = n % m;
            n = t;
        }
        return n;
    }

    /**
     * Let's be honest here... I totally stole these mathy bits from
     * SimpleCrypto, and am entirely unsure what they do. I mean, I could
     * write out the formulae on a piece of paper but don't really understand
     * the concepts behind them.
     */
    static long findinverse(long n, long phi) {
        long i = 2;
        while( ((i * n) % phi) != 1) i++;
        return i;
    }

    /**
     * Calculates n, phi, e, and d from p and q.
     * These are super sneaky-peaky RSA maths.
     * @param p the larger of two primes
     * @param q the smaller of two primes
     * @return n is toReturn[0], phi toReturn[1], e is toReturn[2], d is toReturn[3]
     */
    protected long[] Calculate(int p, int q) {
        long toReturn[]= new long[4];
        toReturn[0] = p * q; // n
        toReturn[1] = (p-1) * (q-1); // phi
        toReturn[2] = findfirstnocommon(toReturn[1]); // e (calculated from phi)
        toReturn[3] = findinverse(toReturn[2],toReturn[1]); // d (calculated from e and phi)
        return toReturn;
    }

    public static void main(String[] args){
        KeyGen foo = new KeyGen();
    }
}
