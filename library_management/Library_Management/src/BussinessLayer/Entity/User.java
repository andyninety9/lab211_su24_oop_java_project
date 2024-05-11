/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package BussinessLayer.Entity;

/**
 *
 * @author duyma
 */
public class User extends LibraryObject {
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
	return String.format("|%10s|%20s|%12s|%12s|%20s|%8s|", super.getId(), super.getName(), this.dateOfBirth,
		this.phoneNumber, this.getEmail(), super.getStatus());
    }

}
