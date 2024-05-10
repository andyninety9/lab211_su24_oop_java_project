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
	for (Map.Entry<String, Book> entry : getList().entrySet()) {
	    if (entry.getKey().equalsIgnoreCase(id)) {
		return entry.getValue();
	    }
	}

	return null;
    }

    public List<String> sortListBookByIDAsc(Map<String, Book> list) throws Exception {
	List<String> sortedBookIDList = new ArrayList<>();
	for (Map.Entry<String, Book> entry : getList().entrySet()) {
	    sortedBookIDList.add(entry.getKey());
	}

	for (int i = 0; i < sortedBookIDList.size() - 1; i++) {
	    int minIndex = i;
	    for (int j = i + 1; j < sortedBookIDList.size(); j++) {
		if (sortedBookIDList.get(j).compareTo(sortedBookIDList.get(minIndex)) < 0) {
		    minIndex = j;
		}
	    }

	    String tmp = sortedBookIDList.get(minIndex);
	    sortedBookIDList.set(minIndex, sortedBookIDList.get(i));
	    sortedBookIDList.set(i, tmp);
	}
	return sortedBookIDList;
    }

    @Override
    public void add(Book obj) throws Exception {
	bookAction.getList().put(obj.getId(), obj);
    }

    @Override
    public void delete(String id) throws Exception {
	Book tempBook = searchBookById(id);
	if (tempBook == null) {
	    throw new Exception(">>Book ID does not exist!");
	}
	if (Utils.confirmChoice("Do you want to hide " + id + " [YES/NO]: ")) {
	    for (Map.Entry<String, Book> entry : getList().entrySet()) {
		if (entry.getKey().equalsIgnoreCase(id)) {
		    entry.getValue().setStatus(false);
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
	    bookAction.getList().replace(id, searchBookById(id), tempBook);
	    System.out.println(">>Updated book successfully!");
	}

    }

    @Override
    public Map<String, Book> getList() throws Exception {
	return bookAction.getList();
    }

    @Override
    public void saveDataToFile() throws Exception {
	bookAction.saveDataToFile();
    }

}
