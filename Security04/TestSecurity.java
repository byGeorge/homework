package Security04;/*  Simple Java program that shows how to import and use some Java Cryptography
 */
 
import java.security.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class TestSecurity {

 public static void main(String[] args) {

  // initial setup
  SecurityManager sm = System.getSecurityManager();
  if (sm == null)
   System.out.println("no security manager");
  else
   System.out.println("security manager available");

  // get a list of security providers and algorithms
//   this section is just for general interest - not part of the encryption/decryption
  try
  {
   Provider p[] = Security.getProviders();
   System.out.println("number of providers: " + p.length);
   int j=0;
   for (int i = 0; i < p.length; i++)
   {
    System.out.println(p[i]);
    for (Enumeration e = p[i].keys(); e.hasMoreElements(); )
     System.out.println("\t" + e.nextElement());
   }
  }
  catch (Exception e)
  {
   System.out.println("Exception in listing providers/algorithms");
   e.printStackTrace();
  }

  // encrypt and decrypt a text string using DES
  try
  {
   KeyGenerator kg = KeyGenerator.getInstance("DES");
   Cipher c = Cipher.getInstance("DES/CBC/PKCS5Padding");
   Key key = kg.generateKey();

   // encryption
   c.init(Cipher.ENCRYPT_MODE, key);
   byte input[] = "Java Cryptography Extension forever!".getBytes();
   System.out.println("The string to encrypt is: ");
   System.out.println(new String(input));
   byte encrypted[] = c.doFinal(input);
   byte iv[] = c.getIV();
   System.out.println("The encrypted string is: ");
   System.out.println(new String(encrypted));

   // decryption
   IvParameterSpec dps = new IvParameterSpec(iv);
   c.init(Cipher.DECRYPT_MODE, key, dps);
   byte output[] = c.doFinal(encrypted);
   System.out.println("The decrypted string is: ");
   System.out.println(new String(output));
  }
  catch (Exception e)
  {
   System.out.println("Exception in encrypting/decrypting");
   e.printStackTrace();
  }
 }
}
