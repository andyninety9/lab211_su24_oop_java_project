/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DataLayer.BookDao;

import BussinessLayer.Entity.Book;
import DataLayer.IFileManager;
import DataLayer.ILibraryDao;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author duyma
 */
public class BookDao implements ILibraryDao<Book> {
    IFileManager<Book> fileManager;
    Map<Book, String> bookList = new HashMap<>();

    public BookDao() {
    }

    public BookDao(IFileManager<Book> fileManager) {
	this.fileManager = fileManager;
	try {
	    loadDataFromFile();
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
    }

    @Override
    public Map<Book, String> getList() throws Exception {
	return bookList;
    }

    @Override
    public void loadDataFromFile() throws Exception {
	this.bookList.putAll(fileManager.readDataFromFile());
    }

    @Override
    public void saveDataToFile() throws Exception {
	System.out.println();
	fileManager.writeDataToFile(bookList);
    }

}
