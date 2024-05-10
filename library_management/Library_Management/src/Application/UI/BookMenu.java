/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Application.UI;

import Application.Utilities.Utils;
import BussinessLayer.Components.DataValidation;
import BussinessLayer.Entity.Book;
import BussinessLayer.Service.BookService;
import BussinessLayer.Service.IService;
import java.util.Map;

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

    public void processMenuBook() {
	boolean isRun = true;
	do {
	    Menu.display(bookMenu);
	    switch (Menu.getUserChoice()) {
	    case 1: {
		handleAddNewBook();
		break;
	    }
	    case 2: {
		handleUpdateBook();
		break;
	    }
	    case 3: {
		handleDeleteBook();
		break;
	    }
	    case 4: {
		handleDisplayAllBook();
		break;
	    }
	    default: {
		isRun = false;
		break;
	    }
	    }
	} while (isRun);
    }

    public void handleAddNewBook() {
	try {
	    do {
		Book newBook = inputBookInformation();
		service.add(newBook);
		System.out.println(">>Added new book successfully!");
	    } while (Utils.confirmChoice("Do you want to continue[YES/NO]: "));
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
    }

    public void handleSaveDataToFile() {
	try {
	    service.saveDataToFile();
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
    }

    public void handleDeleteBook() {
	try {
	    handleDisplayOnlyTrueBook();
	    String idUpdate = Utils.getString("Enter book id to detele: ");
	    service.delete(idUpdate);
	    handleDisplayOnlyTrueBook();
	} catch (Exception e) {
	}
    }

    public void handleUpdateBook() {
	try {
	    handleDisplayAllBook();
	    String idUpdate = Utils.getString("Enter book id to update: ");
	    service.update(idUpdate);
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}

    }

    public void handleDisplayAllBook() {
	try {
	    System.out.println(
		    "+--------------------------------------------------------------------------------------------+");
	    System.out.println(String.format("|%-10s|%-20s|%-20s|%-4s|%-15s|%-10s|%-7s|", "    ID", "        NAME",
		    "       AUTHOR", "YEAR", "   PUBLISHER", "   ISBN", " STATUS"));
	    System.out.println(
		    "+--------------------------------------------------------------------------------------------+");

	    for (String idSorted : ((BookService) service).sortListBookByIDAsc(service.getList())) {
		System.out.println(((BookService) service).searchBookById(idSorted));
	    }

	    System.out.println(
		    "+--------------------------------------------------------------------------------------------+");
	} catch (Exception ex) {
	    System.out.println(">>" + ex.getMessage());
	}
    }

    public void handleDisplayOnlyTrueBook() {
	try {
	    System.out.println(
		    "+--------------------------------------------------------------------------------------------+");
	    System.out.println(String.format("|%-10s|%-20s|%-20s|%-4s|%-15s|%-10s|%-7s|", "    ID", "        NAME",
		    "       AUTHOR", "YEAR", "   PUBLISHER", "   ISBN", " STATUS"));
	    System.out.println(
		    "+--------------------------------------------------------------------------------------------+");
	    for (Map.Entry<String, Book> entry : service.getList().entrySet()) {
		if (entry.getValue().getStatus()) {
		    System.out.println(entry.getValue());
		}
	    }
	    System.out.println(
		    "+--------------------------------------------------------------------------------------------+");
	} catch (Exception ex) {
	    System.out.println(">>" + ex.getMessage());
	}
    }

    public Book inputBookInformation() throws Exception {
	Book newBook = new Book();
	try {
	    while (true) {
		String newId = Utils.getString("Create book id: ");
		if (((BookService) service).searchBookById(newId) == null) {
		    newBook.setId(newId);
		    break;
		} else {
		    System.out.println(">>Book id have been used!");
		}
	    }
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
	try {
	    while (true) {
		String newName = Utils.getString("Enter book name: ");
		newBook.setName(newName);
		break;
	    }
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
	try {
	    while (true) {
		String newAuthor = Utils.getString("Enter book author: ");
		newBook.setAuthor(newAuthor);
		break;
	    }
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}

	try {
	    while (true) {
		String newPublicationYear = Utils.getString("Enter book publication year: ");
		if (DataValidation.validateYear(newPublicationYear)) {
		    newBook.setPublicationYear(newPublicationYear);
		    break;
		}
	    }
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}

	try {
	    while (true) {
		String newPublisher = Utils.getString("Enter book publisher: ");
		newBook.setPublisher(newPublisher);
		break;
	    }
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}

	try {
	    while (true) {
		String newISBN = Utils.getString("Enter book ISBN: ");
		newBook.setISBN(newISBN);
		break;
	    }
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}

	newBook.setStatus(true);
	return newBook;
    }

    public static String[] bookMenu = { "+--------------------------------------------------+", "       MAIN MENU",
	    "|--------------------------------------------------|", "1. Add a Book", "2. Update Book Information",
	    "3. Delete a Book", "4. Show All Books", "5. Others - Quits",
	    "+--------------------------------------------------+" };
}
