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
public class User extends LibraryObject implements Serializable {
    private String dateOfBirth;
    private String phoneNumber;
    private String email;

    public User() {
    }

    public User(String id, String name, String dateOfBirth, String phoneNumber, String email, boolean status) {
	super(id, name, status);
	this.dateOfBirth = dateOfBirth;
	this.phoneNumber = phoneNumber;
	this.email = email;
    }

    public String getDateOfBirth() {
	return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
	return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    @Override
    public String toString() {
	return "User{" + "dateOfBirth=" + dateOfBirth + ", phoneNumber=" + phoneNumber + ", email=" + email + '}';
    }

    @Override
    public String getID() throws Exception {
	return id;
    }

}
