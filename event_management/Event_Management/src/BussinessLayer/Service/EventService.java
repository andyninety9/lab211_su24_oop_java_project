/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package BussinessLayer.Service;

import Application.Utilities.Utils;
import BussinessLayer.Components.DataValidation;
import BussinessLayer.Entity.Event;
import DataLayer.DaoFactory;
import DataLayer.Event.IEventDao;
import DataLayer.IDaoFactory;
import java.util.ArrayList;
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

    public int searchById(String id) throws Exception {
	if (eventAction.getList().isEmpty()) {
	    return -1;
	}
	for (int i = 0; i < eventAction.getList().size(); i++) {
	    if (eventAction.getList().get(i) instanceof Event) {
		Event event = (Event) eventAction.getList().get(i);
		if (event.getEventId().equalsIgnoreCase(id)) {
		    return i;
		}
	    }

	}

	return -1;
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

    public List<Event> searchByLocation(String location) throws Exception {
	List<Event> list = new ArrayList<>();
	if (eventAction.getList().isEmpty()) {
	    return null;
	}
	for (Object object : eventAction.getList()) {
	    if (object instanceof Event) {
		Event event = (Event) object;
		if (event.getEventLocation().toLowerCase().contains(location.toLowerCase())) {
		    list.add(event);
		}
	    }
	}
	sortListEventByAttendance(list);
	return list;

    }

    public void update(String id) throws Exception {
	int position = searchById(id);
	if (position == -1) {
	    throw new Exception("Event does not exist");
	}
	Event tmpEvent = (Event) eventAction.getList().get(position);
	String newName = Utils.getString("Enter a new event name: ");
	if (!newName.isBlank() && DataValidation.validateName(newName)) {

	}
    }

    private void sortListEventByAttendance(List<Event> list) {
	for (int i = 0; i < list.size(); i++) {
	    for (int j = i; j < list.size(); j++) {
		if (list.get(i).getNumberOfAttendees() > list.get(j).getNumberOfAttendees()) {
		    Event tmp = list.get(i);
		    list.set(i, list.get(j));
		    list.set(j, tmp);
		}
	    }
	}
    }

}
