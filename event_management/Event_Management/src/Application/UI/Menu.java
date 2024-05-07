/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Application.UI;

import Application.Utilities.Utils;
import BussinessLayer.Service.IService;

/**
 *
 * @author duyma
 */
public class Menu {

    public static void display(String[] menuContent) {
	for (int i = 0; i < menuContent.length; i++) {
	    if (i == menuContent.length - 1 || i == 0 || i == 2) {
		System.out.println(menuContent[i]);
	    } else {
		System.out.println(String.format("|%13s%-30s%7s|", "", menuContent[i], ""));
	    }
	}
    }

    public static int getUserChoice() {
	int choice = 0;
	try {
	    choice = Utils.getIntegerNumber("Enter your choice: ");
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
	return choice;
    }

    public static void manageEvent(IService service) {
	EventMenu eventMenu = new EventMenu(service);
	eventMenu.processMenuForEmployee();
    }

}
