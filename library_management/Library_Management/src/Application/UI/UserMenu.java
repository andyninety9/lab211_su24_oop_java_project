/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Application.UI;

import Application.Utilities.Utils;
import BussinessLayer.Components.DataValidation;
import BussinessLayer.Entity.User;
import BussinessLayer.Service.IService;
import BussinessLayer.Service.UserService;
import java.util.List;

/**
 *
 * @author duyma
 */
public class UserMenu {
    IService<User> service;

    public UserMenu() {
    }

    public UserMenu(IService<User> service) {
	this.service = service;
    }

    public void processMenuUser() {
	boolean isRun = true;
	do {
	    Menu.display(userMenu);
	    switch (Menu.getUserChoice()) {
	    case 1: {
		handleAddNewUser();
		break;
	    }
	    case 2: {
		handleUpdateUser();
		break;
	    }
	    case 3: {
		handleDeleteUser();
		break;
	    }
	    case 4: {
		handleDisplayUserByIDAsc(false);
		break;
	    }
	    default: {
		isRun = false;
		break;
	    }
	    }
	} while (isRun);
    }

    public User inputUserInformation() throws Exception {
	User newUser = new User();
	try {
	    while (true) {
		String newId = Utils.getString("Create user id: ");
		if (((UserService) service).searchUserById(newId) == null && !newId.isBlank()) {
		    newUser.setId(newId);
		    break;
		} else {
		    System.out.println(">>User id have been used!");
		}
	    }
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
	while (true) {
	    String newName = Utils.getString("Enter a new user name: ");
	    if (!newName.isBlank()) {
		newUser.setName(newName);
		break;
	    }
	}
	while (true) {
	    String dob = Utils.getString("Enter birthday[yyyy-mm-dd]: ");
	    if (DataValidation.validateDate(dob)) {
		newUser.setDateOfBirth(dob);
		break;
	    }
	}

	while (true) {
	    String phone = Utils.getString("Enter user phone number: ");
	    if (DataValidation.validatePhone(phone)) {
		newUser.setPhoneNumber(phone);
		break;
	    }
	}
	while (true) {
	    String email = Utils.getString("Enter user email: ");
	    if (DataValidation.validateEmail(email)) {
		newUser.setEmail(email);
		break;
	    }
	}

	newUser.setStatus(true);

	return newUser;
    }

    public void handleDeleteUser() {
	String idDelete;
	try {
	    idDelete = Utils.getString("Enter user id to remove: ");
	    service.delete(idDelete);
	    handleDisplayUserByIDAsc(true);
	} catch (Exception ex) {
	    System.out.println(">>" + ex.getMessage());
	}
    }

    public void handleUpdateUser() {
	String updateID;
	try {
	    updateID = Utils.getString("Enter user id to update: ");
	    service.update(updateID);
	} catch (Exception e) {
	}
    }

    public void handleDisplayUserByIDAsc(boolean methodDisplay) {

	try {
	    List<String> listUserIdSorted = ((UserService) service).getListUserIdAsc(service.getList());
	    System.out.println(
		    "+---------------------------------------------------------------------------------------+");
	    System.out.println(String.format("|%-10s|%-20s|%-12s|%-12s|%-20s|%-8s|", "   ID", "        NAME",
		    "  BIRTHDAY", "   PHONE", "       EMAIL", " STATUS"));
	    System.out.println(
		    "+---------------------------------------------------------------------------------------+");
	    for (String string : listUserIdSorted) {
		if (methodDisplay) {
		    if (((UserService) service).searchUserById(string).getStatus()) {
			System.out.println(((UserService) service).searchUserById(string));
		    }
		} else {
		    System.out.println(((UserService) service).searchUserById(string));
		}
	    }
	    System.out.println(
		    "+---------------------------------------------------------------------------------------+");
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
    }

    public void handleAddNewUser() {
	try {
	    do {
		User newUser = inputUserInformation();
		service.add(newUser);
		System.out.println(">>Added user successfully!");
	    } while (Utils.confirmChoice("Do you want to continue[YES/NO]: "));
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
    }

    public static String[] userMenu = { "+--------------------------------------------------+", "       USER MENU",
	    "|--------------------------------------------------|", "1. Add a user", "2. Update user Information",
	    "3. Delete a user", "4. Display all users", "5. Others - Quits",
	    "+--------------------------------------------------+" };
}
