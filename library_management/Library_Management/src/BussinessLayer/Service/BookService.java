/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package BussinessLayer.Service;

import BussinessLayer.Entity.Book;
import DataLayer.DaoFactory;
import DataLayer.IDaoFactory;
import DataLayer.ILibraryDao;
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

    @Override
    public void add(Book obj) throws Exception {
	bookAction.getList().put(obj, obj.getID());
    }

    @Override
    public void delete(String id) throws Exception {
	throw new UnsupportedOperationException("Not supported yet."); // Generated from
								       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(String id) throws Exception {
	throw new UnsupportedOperationException("Not supported yet."); // Generated from
								       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Map<Book, String> getList() throws Exception {
	throw new UnsupportedOperationException("Not supported yet."); // Generated from
								       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void saveDataToFile() throws Exception {
	throw new UnsupportedOperationException("Not supported yet."); // Generated from
								       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
