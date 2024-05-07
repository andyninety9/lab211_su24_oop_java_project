/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Application.UI;

import BussinessLayer.Service.EventService;
import BussinessLayer.Service.IService;

/**
 *
 * @author duyma
 */
public class Program {
    public static final String EVENT_FILENAME = "event.dat";

    public static void main(String[] args) {
	try {
	    IService eventService = new EventService(EVENT_FILENAME);
	    Menu.manageEvent(eventService);
	} catch (Exception e) {
	    System.out.println(">>" + e.getMessage());
	}
    }

}
