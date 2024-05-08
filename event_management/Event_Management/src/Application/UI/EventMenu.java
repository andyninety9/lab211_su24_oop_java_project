/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Application.UI;

import Application.Utilities.Utils;
import BussinessLayer.Components.DataValidation;
import BussinessLayer.Entity.Event;
import BussinessLayer.Service.EventService;
import BussinessLayer.Service.IService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duyma
 */
public class EventMenu {
    IService<Event> service;

    public EventMenu() {
    }

    public EventMenu(IService<Event> service) {
	this.service = service;
    }

    public void processMenuForEmployee() {
	boolean isRun = true;
	do {
	    Menu.display(mainMenu);
	    switch (Menu.getUserChoice()) {
	    case 1: {
		handleAddNewEvent();
		break;
	    }
	    case 2: {
		handleCheckEventExist();
		break;
	    }
	    case 3: {
		handleSortEventByLocation();
		break;
	    }
	    case 4: {
		processMenuUpdate();
		break;
	    }
	    case 5: {
		handleSaveToFile();
		break;
	    }
	    case 6: {
		handleDisplayListEvent();
		break;
	    }
	    default: {
		isRun = false;
		System.out.println("Thank you!");
		break;
	    }
	    }
	} while (isRun);
    }

    public void handleCheckEventExist() {
	try {
	    do {
		String id_check = Utils.getString("Enter event id to check: ");
		if (((EventService) service).searchById(id_check) > -1) {
		    System.out.println(">>Exist Event");
		} else {
		    System.out.println(">>No Event Found!");
		}
	    } while (Utils.confirmChoice("Do you want to continue checking[YES/NO]: "));
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
    }

    public void handleSortEventByLocation() {
	try {
	    do {
		String location = Utils.getString("Enter event location to check: ");
		List<Event> list = ((EventService) service).searchByLocation(location);
		if (list.isEmpty()) {
		    System.out.println(">>No Event Found");
		} else {
		    System.out.println(
			    "+---------------------------------------------------------------------------------------------+");
		    System.out.println(String.format("|%-10s|%-20s|%-10s|%-25s|%-10s|%-13s|", "    ID", "        NAME",
			    "   DATE", "   LOCATION", "  NO.ATD", "   STATUS"));
		    System.out.println(
			    "+---------------------------------------------------------------------------------------------+");
		    for (Event event : list) {
			System.out.println(event);
		    }
		    System.out.println(
			    "+---------------------------------------------------------------------------------------------+");
		}
	    } while (Utils.confirmChoice("Do you want to continue[YES/NO]: "));
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
    }

    public Event inputEventInformation() throws Exception {
	String id = Utils.generateID("E");
	String name;
	while (true) {
	    name = Utils.getString("Enter event name: ");
	    if (DataValidation.validateName(name)) {
		break;
	    }
	}
	String day;
	while (true) {
	    day = Utils.getString("Enter event day[yyyy-mm-dd]: ");
	    if (DataValidation.validateDate(day)) {
		break;
	    }
	}
	String location;
	while (true) {
	    location = Utils.getString("Enter event location: ");
	    if (DataValidation.validateLocation(location)) {
		break;
	    }
	}
	int numberAttendees;
	while (true) {
	    numberAttendees = Utils.getIntegerNumber("Enter number of attendees: ");
	    if (DataValidation.validateNoAttendees(numberAttendees)) {
		break;
	    }
	}
	String available = Utils.inputStatus();

	return new Event(id, name, day, location, numberAttendees, available);
    }

    public void handleAddNewEvent() {
	try {
	    do {
		Event newEvent = inputEventInformation();
		service.add(newEvent);
		System.out.println(">>Added event successfully!");
	    } while (Utils.confirmChoice("Do you want to continue[YES/NO]: "));
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
    }

    public void handleSaveToFile() {
	try {
	    service.saveDataToFile();
	} catch (Exception e) {
	}
    }

    public void handleUpdateEvent() {
	try {
	    handleDisplayListEvent();
	    do {
		String idUpdate = Utils.getString("Enter event id to update: ");
		service.update(idUpdate);
		handleDisplayListEvent();
	    } while (Utils.confirmChoice("Do you want to continue update[YES/NO]: "));
	} catch (Exception ex) {
	    Logger.getLogger(EventMenu.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public void handleDisplayListEvent() {
	try {
	    service.printList();
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}

    }

    public void handleDeleteExistEvent() {
	try {
	    do {
		String idDelete = Utils.getString("Enter event ID to delete: ");
		service.delete(idDelete);
		handleDisplayListEvent();
	    } while (Utils.confirmChoice("Do you want to continue delete[YES/NO]: "));
	} catch (Exception e) {
	}
    }

    public void processMenuUpdate() {
	boolean isRun = true;
	do {
	    Menu.display(updateMenu);
	    switch (Menu.getUserChoice()) {
	    case 1: {
		handleUpdateEvent();
		break;
	    }
	    case 2: {
		handleDeleteExistEvent();
		break;
	    }
	    default: {
		isRun = false;
		break;
	    }
	    }
	} while (isRun);
    }

    public static String[] updateMenu = { "+--------------------------------------------------+", "       MAIN MENU",
	    "|--------------------------------------------------|", "1. Update exist event", "2. Delete exist event",
	    "3. Others - Quits", "+--------------------------------------------------+" };

    public static String[] mainMenu = { "+--------------------------------------------------+", "       MAIN MENU",
	    "|--------------------------------------------------|", "1. Create a new event", "2. Check event exists",
	    "3. Search event by location", "4. Update event", "5. Save events to file", "6. Print the list of events",
	    "7. Others - Quits", "+--------------------------------------------------+" };
}
