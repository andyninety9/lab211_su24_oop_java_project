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
public abstract class LibraryObject implements Serializable {
    protected String id;
    protected String name;
    protected boolean status;

    public LibraryObject() {
    }

    public LibraryObject(String id, String name, boolean status) {
	this.id = id;
	this.name = name;
	this.status = status;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public boolean getStatus() {
	return status;
    }

    public void setStatus(boolean status) {
	this.status = status;
    }

    @Override
    public String toString() {
	return "LibraryObject{" + "id=" + id + ", name=" + name + ", status=" + status + '}';
    }

}
