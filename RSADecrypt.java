package Project_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class RSADecrypt {
	public static void decryptedNums(BigInteger privateKey, BigInteger product) {
		ArrayList<Integer> arrayList = new ArrayList<>();
		String encryptFile = "C:\\Users\\jason\\eclipse-workspace\\Project\\src\\Project_2\\test.enc";
		try (BufferedReader reader = new BufferedReader(new FileReader(encryptFile))) {
			String line;
			while ((line = reader.readLine()) != null) {
				int num = Integer.parseInt(line.trim());
				arrayList.add(num);
			}
		} catch (IOException e) {
			System.out.println(e);
		}

		ArrayList<String> decryptedNums = new ArrayList<>();
		
		for (int i = 0; i < arrayList.size(); i++) {
			BigInteger encryptedNum = BigInteger.valueOf(arrayList.get(i));
            BigInteger decryptedNum = encryptedNum.modPow(privateKey, product);
            
            int intValue = decryptedNum.intValue();
            
            
         // Convert decrypted number to a string
            String str = String.valueOf(intValue);
            
            // Determine the padding if needed
            if (str.length() == 4) {
                str = "00" + str;
            } else if (str.length() == 5) {
                str = "0" + str;
            }
            
            decryptedNums.add(str);
		}
		
		ArrayList<String> arrayList2 = new ArrayList<>();
		
		String numOfZeros = "";
		String str = "";
		for (String i : decryptedNums) {
			numOfZeros = "";
			if(i.length() == 4) {
				numOfZeros = "00";
				numOfZeros += i;
				arrayList2.add(numOfZeros);
				numOfZeros = "";
			}
			else if(i.length() == 5)
			{
				numOfZeros = "0";
				numOfZeros += i;
				arrayList2.add(numOfZeros);
			}
			else
			{
				numOfZeros += i;
				arrayList2.add(numOfZeros);
			}
		}
		System.out.println(arrayList2);
		
		String finString = "";
		for(String i : arrayList2)
		{
			finString += i;
		}

		String secondString = "";
		for (int i2 = 0; i2 <= finString.length() - 1; i2 += 2) {
			String substring = finString.substring(i2, i2 + 2);
			switch (substring) {
			case "00":
				secondString += "A";
				break;
			case "01":
				secondString += "B";
				break;
			case "02":
				secondString += "C";
				break;
			case "03":
				secondString += "D";
				break;
			case "04":
				secondString += "E";
				break;
			case "05":
				secondString += "F";
				break;
			case "06":
				secondString += "G";
				break;
			case "07":
				secondString += "H";
				break;
			case "08":
				secondString += "I";
				break;
			case "09":
				secondString += "J";
				break;
			case "10":
				secondString += "K";
				break;
			case "11":
				secondString += "L";
				break;
			case "12":
				secondString += "M";
				break;
			case "13":
				secondString += "N";
				break;
			case "14":
				secondString += "O";
				break;
			case "15":
				secondString += "P";
				break;
			case "16":
				secondString += "Q";
				break;
			case "17":
				secondString += "R";
				break;
			case "18":
				secondString += "S";
				break;
			case "19":
				secondString += "T";
				break;
			case "20":
				secondString += "U";
				break;
			case "21":
				secondString += "V";
				break;
			case "22":
				secondString += "W";
				break;
			case "23":
				secondString += "X";
				break;
			case "24":
				secondString += "Y";
				break;
			case "25":
				secondString += "Z";
				break;
			case "26":
				secondString += " ";
				break;
			case "27":
				secondString += ",";
				break;
			case "28":
				secondString += ".";
				break;
			default:
				break;
			}
		}
		
		String decryptFile = "C:\\Users\\jason\\eclipse-workspace\\Project\\src\\Project_2\\test.dec";
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(decryptFile))) {
		    writer.write(secondString); // Write the entire secondString at once
		} catch (IOException e) {
		    System.out.println(e);
		}
		System.out.println(secondString);
	}
}