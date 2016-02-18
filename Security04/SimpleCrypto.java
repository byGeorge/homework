package Security04;//  SimpleCrypto.java
//  A minimum RSA public key cryptography example in Java.
//  It does not use the Java Security Classes
//  It only works for small p and q.

public class SimpleCrypto
{

    public static void main(String args[]) {

 // choose two distinct primes with p < q

      long p = 11;
      long q = 13;

      System.out.println("The secret values are p = " + p + " q = " + q );

 // choose n as the product of p and q
 // no known algorithm can recompute p and q from n within a reasonable period
 // of time for large n.
      long n = p * q;
      System.out.println("The value of n = " + n);

// Compute phi = (p-1)*(q-1).
      long phi = (p - 1) * ( q - 1);
      System.out.println("The value of Euler's quotient is " + phi);

 // choose a random prime e between 1 and phi, exclusive, so that e
 // has no common factors with phi.
      long e = findfirstnocommon(phi);
      System.out.println("The public exponent = " + e);

 // Compute d as the multiplicative inverse of e modulo phi(n).

            long d = findinverse(e,phi);
            System.out.println("The private key is " + d);
            System.out.println( " (d) (e) mod phi = " + (d * e) % phi);

// let m be the "MESSAGE" that needs to be encoded
           char m = 'Q';

// encode m as c = m^e mod n using expomod
          long c = expomod(m,e,n);

 // c is sent to the receiver over an open channel
         System.out.println("Transmitting encoded " + m + " as " + c);

 // decode c to m = c^d mod n
         m = (char)expomod(c,d,n);

         System.out.println( "Decoding " + c + " to " + m);
 }

// Helper Methods Follow:

// Let a and n be two long integers with n > 0. We wish to compute
// x = a^n mod z.
static long expomod(long a, long n, long z) {
    long r = a % z;

    for(long i = 1; i < n; i++) {
      r = (a * r) % z;
    }
 return r;
 }


static long findfirstnocommon(long n) {
  long j;
  for(j = 2; j < n; j++)
    if(euclid(n,j) == 1) return j;
  return 0;
}


static long findinverse(long n, long phi) {
  long i = 2;
  while( ((i * n) % phi) != 1) i++;
  return i;
}

// m and n are two positive integers (not both 0)
// returns the largest integer that divides both m and n exactly
static long euclid(long m, long n) {
        while(m > 0) {
          long t = m;
          m = n % m;
          n = t;
        }
 return n;
}

}
