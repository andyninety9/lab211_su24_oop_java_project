/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Application.UI;

import BussinessLayer.Entity.Book;
import BussinessLayer.Service.IService;

/**
 *
 * @author duyma
 */
public class BookMenu {
    IService<Book> service;

    public BookMenu() {
    }

    public BookMenu(IService<Book> service) {
	this.service = service;
    }

    public void processMenuForBook() {
	boolean isRun = true;
	do {
	    Menu.display(bookMenu);
	    switch (Menu.getUserChoice()) {
	    case 1: {

		break;
	    }
	    case 2: {

		break;
	    }
	    case 3: {

		break;
	    }
	    case 4: {

		break;
	    }
	    default: {
		isRun = false;
		break;
	    }
	    }
	} while (isRun);
    }

    public Book inputBookInformation() {
	Book newBook = new Book();

	return newBook;
    }

    public static String[] bookMenu = { "+--------------------------------------------------+", "       MAIN MENU",
	    "|--------------------------------------------------|", "1. Add a Book", "2. Update Book Information",
	    "3. Delete a Book", "4. Show All Books", "5. Others - Quits",
	    "+--------------------------------------------------+" };
}
