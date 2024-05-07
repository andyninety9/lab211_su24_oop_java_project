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
		addNewEvent();
		break;
	    }
	    case 2: {
		checkEventExist();
		break;
	    }
	    case 3: {
		displayEventByLocation();
		break;
	    }
	    case 4: {

		break;
	    }
	    case 5: {
		saveFile();
		break;
	    }
	    case 6: {
		displayListEvent();
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

    public void checkEventExist() {
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

    public void displayEventByLocation() {
	try {
	    do {
		String location = Utils.getString("Enter event location to check: ");
		List<Event> list = ((EventService) service).searchByLocation(location);
		if (list.isEmpty()) {
		    System.out.println("No Event Found");
		} else {
		    System.out.println(
			    "+-----------------------------------------------------------------------------------+");
		    System.out.println(String.format("|%-10s|%-20s|%-10s|%-15s|%-10s|%-13s|", "    ID", "        NAME",
			    "   DATE", "   LOCATION", "  NO.ATD", "   STATUS"));
		    System.out.println(
			    "+-----------------------------------------------------------------------------------+");
		    for (Event event : list) {
			System.out.println(event);
		    }
		    System.out.println(
			    "+-----------------------------------------------------------------------------------+");
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
	    } else {
		System.out.println(
			">>Ensure that the event name is at least five characters and does not contain spaces");
	    }
	}
	String day;
	while (true) {
	    day = Utils.getString("Enter event day[yyyy-mm-dd]: ");
	    if (DataValidation.validateDate(day)) {
		break;
	    } else {
		System.out.println(">>Ensure that the event date is valid and in the correct format (YYYY-MM-DD)");
	    }
	}
	String location;
	while (true) {
	    location = Utils.getString("Enter event location: ");
	    if (DataValidation.validateLocation(location)) {
		break;
	    } else {
		System.out.println(">>Ensure that the location is provided.");
	    }
	}
	int numberAttendees;
	while (true) {
	    numberAttendees = Utils.getIntegerNumber("Enter number of attendees: ");
	    if (DataValidation.validateNoAttendees(numberAttendees)) {
		break;
	    } else {
		System.out.println(">>Ensure that the number of attendees must be greater than 0");
	    }
	}
	String available;
	if (Utils.confirmChoice("Enter 'y' for Available or others for Not Available:  ")) {
	    available = "Available";
	} else {
	    available = "Not Available";
	}
	return new Event(id, name, day, location, numberAttendees, available);
    }

    public void addNewEvent() {
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

    public void saveFile() {
	try {
	    service.saveDataToFile();
	} catch (Exception e) {
	}
    }

    public void displayListEvent() {
	try {
	    service.printList();
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}

    }

    public static String[] mainMenu = { "+--------------------------------------------------+", "       MAIN MENU",
	    "|--------------------------------------------------|", "1. Create a new event", "2. Check event exists",
	    "3. Search event by location", "4. Update event", "5. Save events to file", "6. Print the list of events",
	    "7. Others - Quits", "+--------------------------------------------------+" };
}
