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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
	List<Event> listAfterSorted = eventAction.getList();
	sortListEventByDateAndName(listAfterSorted);
	System.out.println(
		"+---------------------------------------------------------------------------------------------+");
	System.out.println(String.format("|%-10s|%-20s|%-10s|%-25s|%-10s|%-13s|", "    ID", "        NAME", "   DATE",
		"         LOCATION", "  NO.ATD", "   STATUS"));
	System.out.println(
		"+---------------------------------------------------------------------------------------------+");
	for (Object object : listAfterSorted) {
	    System.out.println(object);
	}
	System.out.println(
		"+---------------------------------------------------------------------------------------------+");
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
	sortListEventByAttendanceASC(list);
	return list;

    }

    @Override
    public void update(String id) throws Exception {
	int position = searchById(id);
	if (position == -1) {
	    System.out.println(">>Event does not exist");
	    return;
	}
	Event tmpEvent = (Event) eventAction.getList().get(position);
	while (true) {
	    String newName = Utils.getString("Update a new event name: ");
	    if (newName.isBlank()) {
		break;
	    } else if (!newName.isBlank() && DataValidation.validateName(newName)) {
		tmpEvent.setEventName(newName);
		break;
	    }
	}
	while (true) {
	    String newDate = Utils.getString("Update a new date: ");
	    if (newDate.isBlank()) {
		break;
	    } else if (!newDate.isBlank() && DataValidation.validateDate(newDate)) {
		tmpEvent.setEventDay(newDate);
		break;
	    }
	}

	while (true) {
	    String newLocation = Utils.getString("Update a new location: ");
	    if (newLocation.isBlank()) {
		break;
	    } else if (!newLocation.isBlank() && DataValidation.validateLocation(newLocation)) {
		tmpEvent.setEventLocation(newLocation);
		break;
	    }
	}
	while (true) {
	    int newNumAtd;
	    try {
		newNumAtd = Utils.getIntegerNumber("Update new number of attendees or enter -1 to skip: ");
		if (newNumAtd != -1) {
		    if (DataValidation.validateNoAttendees(newNumAtd)) {
			tmpEvent.setNumberOfAttendees(newNumAtd);
			break;
		    }
		} else {
		    break;
		}
	    } catch (Exception e) {
		System.out.println(">>" + e.getMessage());
	    }

	}

	String newStatus = Utils.inputStatus();
	tmpEvent.setEventStatus(newStatus);
	if (Utils.confirmChoice("Do you want to update " + id + "[YES/NO]: ")) {
	    eventAction.getList().set(position, tmpEvent);
	    saveDataToFile();
	    printList();
	    System.out.println("Updated " + id + " successfully!");
	}

    }

    @Override
    public void delete(String id) throws Exception {
	int position = searchById(id);
	if (position == -1) {
	    System.out.println(">>Event does not exist");
	    return;
	}
	if (Utils.confirmChoice("Do you want to delete " + id + "[YES/NO]: ")) {
	    eventAction.getList().remove(position);
	    saveDataToFile();
	    printList();
	    System.out.println("Deleted " + id + " successfully!");
	}

    }

    private void sortListEventByDateAndName(List<Event> list) throws Exception {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	for (int i = 0; i < list.size() - 1; i++) {
	    int minIndex = i;
	    for (int j = i; j < list.size(); j++) {
		LocalDate dateLeft = LocalDate.parse(list.get(i).getEventDay(), formatter);
		LocalDate dateRight = LocalDate.parse(list.get(j).getEventDay(), formatter);
		if (dateLeft.compareTo(dateRight) > 0) {
		    minIndex = j;
		} else if (dateLeft.compareTo(dateRight) == 0 && list.get(i).getEventName().toLowerCase()
			.compareTo(list.get(j).getEventName().toLowerCase()) > 0) {
		    minIndex = j;
		}
	    }
	    Event tmp = list.get(i);
	    list.set(i, list.get(minIndex));
	    list.set(minIndex, tmp);
	}
    }

    private void sortListEventByAttendanceASC(List<Event> list) {
	for (int i = 0; i < list.size() - 1; i++) {
	    int minIndex = i;
	    for (int j = i + 1; j < list.size(); j++) {
		if (list.get(j).getNumberOfAttendees() < list.get(minIndex).getNumberOfAttendees()) {
		    minIndex = j;
		}
	    }
	    Event tmp = list.get(i);
	    list.set(i, list.get(minIndex));
	    list.set(minIndex, tmp);
	}
    }

}
