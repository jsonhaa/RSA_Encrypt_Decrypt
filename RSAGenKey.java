package Project_2;

import java.util.Scanner;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RSAGenKey {
	public static void main(String[] args) throws IOException {
		RSAEncrypt rsaEncrypt = new RSAEncrypt();
		RSADecrypt rsaDecrypt = new RSADecrypt();
		
//		Part 1
//		BigInteger[] bigNums = keyGeneration(7, 17, 5);
		BigInteger[] bigNums2 = keyGeneration(12);
		
//		Part 2
//		RSAEncrypt.encrypt(bigNums[0], bigNums[1], bigNums[2]);
		RSAEncrypt.encrypt(bigNums2[0], bigNums2[1], bigNums2[2]);
		
//		Part 3
//		RSADecrypt.decryptedNums(bigNums[1], bigNums[2]);
		RSADecrypt.decryptedNums(bigNums2[1], bigNums2[2]);
	}
	
    private static BigInteger[] keyGeneration(int bitLength) throws IOException {
        BigInteger p = BigInteger.probablePrime(bitLength, new SecureRandom());
        BigInteger q = BigInteger.probablePrime(bitLength, new SecureRandom());
        BigInteger product = p.multiply(q);
        
        BigInteger e = BigInteger.probablePrime(bitLength, new SecureRandom());
        SecureRandom random = new SecureRandom();
        
        do {
            // Generate a random BigInteger 'e' with the required bit length or value
            e = new BigInteger(bitLength, random);

            // Ensure 'e' is a prime number and relatively prime to 'product'
        } while (!(isPrime(e) && product.gcd(e).equals(BigInteger.ONE)));
        
        BigInteger[] nums = keyGeneration(p.intValue(), q.intValue(), e.intValue());
        return nums;
    }
	
	public static void saveToFile(BigInteger p, BigInteger q, BigInteger e, BigInteger product, BigInteger privateKey2) throws IOException
	{
		FileWriter publicKey = new FileWriter("C:\\Users\\jason\\eclipse-workspace\\Project\\src\\Project_2\\pub_key.txt");
		FileWriter privateKey = new FileWriter("C:\\Users\\jason\\eclipse-workspace\\Project\\src\\Project_2\\pri_key.txt");
		publicKey.write("e = " + e + "\n" + "n = " + product);
		privateKey.write("d = " + privateKey2 + "\n" + "n = " + product);
		
		publicKey.close();
		privateKey.close();

		Path fileNamePublic = Path.of("C:\\Users\\jason\\eclipse-workspace\\Project\\src\\Project_2\\pub_key.txt");
		String content = Files.readString(fileNamePublic);
		
		Path fileNamePrivate = Path.of("C:\\Users\\jason\\eclipse-workspace\\Project\\src\\Project_2\\pri_key.txt");
		String privateContent = Files.readString(fileNamePrivate);
		
		publicKey.close();
		privateKey.close();
	}
	
	public static BigInteger[] keyGeneration(int p, int q, int e) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		
		BigInteger p1 = BigInteger.valueOf(p);
		BigInteger q1 = BigInteger.valueOf(q);
		BigInteger e1 = BigInteger.valueOf(e);
		
		
		BigInteger product = p1.multiply(q1);
        BigInteger n = p1.subtract(BigInteger.ONE).multiply(q1.subtract(BigInteger.ONE));
        if(!(isPrime(p1) && isPrime(q1)))
		{
			System.out.println("Numerical Value P and/or Q is NOT Prime!");
			return null;
		}
        
        if (!(isPrime(e1) && n.mod(e1).compareTo(BigInteger.ZERO) != 0)) {
            System.out.println("Invalid e");
            return null;
        }
        
		BigInteger[] nums = getPrimeFactors(n);
		
		BigInteger privateKey = findModularInverse(e, n);
		
//		Printing Private Key
//		System.out.println("Private Key: " + privateKey);
		
		saveToFile(p1, q1, e1, product, privateKey);
		BigInteger[] keys = {e1, privateKey, product};
		System.out.println("e1 " + e1);
		System.out.println("private key " + privateKey);
		System.out.println("product " + product);
		return keys;
	}
	
	public static BigInteger findModularInverse(int e, BigInteger n) {
		BigInteger e1 = BigInteger.valueOf(e);
        BigInteger[] gcdExtended = extendedEuclidean(e1, n);

        if (!gcdExtended[0].equals(BigInteger.ONE)) {
            // The modular inverse does not exist if GCD(e, n) != 1
            return BigInteger.valueOf(-1);
        } else {
            BigInteger inverse = gcdExtended[1].mod(n);
            if (inverse.compareTo(BigInteger.ZERO) < 0) {
                // Ensure the inverse is positive
                inverse = inverse.add(n);
            }
            return inverse;
        }
    }

    public static BigInteger[] extendedEuclidean(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) {
            return new BigInteger[] { a, BigInteger.ONE, BigInteger.ZERO };
        } else {
            BigInteger[] values = extendedEuclidean(b, a.mod(b));
            BigInteger gcd = values[0];
            BigInteger x = values[2];
            BigInteger y = values[1].subtract(a.divide(b).multiply(values[2]));
            return new BigInteger[] { gcd, x, y };
        }
    }

	public static BigInteger[] getPrimeFactors(BigInteger n) {
        ArrayList<BigInteger> factorsList = new ArrayList<>();

        BigInteger divisor = BigInteger.valueOf(2);

        while (n.compareTo(BigInteger.ONE) > 0) {
            if (n.remainder(divisor).equals(BigInteger.ZERO)) {
                factorsList.add(divisor);
                n = n.divide(divisor);
            } else {
                // If it's not divisible, move to the next prime number
                divisor = divisor.add(BigInteger.ONE);
            }
        }

        // Convert the ArrayList to an array
        BigInteger[] factorsArray = new BigInteger[factorsList.size()];
        factorsList.toArray(factorsArray);

        return factorsArray;
    }
	
	public static boolean isPrime(BigInteger num) {
        if (num.compareTo(BigInteger.ONE) <= 0) {
            return false; // Numbers less than or equal to 1 are not prime
        }

        if (num.equals(BigInteger.TWO)) {
            return true; // Number 2 is prime
        }

        // Check divisibility from 2 to square root of num
        for (BigInteger i = BigInteger.valueOf(2); i.multiply(i).compareTo(num) <= 0; i = i.add(BigInteger.ONE)) {
            if (num.mod(i).equals(BigInteger.ZERO)) {
                return false; // If divisible by i, not prime
            }
        }
        return true; // If not divisible by any smaller number, it's prime
    }
	
}