/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package BussinessLayer.Service;

import Application.UI.BookMenu;
import Application.UI.Program;
import Application.UI.UserMenu;
import Application.Utilities.Utils;
import BussinessLayer.Components.DataValidation;
import BussinessLayer.Entity.Book;
import BussinessLayer.Entity.Borrow;
import BussinessLayer.Entity.User;
import DataLayer.DaoFactory;
import DataLayer.IDaoFactory;
import DataLayer.ILibraryDao;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author duyma
 */
public class BorrowService implements IService<Borrow> {
    ILibraryDao borrowAction;
    IDaoFactory borrowDaoFactory;

    public BorrowService() {
    }

    public BorrowService(String inputFileName) throws Exception {
	borrowDaoFactory = new DaoFactory(inputFileName);
	this.borrowAction = borrowDaoFactory.borrowDao();
    }

    public Borrow searchBorrowById(String id) throws Exception {
	if (borrowAction.getList().isEmpty()) {
	    return null;
	}

	for (Map.Entry<String, Borrow> borrow : getList().entrySet()) {
	    if (borrow.getKey().equalsIgnoreCase(id)) {
		return borrow.getValue();
	    }
	}
	return null;
    }

    public Borrow inputBorrowInformation() throws Exception {
	Borrow newBorrow = new Borrow();

	try {
	    while (true) {
		String newId = Utils.getString("Create borrow id: ");
		if (searchBorrowById(newId) == null) {
		    newBorrow.setId(newId);
		    break;
		} else {
		    System.out.println(">>Borrow id have been used!");
		}
	    }
	    System.out.println(newBorrow.getId());
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
	IService<User> userService = new UserService(Program.USERS_FILENAME);
	UserMenu userMenu = new UserMenu(userService);
	userMenu.handleDisplayUserByIDAsc(true);
	try {
	    while (true) {
		String idUser = Utils.getString("Enter user borrow book: ");
		if (checkUserBorrow(idUser, (UserService) userService)) {
		    newBorrow.setUserId(idUser);
		    break;
		}
	    }

	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
	IService<Book> bookService = new BookService(Program.BOOKS_FILENAME);
	BookMenu bookMenu = new BookMenu(bookService);
	bookMenu.handleDisplayAllBook();
	try {
	    while (true) {
		String bookId = Utils.getString("Enter book id to borrow: ");
		if (checkBookBorrow(bookId, (BookService) bookService)) {
		    newBorrow.setBookId(bookId);
		    break;
		}
	    }
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}

	try {
	    while (true) {
		String borrowDate = Utils.getString("Enter borrow date[yyyy-mm-dd]: ");
		if (DataValidation.validateDate(borrowDate)) {
		    newBorrow.setBorrowDate(borrowDate);
		    break;
		}
	    }
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
	try {
	    while (true) {
		String returnDate = Utils.getString("Enter return date[yyyy-mm-dd]: ");
		if (checkReturnDate(newBorrow.getBorrowDate(), returnDate)) {
		    newBorrow.setReturnDate(returnDate);
		    break;
		}
		System.out.println(">>Return day must be a day after borrow date[yyyy-mm-dd]");
	    }
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}

	try {
	    String statusBorrowing = Utils.inputStatusBorrow();
	    newBorrow.setStatus(statusBorrowing);
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}

	return newBorrow;
    }

    public boolean checkReturnDate(String borrowDate, String returnDate) {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	LocalDate date1 = LocalDate.parse(borrowDate, formatter);
	LocalDate date2 = LocalDate.parse(returnDate, formatter);

	if (DataValidation.validateDate(returnDate) == false || date1.compareTo(date2) > 0) {
	    return false;
	}
	return true;
    }

    public boolean checkUserBorrow(String userId, UserService userService) throws Exception {
	if (userService.searchUserById(userId) == null) {
	    System.out.println(">>User does not exist");
	    return false;
	} else if (((UserService) userService).searchUserById(userId).getStatus() == false) {
	    System.out.println(">>User inactived status");
	    return false;
	}
	return true;
    }

    public boolean checkBookBorrow(String bookId, BookService bookService) throws Exception {
	if (bookService.searchBookById(bookId) == null) {
	    System.out.println(">>Book does not exist!");
	    return false;
	} else if (bookService.searchBookById(bookId).getStatus() == false) {
	    System.out.println(">>Book inactived status");
	    return false;
	}
	return true;
    }

    public List<String> getListBookBorrowed() throws Exception {
	List<String> listBorrowedBooks = new ArrayList<>();
	Set<String> listCheck = new TreeSet<>();
	for (Borrow b : getList().values()) {
	    if (!listCheck.contains(b.getBookId())) {
		listBorrowedBooks.add(b.getBookId());
		listCheck.add(b.getBookId());
	    }
	}
	return sortIDByASC(listBorrowedBooks);
    }

    public ArrayList<Borrow> getListFilterBorrowByDate() throws Exception {
//	try {
	String date2, date1;

	while (true) {
	    date1 = Utils.getString("From date[yyyy-mm-dd]: ");
	    if (DataValidation.validateDate(date1)) {
		break;
	    }
	}
	while (true) {
	    date2 = Utils.getString("To date[yyyy-mm-dd]: ");
	    if (DataValidation.validateDate(date2)) {
		break;
	    }
	}
	IService<Borrow> borrowService = new BorrowService(Program.BORROWS_FILENAME);
	ArrayList<Borrow> listFiltered = new ArrayList<>();
//	    System.out.println("+-----------------------------------------------------------------------+");
//	    System.out.println(String.format("|%-10s|%-10s|%-10s|%-12s|%-12s|%-12s|", "    ID", "  USER ID",
//		    "  BOOK ID", "    BR.DATE", "    RT.DATE", "   STATUS"));
//	    System.out.println("+-----------------------------------------------------------------------+");
	for (Borrow b : borrowService.getList().values()) {
	    if (date1.compareTo(b.getBorrowDate()) < 0 && b.getBorrowDate().compareTo(date2) < 0) {
//		    System.out.println(b);
		listFiltered.add(b);
	    }
	}
	System.out.println("+-----------------------------------------------------------------------+");
	return listFiltered;
//	} catch (Exception e) {
//	}
    }

    public List<String> getReportOverdueBooks() throws Exception {

	List<String> listIDOverdueBooks = new ArrayList<>();
	for (Map.Entry<String, Borrow> borrow : getList().entrySet()) {
	    if (borrow.getValue().getStatus().equalsIgnoreCase("Overdue")) {
		listIDOverdueBooks.add(borrow.getValue().getBookId());
	    }
	}
	return sortIDByASC(listIDOverdueBooks);
    }

    public List<String> sortIDByASC(List<String> list) {
	for (int i = 0; i < list.size() - 1; i++) {
	    int minIndex = i;
	    for (int j = i + 1; j < list.size(); j++) {
		if (list.get(j).compareTo(list.get(minIndex)) < 0) {
		    minIndex = j;
		}
	    }

	    String tmp = list.get(minIndex);
	    list.set(minIndex, list.get(i));
	    list.set(i, tmp);
	}
	return list;
    }

    @Override
    public void add(Borrow obj) throws Exception {
	borrowAction.getList().put(obj.getId(), obj);
    }

    @Override
    public void delete(String id) throws Exception {
	throw new UnsupportedOperationException("Not supported yet."); // Generated from
								       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(String id) throws Exception {
	Borrow updateBorrow = searchBorrowById(id);
	if (updateBorrow == null) {
	    throw new Exception("Borrow id does not exist!");
	}
	IService<User> userService = new UserService(Program.USERS_FILENAME);
	IService<Book> bookService = new BookService(Program.BOOKS_FILENAME);
	UserMenu userAction = new UserMenu(userService);
	userAction.handleDisplayUserByIDAsc(true);
	try {
	    while (true) {
		String idUser = Utils.getString("Update user borrow book: ");
		if (idUser.isBlank()) {
		    break;
		}
		if (checkUserBorrow(idUser, (UserService) userService)) {
		    updateBorrow.setUserId(idUser.toUpperCase());
		    break;
		}
	    }

	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
	try {
	    while (true) {
		String idBook = Utils.getString("Update book id: ");
		if (idBook.isBlank()) {
		    break;
		}
		if (checkBookBorrow(idBook, (BookService) bookService)) {
		    updateBorrow.setBookId(idBook.toUpperCase());
		    break;
		}
	    }

	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
	try {
	    while (true) {
		String borrowDate = Utils.getString("Enter borrow date[yyyy-mm-dd]: ");
		if (borrowDate.isBlank()) {
		    break;
		}
		if (DataValidation.validateDate(borrowDate)) {
		    updateBorrow.setBorrowDate(borrowDate);
		    break;
		}
	    }
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
	try {
	    while (true) {
		String returnDate = Utils.getString("Enter return date[yyyy-mm-dd]: ");
		if (returnDate.isBlank() && checkReturnDate(updateBorrow.getBorrowDate(), returnDate)) {
		    break;
		}
		if (checkReturnDate(updateBorrow.getBorrowDate(), returnDate)) {
		    updateBorrow.setReturnDate(returnDate);
		    break;
		}
		System.out.println(">>Return day must be a day after borrow date[yyyy-mm-dd]");
	    }
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}

	try {
	    while (true) {
		String statusBorrowing = Utils.inputStatusBorrow();
		if (statusBorrowing == null) {
		    break;
		}
		updateBorrow.setStatus(statusBorrowing);
		break;
	    }
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}

	if (Utils.confirmChoice("Do you want to update " + id + " [YES/NO]: ")) {
	    getList().replace(id, searchBorrowById(id), updateBorrow);
	}
    }

    @Override
    public Map<String, Borrow> getList() throws Exception {
	return borrowAction.getList();
    }

    @Override
    public void saveDataToFile() throws Exception {
	borrowAction.saveDataToFile();
    }

}
