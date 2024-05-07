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
    public static boolean validateName(String name) {
	String regex = "^[^\\s]*$";
	if (name.length() < 5 || !name.matches(regex)) {
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
	    return false;
	}
    }

    public static boolean validateLocation(String location) {
	if (location.isBlank())
	    return false;
	return true;
    }

    public static boolean validateNoAttendees(int number) {
	if (number <= 0)
	    return false;
	return true;
    }

    public static boolean validateStatus(String status) {
	if (!status.equalsIgnoreCase("Available") || !status.equalsIgnoreCase("Not Available")) {
	    return false;
	}
	return true;
    }

//    public static void main(String[] args) {
//	try {
//	    String s = Utils.getString("Enter: ");
//	    System.out.println(validateDate(s));
//	} catch (Exception e) {
//	    System.out.println(e);
//	}
//
//    }

}
