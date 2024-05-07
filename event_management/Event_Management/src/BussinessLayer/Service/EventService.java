/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package BussinessLayer.Service;

import BussinessLayer.Entity.Event;
import DataLayer.DaoFactory;
import DataLayer.Event.IEventDao;
import DataLayer.IDaoFactory;
import java.util.List;

/**
 *
 * @author duyma
 */
public class EventService implements IService<Event> {
    IEventDao eventAction;
    IDaoFactory eventDaoFactory;

    public EventService() {
    }

    public EventService(String inputDataFile) throws Exception {
	eventDaoFactory = new DaoFactory(inputDataFile);
	this.eventAction = eventDaoFactory.eventDao();
    }

    @Override
    public void printList() throws Exception {
	System.out.println("+-----------------------------------------------------------------------------------+");
	System.out.println(String.format("|%-10s|%-20s|%-10s|%-15s|%-10s|%-13s|", "    ID", "        NAME", "   DATE",
		"   LOCATION", "  NO.ATD", "   STATUS"));
	System.out.println("+-----------------------------------------------------------------------------------+");
	for (Object object : eventAction.getList()) {
	    System.out.println(object);
	}
	System.out.println("+-----------------------------------------------------------------------------------+");
    }

    @Override
    public List<Event> getList() throws Exception {
	return eventAction.getList();
    }

    @Override
    public void add(Event obj) throws Exception {
	eventAction.addNew(obj);
    }

    @Override
    public void saveDataToFile() throws Exception {
	eventAction.saveDataToFile();
    }

}
