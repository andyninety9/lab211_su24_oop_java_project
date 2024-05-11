/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DataLayer.UserDao;

import BussinessLayer.Entity.User;
import DataLayer.IFileManager;
import DataLayer.ILibraryDao;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author duyma
 */
public class UserDao implements ILibraryDao<User> {
    IFileManager<User> fileManager;

    Map<String, User> userList = new HashMap<>();

    public UserDao() {
    }

    public UserDao(IFileManager<User> fileManager) {
	this.fileManager = fileManager;
	try {
	    loadDataFromFile();
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
    }

    @Override
    public Map<String, User> getList() throws Exception {
//	if (userList.isEmpty()) {
//	    throw new Exception("Event list is empty");
//	}
	return userList;
    }

    @Override
    public void loadDataFromFile() throws Exception {
	this.userList.putAll(fileManager.readDataFromFile());
    }

    @Override
    public void saveDataToFile() throws Exception {
	fileManager.writeDataToFile(userList);
    }

}
