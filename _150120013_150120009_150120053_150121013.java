
	// 1500120013 Irem Aydin - 150120009 Hatice Yavuzsan - 150120053 Necati Kocak -150121013 Irem Kiranmezar
	
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

public class _150120013_150120009_150120053_150121013{

    // Hexadecimal to Binary method
    static String hexToBinary(String hex) {

        // Empty String for Binary Sequence
        String binary = "";

        // hexadecimal string to upper case
        hex = hex.toUpperCase();

        // initializing the HashMap class
        HashMap<Character, String> hashMap = new HashMap<Character, String>();

        // storing the key value pairs
        hashMap.put('0', "0000");
        hashMap.put('1', "0001");
        hashMap.put('2', "0010");
        hashMap.put('3', "0011");
        hashMap.put('4', "0100");
        hashMap.put('5', "0101");
        hashMap.put('6', "0110");
        hashMap.put('7', "0111");
        hashMap.put('8', "1000");
        hashMap.put('9', "1001");
        hashMap.put('A', "1010");
        hashMap.put('B', "1011");
        hashMap.put('C', "1100");
        hashMap.put('D', "1101");
        hashMap.put('E', "1110");
        hashMap.put('F', "1111");

        int i;
        char ch;

        for (i = 0; i < hex.length(); i++) {

            ch = hex.charAt(i);

            // checking if the character is
            // present in the keys
            if (hashMap.containsKey(ch))

                // adding to the Binary Sequence the corresponding value of the key
                binary += hashMap.get(ch);

                // returning Invalid Hexadecimal
                // String if the character is
                // not present in the keys
            else {
                binary = "Invalid Hexadecimal String";
                return binary;
            }
        }

        // returning the converted Binary
        return binary;
    }

    // method for little endian 
    public static String L_B_Endian(String str) {

        //split the given string according to empty space and create new String array from it
        String[] newStr = str.split(" ");

        //create new string array for little endian form
        String[] last = new String[newStr.length];

        //declare first array's last element to new array's first element
        for (int i = 0; i < newStr.length; i++) {
            last[i] = newStr[newStr.length - i - 1];
        }

        String finalStr = "";

        //convert string array to string
        for (int i = 0; i < newStr.length; i++) {
            finalStr += last[i];
        }

        //return little endian form
        return finalStr;

    }

    //method for calculate unsigned int data type 
    public static long unsigned(String b) {
        int j = 0;
        long num = 0;
        int length = b.length();

        //if the given string's char at index i is equal to 1 add 2^j to the decimal value
        //start from the last index of string
        for (int i = length - 1; i >= 0; i--) {
            if (b.charAt(i) == '1') {
                num += Math.pow(2, j);
            }
            j++;
        }
        return num;

    }

    //method for calculate signed int data type 
    public static long signed(String b) {
        int j = 0;
        long num = 0;
        int length = b.length();

        //if the given string's char at index i is equal to 1 add 2^j to the decimal value
        for (int i = length - 1; i > 0; i--) {
            if (b.charAt(i) == '1') {
                num += Math.pow(2, j);
            }
            j++;
        }
        //if the number is negative (first index is '1') add (-2)^(length-1) to the decimal value
        if (b.charAt(0) == '1') {
            num -= Math.pow(2, length - 1);
        }

        return num;

    }

    //find signed bit value for calculate floating point number
    public static int signedBit(String b) {
        int sBit;
        if (b.charAt(0) == '1') {
            sBit = -1;
        } else {
            sBit = 1;
        }
        return sBit;
    }

    //method for calculate fraction part of the floating point number
    public static double fraction(String b) {
        int size = b.length();
        double fractionPart = 0;
        double truncationPart = 0;

        switch (size) {
            //if data type size is 1 byte - 8 bit
            case 8:
                int j = 1;
                //fraction part between index 5 and 7 - 3 bit
                for (int i = 5; i <= 7; i++) {
                    if (b.charAt(i) == '1') {
                        fractionPart +=Math.pow(0.5, j);
                    }
                    j++;
                }
                break;
            //if data type size is 2 byte - 16 bit
            case 16:
                int k = 1;
                //fraction part between index 7 and 15 - 9 bit
                for (int i = 7; i <= 15; i++) {
                    if (b.charAt(i) == '1') {
                        fractionPart += Math.pow(0.5, k);
                    }
                    k++;
                }
                break;
            //if data type size is 3 byte - 24 bit
            case 24:
                int z = 1;
                //fraction part between index 9 and 23 - 16 bit but take just 13 bit
                for (int i = 9; i <= 21; i++) {
                    if (b.charAt(i) == '1') {
                        fractionPart += Math.pow(0.5, z);
                    }
                    z++;
                }

                int y = 1;
                //truncation part 
                for (int i = 22; i <= 23; i++) {
                    if (b.charAt(i) == '1') {
                        truncationPart += Math.pow(0.5, y);
                    }
                    y++;
                }

                //rules for round the fraction part
                if (truncationPart > 0.5) {
                    fractionPart += Math.pow(0.5, 12);
                } else if ((truncationPart == 0.5) && (b.charAt(21) == '1')) {
                    fractionPart += Math.pow(0.5, 12);
                }

                break;
            //if data type size is 4 byte - 32 bit
            case 32:
                int m = 1;
                //fraction part between index 11 and 31 - 21 bit but take just 13 bit
                for (int i = 11; i <= 23; i++) {
                    if (b.charAt(i) == '1') {
                        fractionPart += Math.pow(0.5, m);
                    }
                    m++;
                }
                int n = 1;
                //truncation part
                for (int i = 24; i <= 31; i++) {
                    if (b.charAt(i) == '1') {
                        truncationPart += Math.pow(0.5, n);
                    }
                    n++;
                }
                //rules for round the fraction part
                if (truncationPart > 0.5) {
                    fractionPart += Math.pow(0.5, 13);
                } else if ((truncationPart == 0.5) && (b.charAt(23) == '1')) {
                    fractionPart += Math.pow(0.5, 13);
                }

                break;
        }

        return fractionPart;
    }

