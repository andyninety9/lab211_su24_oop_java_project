/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DataLayer.Event;

import BussinessLayer.Entity.Event;
import DataLayer.IFileManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duyma
 */
public class EventDao implements IEventDao<Event> {
    IFileManager<Event> fileManager;

    List<Event> eventList = new ArrayList<>();

    public EventDao() {
    }

    public EventDao(IFileManager<Event> fileManager) {
	this.fileManager = fileManager;
	try {
	    loadDataFromFile();
	} catch (Exception ex) {
	    System.out.println(">>" + ex.getMessage());
	}
    }

    @Override
    public void loadDataFromFile() throws Exception {
	this.eventList.addAll(fileManager.readDataFromFile());
    }

    @Override
    public void saveDataToFile() throws Exception {
	fileManager.writeDataToFile(eventList);
    }

    @Override
    public void addNew(Event event) throws Exception {
	eventList.add(event);
    }

    @Override
    public List<Event> getList() throws Exception {
	if (eventList.isEmpty()) {
	    throw new Exception("Event list is empty");
	}
	return eventList;
    }

}
