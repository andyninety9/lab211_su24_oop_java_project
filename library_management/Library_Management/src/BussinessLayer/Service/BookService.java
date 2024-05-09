/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package BussinessLayer.Service;

import Application.Utilities.Utils;
import BussinessLayer.Components.DataValidation;
import BussinessLayer.Entity.Book;
import DataLayer.DaoFactory;
import DataLayer.IDaoFactory;
import DataLayer.ILibraryDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author duyma
 */
public class BookService implements IService<Book> {
    ILibraryDao bookAction;
    IDaoFactory bookDaoFactory;

    public BookService() {
    }

    public BookService(String inputFileName) throws Exception {
	bookDaoFactory = new DaoFactory(inputFileName);
	this.bookAction = bookDaoFactory.bookDao();
    }

    public Book searchBookById(String id) throws Exception {
	if (bookAction.getList().isEmpty()) {
	    return null;
	}
	for (Map.Entry<Book, String> entry : getList().entrySet()) {
	    if (entry.getValue().equalsIgnoreCase(id)) {
		return entry.getKey();
	    }
	}

	return null;
    }

    public List<String> sortListBookByIDAsc(Map<Book, String> list) throws Exception {
	List<String> sortedBookID = new ArrayList<>();
	for (Map.Entry<Book, String> entry : getList().entrySet()) {
	    sortedBookID.add(entry.getValue());
	}

	for (int i = 0; i < sortedBookID.size(); i++) {
	    for (int j = i; j < sortedBookID.size(); j++) {
		if (sortedBookID.get(i).compareTo(sortedBookID.get(j)) > 0) {
		    String tmp = sortedBookID.get(i);
		    sortedBookID.set(i, sortedBookID.get(j));
		    sortedBookID.set(j, tmp);
		}
	    }
	}
	return sortedBookID;
    }

    @Override
    public void add(Book obj) throws Exception {
	bookAction.getList().put(obj, obj.getId());
    }

    @Override
    public void delete(String id) throws Exception {
	Book tempBook = searchBookById(id);
	if (tempBook == null) {
	    throw new Exception(">>Book ID does not exist!");
	}
	if (Utils.confirmChoice("Do you want to hide " + id + " [YES/NO]: ")) {
	    for (Map.Entry<Book, String> entry : getList().entrySet()) {
		if (entry.getValue().equalsIgnoreCase(id)) {
		    entry.getKey().setStatus(false);
		}
	    }
	    System.out.println(">>Hide book successfully!");
	}
    }

    @Override
    public void update(String id) throws Exception {
	Book tempBook = searchBookById(id);
	if (tempBook == null) {
	    throw new Exception(">>Book ID does not exist!");
	}
	while (true) {
	    String newName = Utils.getString("Update a new book name: ");
	    if (newName.isBlank()) {
		break;
	    } else {
		tempBook.setName(newName);
		break;
	    }
	}
	while (true) {
	    String newAuthor = Utils.getString("Update a new book author: ");
	    if (newAuthor.isBlank()) {
		break;
	    } else {
		tempBook.setAuthor(newAuthor);
		break;
	    }
	}
	while (true) {
	    String newYear = Utils.getString("Update a new book year: ");
	    if (newYear.isBlank()) {
		break;
	    } else if (!newYear.isBlank() && DataValidation.validateYear(newYear)) {
		tempBook.setPublicationYear(newYear);
		break;
	    }
	}
	while (true) {
	    String newPublisher = Utils.getString("Update a new book publisher: ");
	    if (newPublisher.isBlank()) {
		break;
	    } else {
		tempBook.setPublisher(newPublisher);
		break;
	    }
	}
	while (true) {
	    String newISBN = Utils.getString("Update a new book ISBN: ");
	    if (newISBN.isBlank()) {
		break;
	    } else {
		tempBook.setISBN(newISBN);
		break;
	    }
	}

	boolean newStatus = Utils.confirmChoice("Enter [YES/NO] for book status: ");
	tempBook.setStatus(newStatus);

	if (Utils.confirmChoice("Do you want to update " + id + " [YES/NO]: ")) {
	    bookAction.getList().remove(id);
	    bookAction.getList().put(tempBook, id);
	    System.out.println(">>Updated book successfully!");
	}

    }

    @Override
    public Map<Book, String> getList() throws Exception {
	return bookAction.getList();
    }

    @Override
    public void saveDataToFile() throws Exception {
	bookAction.saveDataToFile();
    }

}
