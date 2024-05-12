/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Application.Utilities;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author duyma
 */
public class Utils {
    private static Scanner sc = new Scanner(System.in);

    public static int getIntegerNumber(String mess) throws Exception {
	int result = 0;
	String regex = "^-?\\d+$";
	System.out.print(mess);
	String input = sc.nextLine();

	if (!input.matches(regex)) {
	    throw new Exception("Please enter a number follow instructor...");
	} else {
	    result = Integer.parseInt(input);
	}

	return result;
    }

    public static double getDoubleNumber(String mess) throws Exception {
	double result = 0;
	String regex = "^\\\".*\\\"$|^'.*'$";

	System.out.print(mess);
	String input = sc.nextLine();

	if (!input.matches(regex)) {
	    throw new Exception("Please enter a number follow instructor...");
	} else {
	    result = Double.parseDouble(input);
	}
	return result;
    }

    public static String getString(String mess) throws Exception {
	System.out.print(mess);
	String result = sc.nextLine();
	return result;
    }

    public static String generateID(String prefix) {
	String tmp = Integer.toString(Math.abs((int) System.currentTimeMillis() % (int) (1000000 * Math.random())));
	String result = "" + prefix;
	for (int i = 0; i < 6 - tmp.length(); i++) {
	    result += '0';
	}
	result += tmp;
	return result;
    }

    public static boolean confirmChoice(String mess) {
	sc = new Scanner(System.in);
	System.out.print(mess);
	String choice = sc.nextLine();
	if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
	    return true;
	} else {
	    return false;
	}
    }

    public static String inputStatusBorrow() throws Exception {
	String available = getString("Enter '1' for Borrowing, '2' for Returned or '3' for Overdue: ");

	if (available.equalsIgnoreCase("1")) {
	    return "Borrowing";
	} else if (available.equalsIgnoreCase("2")) {
	    return "Returned";
	} else if (available.equalsIgnoreCase("3")) {
	    return "Overdue";
	}
	return null;
    }

    public static String normalizeName(String s) {
	if (s.isEmpty()) {
	    return s;
	}
	s = s.toLowerCase().replaceAll("\\s+", " ");
	String result = "";
	String[] arrStr = s.trim().split(" ");
	for (String string : arrStr) {
	    char[] charArr = string.toCharArray();
	    charArr[0] = Character.toUpperCase(charArr[0]);
	    result += new String(charArr);
	    result += " ";
	}

	return result.trim();
    }

//    public static void main(String[] args) {
//	try {
//	    ArrayList<Integer> list = new ArrayList<>();
//	    list.add(5);
//	    list.add(2);
//	    list.add(6);
//	    list.add(1);
//	    list.add(3);
//	} catch (Exception e) {
//	    System.out.println(e.getMessage());
//	}
//    }

}
