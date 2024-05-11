/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package BussinessLayer.Components;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author duyma
 */
public class DataValidation {
    public static boolean validatePhone(String phone) {
	String regex = "^[0-9]+$";
	if (phone.length() < 10 || !phone.matches(regex)) {
	    System.out.println(">>Ensure that the phone number is at least 10 number and contain only number");
	    return false;
	}
	return true;
    }

    public static boolean validateEmail(String email) {
	String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	if (!email.matches(regex)) {
	    System.out.println(">>Ensure following email format");
	    return false;
	}
	return true;
    }

    public static boolean validateDate(String dateCheck) {
	try {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate date = LocalDate.parse(dateCheck, formatter);
	    return true;
	} catch (DateTimeParseException e) {
	    System.out.println(">>Ensure that the event date is valid and in the correct format (YYYY-MM-DD)");
	    return false;
	}
    }

    public static boolean validateYear(String dateCheck) {
	try {
	    if (Integer.parseInt(dateCheck) > 0) {
		return true;
	    }
	    return false;
	} catch (DateTimeParseException e) {
	    System.out.println(">>Ensure that the event year is valid and in the correct format (YYYY)");
	    return false;
	}
    }

}