    //calculate mantissa, exponent then floating point number 
    public static double floatNumber(String b) {
        int size = b.length();
        int l = 0;

        //exponent part
        if (size == 8) {
            l = 4;
        } else if (size == 16) {
            l = 6;
        } else if (size == 24) {
            l = 8;
        } else if (size == 32) {
            l = 10;
        }

        char[] e = new char[l];

        for (int i = 1; i <= l; i++) {
            e[i - 1] = b.charAt(i);
        }

        String ex = new String(e);

        double Bias = Math.pow(2, l - 1) - 1;
        double E = 0;
        double mantissa = 0;

        //if the exponent parts unsigned value is zero
        if (unsigned(ex) == 0) {
            E = 1 - Bias;
            mantissa = fraction(b);
        }
        //if the exponent part is not 00..000 and not 111...111
        else if ((unsigned(ex) != 0) && (unsigned(ex) != (2 ^ l - 1))) {
            E = unsigned(ex) - Bias;
            mantissa = 1 + fraction(b);
        }

        //decimal value rule 
        double decimalValue = (signedBit(b) * mantissa * Math.pow(2, E));

        //if the exponent part is 11...11 and fraction part is 00...00 and signed bit is 0
        if ((unsigned(ex) == (Math.pow(2, l) - 1)) && fraction(b) == 0.0 && signedBit(b) == 1) {
            decimalValue = Double.POSITIVE_INFINITY;
        } //if the exponent part is 11...11 and fraction part is 00...00 and signed bit is 1
        else if ((unsigned(ex) == (Math.pow(2, l) - 1)) && fraction(b) == 0.0 && signedBit(b) == -1) {
            decimalValue = Double.NEGATIVE_INFINITY;
        }
        //if the exponent part is 11...11 and fraction part is not 00...00
        else if ((unsigned(ex) == (Math.pow(2, l) - 1)) && fraction(b) != 0.0) {
            decimalValue = Double.NaN;
        }

        //return decimal value of the floating point binary string
        return decimalValue;
    }


    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);

        //get input file name from user
        System.out.print("Input file name (.txt): ");
        String inputFileName = input.next();

        //get byte ordering type from user
        System.out.print("Byte ordering (little endian - big endian) : ");
        String b_type = input.next();
        String bType= b_type.toLowerCase();
        input.next();

        //get data type from user
        System.out.print("Data type (unsigned - signed - float) : ");
        String d_type = input.next();
        String dType = d_type.toLowerCase();

        //get data type size from user
        System.out.print("Data type size: ");
        int size = input.nextInt();
        if(size>4 || size<1){
            System.out.println("Invalid data size! Please enter a number between 1 and 4.");
        }

        //read the input file and write output to the file
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(inputFileName)))) {
            PrintStream writeToTxt = new PrintStream(new FileOutputStream("output.txt", false));
            String h_number = null;
            while (scanner.hasNextLine()) {

                if(size>4 || size<1){
                    break;
                }

                //get whole line from input file
                String numInput = scanner.nextLine();

                for (int i = 0; i < numInput.length(); i += size * 3) {
                    //read up to data size (two number and one empty space = size*3)
                    int endIndex = Math.min(i + size * 3, numInput.length());
                    String num = numInput.substring(i, endIndex);

                    //if byte ordering type is little endian call L_B_Endian method
                    if (bType.contains("little")) {
                        h_number = L_B_Endian(num);
                    } else {
                        String[] array = num.split(" ");
                        h_number = "";

                        for (int j = 0; j < size; j++) {
                            h_number += array[j];
                        }
                    }

                    //convert hexadecimal string to binary string
                    String b_number = hexToBinary(h_number);

                    //if data type is unsigned
                    if (dType.contains("unsigned")) {
                        long d_number = unsigned(b_number);
                        writeToTxt.print(d_number + " ");	//write decimal value to the output file
                    }
                    //if data type is signed
                    else if (dType.contains("int")||dType.contains("signed")) {
                        long d_number = signed(b_number);
                        writeToTxt.print(d_number + " ");
                    }
                    //if data type is floating point number
                    else if (dType.contains("float")) {
                        double d_number = floatNumber(b_number);
                        String dN = Double.toString(d_number);


                        //round the decimal value of floating point number to maximum 5 digits after the decimal point
                        if(dN.contains("e")||dN.contains("E")){
                            int e= dN.indexOf('E');
                            char[] fr= new char[e+1];
                            char[] st= new char[dN.length()-e];
                            for(int k=0;k<e;k++){
                                fr[k]=dN.charAt(k);
                            }
                            for(int k=e;k<dN.length();k++){
                                st[k-e]=dN.charAt(k);
                            }
                            String frr = new String(fr);
                            String stt = new String(st);
                            double n= Double.parseDouble(frr);
                            writeToTxt.printf("%.5f",n);
                            writeToTxt.print(stt+" ");

                        }else if(d_number==0){
                            writeToTxt.print(new DecimalFormat("##.#####").format(d_number)+" ");

                        }else{
                            writeToTxt.printf("%.5f ",d_number);

                        }
                    }

                }
                writeToTxt.println("");

            }
            writeToTxt.close();
            input.close();
        }

        catch (FileNotFoundException e) {
            input.close();
            throw new RuntimeException(e);

        }

    }

}