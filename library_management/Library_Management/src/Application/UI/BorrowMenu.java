/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Application.UI;

import Application.Utilities.Utils;
import BussinessLayer.Entity.Borrow;
import BussinessLayer.Service.BorrowService;
import BussinessLayer.Service.IService;
import java.util.Map;

/**
 *
 * @author duyma
 */
public class BorrowMenu {
    IService<Borrow> borrowService;

    public BorrowMenu() {
    }

    public BorrowMenu(IService<Borrow> borrowService) {
	this.borrowService = borrowService;
    }

    public void processMenuBorrow() {
	boolean isRun = true;
	do {
	    Menu.display(borrowMenu);
	    switch (Menu.getUserChoice()) {
	    case 1: {
		handleCreateANewBorrow();
		break;
	    }
	    case 2: {
		handleUpdateBorrow();
		break;
	    }
	    case 3: {
		handleDisplayAllBorrow();
		break;
	    }
	    default: {
		isRun = false;
		break;
	    }
	    }
	} while (isRun);
    }

    public void handleCreateANewBorrow() {
	try {
	    Borrow newBorrow = ((BorrowService) borrowService).inputBorrowInformation();
	    borrowService.add(newBorrow);
	    System.out.println(">>Added borrow successfully!");
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
    }

    public void handleDisplayAllBorrow() {
	try {
	    System.out.println("+----------------------------------------------------------+");
	    System.out.println(String.format("|%-10s|%-10s|%-10s|%-12s|%-12s|", "    ID", "  USER ID", "  BOOK ID",
		    "    BR.DATE", "    RT.DATE"));
	    System.out.println("+----------------------------------------------------------+");
	    for (Map.Entry<String, Borrow> borrow : borrowService.getList().entrySet()) {
		System.out.println(borrow.getValue());
	    }
	    System.out.println("+----------------------------------------------------------+");
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
    }

    public void handleUpdateBorrow() {
	try {
	    String idUpdate = Utils.getString("Enter borrow id to update: ");
	    borrowService.update(idUpdate);
	    System.out.println(">>Update borrow successfully!");
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
    }

    public static String[] borrowMenu = { "+--------------------------------------------------+", "       LOANS MENU",
	    "|--------------------------------------------------|", "1. Borrow Book", "2. Update borrow Information",
	    "3. Show All Borrow", "4. Others - Quits", "+--------------------------------------------------+" };
}
