/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DataLayer;

import BussinessLayer.Entity.ILibraryObject;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author duyma
 * @param <T>
 */
public class FileManager<T> implements IFileManager<T> {
    private String fileName;

    public FileManager() {
    }

    public FileManager(String fileName) {
	this.fileName = fileName;
    }

    @Override
    public <T extends ILibraryObject> Map<T, String> readDataFromFile() throws Exception {
	Map<T, String> result = new HashMap<>();
	File file = new File(this.fileName);
	if (!file.exists()) {
	    System.out.println("File " + fileName + " does not exist!");
	} else {
	    FileInputStream fis = new FileInputStream(file);
	    ObjectInputStream ois = new ObjectInputStream(fis);
	    T tempObj;
	    while (true) {
		try {
		    tempObj = (T) ois.readObject();
		    result.put(tempObj, tempObj.getID());
		} catch (EOFException e) {
		    break;
		}
	    }
	    ois.close();
	    fis.close();
	    System.out.println(">>Load file successfully!");
	}
	return result;
    }

    @Override
    public <T extends ILibraryObject> void writeDataToFile(Map<T, String> list) throws Exception {
	if (list.isEmpty()) {
	    System.out.println(">>Nothing to save...");
	    return;
	}
	FileOutputStream fos = new FileOutputStream(this.fileName);
	ObjectOutputStream oos = new ObjectOutputStream(fos);

	oos.writeObject(list);

	System.out.println(">>Saved to file successfully!");
    }

}
