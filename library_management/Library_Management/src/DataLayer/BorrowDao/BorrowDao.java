/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DataLayer.BorrowDao;

import BussinessLayer.Entity.Borrow;
import DataLayer.IFileManager;
import DataLayer.ILibraryDao;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author duyma
 */
public class BorrowDao implements ILibraryDao<Borrow> {
    IFileManager<Borrow> fileManager;

    Map<String, Borrow> borrowList = new HashMap<>();

    public BorrowDao() {
    }

    public BorrowDao(IFileManager<Borrow> fileManager) {
	this.fileManager = fileManager;
	try {
	    loadDataFromFile();
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
    }

    @Override
    public Map<String, Borrow> getList() throws Exception {
	if (borrowList.isEmpty()) {
	    throw new Exception("Event list is empty");
	}
	return borrowList;
    }

    @Override
    public void loadDataFromFile() throws Exception {
	this.borrowList.putAll(fileManager.readDataFromFile());
    }

    @Override
    public void saveDataToFile() throws Exception {
	fileManager.writeDataToFile(borrowList);
    }

}
