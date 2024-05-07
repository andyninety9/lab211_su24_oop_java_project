/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DataLayer;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

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
    public List<T> readDataFromFile() throws Exception {
	List<T> result = new ArrayList<>();
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
		    result.add(tempObj);
		} catch (EOFException e) {
		    break;
		}
	    }
	    ois.close();
	    fis.close();
	}
	return result;
    }

    @Override
    public void writeDataToFile(List<T> list) throws Exception {
	if (list.isEmpty()) {
	    System.out.println(">>Nothing to save...");
	    return;
	}
	FileOutputStream fos = new FileOutputStream(this.fileName);
	ObjectOutputStream oos = new ObjectOutputStream(fos);
	for (T obj : list) {
	    oos.writeObject(obj);
	}
	System.out.println(">>Saved to file successfully!");
    }

}
