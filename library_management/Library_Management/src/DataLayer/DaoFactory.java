/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DataLayer;

import DataLayer.BookDao.BookDao;
import DataLayer.BorrowDao.BorrowDao;
import DataLayer.UserDao.UserDao;

/**
 *
 * @author duyma
 */
public class DaoFactory implements IDaoFactory {
    IFileManager fileManager;

    public DaoFactory() {
    }

    public DaoFactory(String inputDataFile) {
	this.fileManager = new FileManager(inputDataFile);
    }

    @Override
    public ILibraryDao bookDao() throws Exception {
	return new BookDao(fileManager);
    }

    @Override
    public ILibraryDao userDao() throws Exception {
	return new UserDao(fileManager);
    }

    @Override
    public ILibraryDao borrowDao() throws Exception {
	return new BorrowDao(fileManager);

    }

}
