/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Application.UI;

import Application.Utilities.Utils;
import BussinessLayer.Service.BookService;
import BussinessLayer.Service.BorrowService;
import BussinessLayer.Service.IService;
import BussinessLayer.Service.UserService;

/**
 *
 * @author duyma
 */
public class Program {
    public static final String BOOKS_FILENAME = "books.dat";
    public static final String USERS_FILENAME = "users.dat";
    public static final String BORROWS_FILENAME = "borrows.dat";

    public static void main(String[] args) {
	boolean isRun = true;
	try {
	    IService bookService = new BookService(BOOKS_FILENAME);
	    IService userService = new UserService(USERS_FILENAME);
	    IService borrowService = new BorrowService(BORROWS_FILENAME);
	    do {
		Menu.display(mainMenu);
		switch (Menu.getUserChoice()) {
		case 1: {
		    Menu.manageBook(bookService);
		    break;
		}
		case 2: {
		    Menu.manageUser(userService);
		    break;
		}
		case 3: {
		    Menu.manageBorrow(borrowService);
		    break;
		}
		case 4: {

		    break;
		}
		case 5: {
		    handleSaveData(bookService, userService, borrowService);
		    break;
		}
		default: {
		    if (Utils.confirmChoice("Do you want to save data before closing[YES/NO]: ")) {
			handleSaveData(bookService, userService, borrowService);
		    }
		    isRun = false;
		    System.out.println("Thank you!");
		    break;
		}
		}
	    } while (isRun);
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
    }

    public static void handleSaveData(IService bookService, IService userService, IService borrowService)
	    throws Exception {
	bookService.saveDataToFile();
	userService.saveDataToFile();
	borrowService.saveDataToFile();
	System.out.println(">>Save data successfully!");
    }

    public static String[] mainMenu = { "+--------------------------------------------------+", "       MAIN MENU",
	    "|--------------------------------------------------|", "1. Manage Books", "2. Manage Users",
	    "3. Manage Loans", "4. Reporting", "5. Store Data to Files", "6. Others - Quits",
	    "+--------------------------------------------------+" };
}
