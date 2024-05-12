/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package BussinessLayer.Entity;

import Application.Utilities.Utils;

/**
 *
 * @author duyma
 */
public class Book extends LibraryObject {
    private String author;
    private String publicationYear;
    private String publisher;
    private String ISBN;

    public Book() {
    }

    public Book(String id, String name, String author, String publicationYear, String publisher, String ISBN,
	    boolean status) {
	super(id, name, status);
	this.author = author;
	this.publicationYear = publicationYear;
	this.publisher = publisher;
	this.ISBN = ISBN;
    }

    public String getAuthor() {
	return author;
    }

    public void setAuthor(String author) {
	this.author = Utils.normalizeName(author);
    }

    public String getPublicationYear() {
	return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
	this.publicationYear = publicationYear;
    }

    public String getPublisher() {
	return publisher;
    }

    public void setPublisher(String publisher) {
	this.publisher = Utils.normalizeName(publisher);
    }

    public String getISBN() {
	return ISBN;
    }

    public void setISBN(String ISBN) {
	this.ISBN = ISBN.toUpperCase();
    }

    @Override
    public String toString() {
	return String.format("|%10s|%20s|%20s|%4s|%15s|%10s|%7s|", super.id, super.name, this.author,
		this.publicationYear, this.publisher, this.ISBN, super.status);
    }

}
