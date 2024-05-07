/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package BussinessLayer.Entity;

import java.io.Serializable;

/**
 *
 * @author duyma
 */
public class Event implements Serializable {
    private String eventId;
    private String eventName;
    private String eventDay;
    private String eventLocation;
    private int numberOfAttendees;
    private String eventStatus;

    public Event() {
    }

    public Event(String eventId, String eventName, String eventDay, String eventLocation, int numberOfAttendees,
	    String eventStatus) {
	this.eventId = eventId;
	this.eventName = eventName;
	this.eventDay = eventDay;
	this.eventLocation = eventLocation;
	this.numberOfAttendees = numberOfAttendees;
	this.eventStatus = eventStatus;
    }

    public String getEventId() {
	return eventId;
    }

    public void setEventId(String eventId) {
	this.eventId = eventId;
    }

    public String getEventName() {
	return eventName;
    }

    public void setEventName(String eventName) {
	this.eventName = eventName;
    }

    public String getEventDay() {
	return eventDay;
    }

    public void setEventDay(String eventDay) {
	this.eventDay = eventDay;
    }

    public String getEventLocation() {
	return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
	this.eventLocation = eventLocation;
    }

    public int getNumberOfAttendees() {
	return numberOfAttendees;
    }

    public void setNumberOfAttendees(int numberOfAttendees) {
	this.numberOfAttendees = numberOfAttendees;
    }

    public String isEventStatus() {
	return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
	this.eventStatus = eventStatus;
    }

    @Override
    public String toString() {
	return String.format("|%10s|%20s|%10s|%15s|%10d|%13s|", this.eventId, this.eventName, this.eventDay,
		this.eventLocation, this.numberOfAttendees, this.eventStatus);
    }

}
