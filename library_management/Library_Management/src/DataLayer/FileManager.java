/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DataLayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
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
    public <K, V> Map<K, V> readDataFromFile() throws Exception {
	Map<K, V> result = new HashMap<>();
	File file = new File(this.fileName);
	if (!file.exists()) {
	    System.out.println("File " + fileName + " does not exist!");
	} else {
	    FileInputStream fis = new FileInputStream(file);
	    ObjectInputStream ois = new ObjectInputStream(fis);

	    result = (Map<K, V>) ois.readObject();

	    ois.close();
	    fis.close();
	    System.out.println(">>Load file " + fileName + " successfully!");
	}
	return result;
    }

    @Override
    public <K, V> void writeDataToFile(Map<String, T> list) throws Exception {
	if (list.isEmpty()) {
	    throw new Exception(">>Nothing to save...");
	}
	FileOutputStream fos = new FileOutputStream(this.fileName);
	ObjectOutputStream oos = new ObjectOutputStream(fos);
	oos.writeObject(list);

	oos.close();
	fos.close();

//	System.out.println(">>Saved to file successfully!");
    }

}
