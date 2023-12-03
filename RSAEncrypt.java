package Project_2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class RSAEncrypt {
	public static ArrayList<Integer> encrypt(BigInteger e, BigInteger privateKey, BigInteger product)
			throws IOException {

		File myObj = new File("C:\\Users\\jason\\eclipse-workspace\\Project\\src\\Project_2\\test.txt");
		Scanner myReader = new Scanner(myObj);
		ArrayList<Integer> arr = new ArrayList<>();
		String testReader = "";
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			testReader += data;
		}
		myReader.close();
		testReader = testReader.toUpperCase();
		char[] ch = testReader.toCharArray();
		String finString = "";
		int i = 0;
		for (char c : ch) {
			if (i % 3 == 0 && i != 0) {
				finString += " ";
			}
			switch (c) {
			case 'A':
				finString += "00";
				break;
			case 'B':
				finString += "01";
				break;
			case 'C':
				finString += "02";
				break;
			case 'D':
				finString += "03";
				break;
			case 'E':
				finString += "04";
				break;
			case 'F':
				finString += "05";
				break;
			case 'G':
				finString += "06";
				break;
			case 'H':
				finString += "07";
				break;
			case 'I':
				finString += "08";
				break;
			case 'J':
				finString += "09";
				break;
			case 'K':
				finString += "10";
				break;
			case 'L':
				finString += "11";
				break;
			case 'M':
				finString += "12";
				break;
			case 'N':
				finString += "13";
				break;
			case 'O':
				finString += "14";
				break;
			case 'P':
				finString += "15";
				break;
			case 'Q':
				finString += "16";
				break;
			case 'R':
				finString += "17";
				break;
			case 'S':
				finString += "18";
				break;
			case 'T':
				finString += "19";
				break;
			case 'U':
				finString += "20";
				break;
			case 'V':
				finString += "21";
				break;
			case 'W':
				finString += "22";
				break;
			case 'X':
				finString += "23";
				break;
			case 'Y':
				finString += "24";
				break;
			case 'Z':
				finString += "25";
				break;
			case ' ':
				finString += "26";
				break;
			case ',':
				finString += "27";
				break;
			case '.':
				finString += "28";
				break;
			default:
				break;
			}
			i += 1;
		}
		
//		Values converted from the test.txt
		String incrementString = "";
		String finalString = "";
		int x = 0;

		while (x <= finString.length() - 1) {
			char currentChar = finString.charAt(x);

			if (currentChar != ' ')
			{
				incrementString += currentChar;
			}
			
			else
			{
			BigDecimal notEncryptedYet = new BigDecimal(incrementString);
	        BigDecimal eBigDecimal = new BigDecimal(e);
	        BigDecimal productBigDecimal = new BigDecimal(product);
	        BigDecimal result = notEncryptedYet.pow(eBigDecimal.intValue()).remainder(productBigDecimal);
	        
			// Result of all the numbers using the given p, q, and e values
			int intValue = result.intValue();
			arr.add(intValue);
			incrementString = "";
			}
			x += 1;
		}

//		Writing to a test.enc file
		String fileName = "C:\\Users\\jason\\eclipse-workspace\\Project\\src\\Project_2\\test.enc";
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			// Writing the array to the file
			for (Integer element : arr) {
				writer.write(element.toString()); // Write each element as a string
				writer.newLine(); // Add a new line after each element
			}
		}
		return arr;
	}
}