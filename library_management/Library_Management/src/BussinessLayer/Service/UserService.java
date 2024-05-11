/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package BussinessLayer.Service;

import Application.Utilities.Utils;
import BussinessLayer.Components.DataValidation;
import BussinessLayer.Entity.User;
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
public class UserService implements IService<User> {
    ILibraryDao userAction;
    IDaoFactory userDaoFactory;

    public UserService() {
    }

    public UserService(String inputFileName) throws Exception {
	userDaoFactory = new DaoFactory(inputFileName);
	this.userAction = userDaoFactory.userDao();
    }

    public User searchUserById(String id) throws Exception {
	if (getList().isEmpty()) {
	    return null;
	}
	for (Map.Entry<String, User> entry : getList().entrySet()) {
	    if (entry.getKey().equalsIgnoreCase(id)) {
		return entry.getValue();
	    }
	}
	return null;
    }

    public List<String> getListUserIdAsc(Map<String, User> list) {
	List<String> listUserID = new ArrayList<>();
	for (String userID : list.keySet()) {
	    listUserID.add(userID);
	}

	for (int i = 0; i < listUserID.size() - 1; i++) {
	    int minIndex = i;
	    for (int j = i + 1; j < listUserID.size(); j++) {
		if (listUserID.get(j).compareTo(listUserID.get(minIndex)) < 0) {
		    minIndex = j;
		}
	    }
	    String tmp = listUserID.get(minIndex);
	    listUserID.set(minIndex, listUserID.get(i));
	    listUserID.set(i, tmp);
	}
	return listUserID;
    }

    @Override
    public void add(User user) throws Exception {
	getList().put(user.getId(), user);
    }

    @Override
    public void delete(String id) throws Exception {
	if (searchUserById(id) == null) {
	    throw new Exception("User ID is not available!");
	}
	if (Utils.confirmChoice("Do you want to delete " + id + " [YES/NO]: ")) {
	    for (Map.Entry<String, User> user : getList().entrySet()) {
		if (user.getKey().equalsIgnoreCase(id)) {
		    user.getValue().setStatus(false);
		}
	    }
	    System.out.println(">>Delete user successfully!");
	}
    }

    @Override
    public void update(String id) throws Exception {
	User updateUser = searchUserById(id);
	if (updateUser == null) {
	    throw new Exception("User ID is not available!");
	}
	while (true) {
	    String newName = Utils.getString("Update a new username: ");
	    if (newName.isBlank()) {
		break;
	    } else {
		updateUser.setName(newName);
		break;
	    }
	}
	while (true) {
	    String dob = Utils.getString("Update birthday[yyyy-mm-dd]: ");
	    if (dob.isBlank()) {
		break;
	    }
	    if (DataValidation.validateDate(dob)) {
		updateUser.setDateOfBirth(dob);
		break;
	    }
	}
	while (true) {
	    String phone = Utils.getString("Update user phone number: ");
	    if (phone.isBlank()) {
		break;
	    }
	    if (DataValidation.validatePhone(phone)) {
		updateUser.setPhoneNumber(phone);
		break;
	    }
	}
	while (true) {
	    String email = Utils.getString("Update user email: ");
	    if (email.isBlank()) {
		break;
	    }
	    if (DataValidation.validateEmail(email)) {
		updateUser.setEmail(email);
		break;
	    }
	}
	updateUser.setStatus(Utils.confirmChoice("Update user status[y-true | others-false]: "));

	if (Utils.confirmChoice("Do you want to update " + id + " [YES/NO]: ")) {
	    getList().replace(id, searchUserById(id), updateUser);
	    System.out.println(">>Updates successfully!");
	}
    }

    @Override
    public Map<String, User> getList() throws Exception {
	return userAction.getList();
    }

    @Override
    public void saveDataToFile() throws Exception {
	userAction.saveDataToFile();
    }

}
