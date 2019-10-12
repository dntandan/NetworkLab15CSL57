//11. Write a program for simple RSA algorithm to encrypt and decrypt the data

import java.security.SecureRandom;
import java.util.Scanner;
import java.math.BigInteger;


class RSA_Algorithm {

    static BigInteger p, q, n, phi_of_n, e, d;
    static SecureRandom Sec_Rand;
    static int bitLength = 64;

    static String encrypt(String enteredMsg) {
        return new BigInteger(enteredMsg.getBytes()).modPow(e, n).toString();
    }

    static String decrypt(String encryptedMessage) {
        BigInteger cypher = new BigInteger(encryptedMessage).modPow(d, n);
        return new String(cypher.toByteArray());
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Sec_Rand = new SecureRandom();

        p = BigInteger.probablePrime(bitLength, Sec_Rand);
        q = BigInteger.probablePrime(bitLength, Sec_Rand);
        n = p.multiply(q);
        phi_of_n = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        e = BigInteger.probablePrime(bitLength / 2, Sec_Rand);
        while (e.gcd(phi_of_n).compareTo(BigInteger.ONE) != 0 && e.compareTo(phi_of_n) < 0) {
            e = e.add(BigInteger.ONE);
        }

        d = e.modInverse(phi_of_n);
        System.out.println("Value of P is taken as: " + p + "\nValue of Q is taken as: " + q +"\nValue of N is taken as: " + n +"\nValue of Phi(n) is taken as: " + phi_of_n);   
        System.out.println("\nEnter Your Message");
        String enteredMsg = scanner.nextLine();
        String encryptedMessage = encrypt(enteredMsg);
        System.out.println("\nYour Encrypted Message W.R.T RSA Algorithm: " + encryptedMessage);

        System.out.println("\n...Decrypting Your Message Using Above Findings i.e n, e & d Of RSA Algorithm...");
        String decryptedMessage = decrypt(encryptedMessage);
        System.out.println("\nYour Decrypted Message Is: " + decryptedMessage);

    }
}


//Output
//Value of P is taken as: 10948250936544026681                                                                                                     
//Value of Q is taken as: 16926290012259889831                                                                                                     
//Value of N is taken as: 185313270478940143694515828990184580911                                                                                  
//Value of Phi(n) is taken as: 185313270478940143666641288041380664400                                                                             
                                                                                                                                                 
//Enter Your Message                                                                                                                               
//RSA Algo Test                                                                                                                                    
                                                                                                                                                 
//Your Encrypted Message W.R.T RSA Algorithm: 39936265603214583335045458908004946404                                                               
                                                                                                                                                 
//...Decrypting Your Message Using Above Findings i.e n, e & d Of RSA Algorithm...                                                                 
                                                                                                                                                 
//Your Decrypted Message Is: RSA Algo Test 
