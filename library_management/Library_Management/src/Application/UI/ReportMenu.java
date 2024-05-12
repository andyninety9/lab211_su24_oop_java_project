/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Application.UI;

import BussinessLayer.Entity.Book;
import BussinessLayer.Entity.Borrow;
import BussinessLayer.Service.BookService;
import BussinessLayer.Service.BorrowService;
import BussinessLayer.Service.IService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duyma
 */
public class ReportMenu {
    public void processReportMenu() {
	boolean isRun = true;
	do {
	    Menu.display(borrowMenu);
	    switch (Menu.getUserChoice()) {
	    case 1: {
		handleReportListBookBorrowed();
		break;
	    }
	    case 2: {
		handleReportOverdueBooks();
		break;
	    }
	    case 3: {
		handleFilterBorrowByDate();
		break;
	    }
	    default: {
		isRun = false;
		break;
	    }
	    }
	} while (isRun);
    }

    public void handleReportListBookBorrowed() {
	try {
	    IService<Borrow> borrowService = new BorrowService(Program.BORROWS_FILENAME);
	    List<String> listBorrowedBooks = ((BorrowService) borrowService).getListBookBorrowed();
	    IService<Book> bookService = new BookService(Program.BOOKS_FILENAME);
	    displayListBook(listBorrowedBooks, bookService);

	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
    }

    public void handleFilterBorrowByDate() {
	try {
	    IService<Borrow> borrowService = new BorrowService(Program.BORROWS_FILENAME);
	    ArrayList<Borrow> listFilterd = ((BorrowService) borrowService).getListFilterBorrowByDate();
	    System.out.println("+-----------------------------------------------------------------------+");
	    System.out.println(String.format("|%-10s|%-10s|%-10s|%-12s|%-12s|%-12s|", "    ID", "  USER ID",
		    "  BOOK ID", "    BR.DATE", "    RT.DATE", "   STATUS"));
	    System.out.println("+-----------------------------------------------------------------------+");

	    for (Borrow borrow : listFilterd) {
		System.out.println(borrow);
	    }
	    System.out.println("+-----------------------------------------------------------------------+");

	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
    }

    public void handleReportOverdueBooks() {
	try {
	    IService<Borrow> borrowService = new BorrowService(Program.BORROWS_FILENAME);
	    List<String> listOverdueBooks = ((BorrowService) borrowService).getReportOverdueBooks();

	    IService<Book> bookService = new BookService(Program.BOOKS_FILENAME);
	    displayListBook(listOverdueBooks, bookService);
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
    }

    public void displayListBook(List<String> list, IService<Book> bookService) throws Exception {
	System.out.println(
		"+--------------------------------------------------------------------------------------------+");
	System.out.println(String.format("|%-10s|%-20s|%-20s|%-4s|%-15s|%-10s|%-7s|", "    ID", "        NAME",
		"       AUTHOR", "YEAR", "   PUBLISHER", "   ISBN", " STATUS"));
	System.out.println(
		"+--------------------------------------------------------------------------------------------+");
	for (String idBook : list) {
	    System.out.println(bookService.getList().get(idBook));
	}
	System.out.println(
		"+--------------------------------------------------------------------------------------------+");
    }

    public static String[] borrowMenu = { "+--------------------------------------------------+", "       REPORT MENU",
	    "|--------------------------------------------------|", "1. Report on Borrowed Books",
	    "2. Report on Overdue Books", "3. Total Borrow Activities", "4. Others - Quits",
	    "+--------------------------------------------------+" };
}
