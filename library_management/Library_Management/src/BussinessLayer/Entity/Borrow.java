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
public class Borrow implements Serializable {
    private String id;
    private String bookId;
    private String userId;
    private String borrowDate;
    private String returnDate;
    private String status;

    public Borrow() {
    }

    public Borrow(String id, String bookId, String userId, String borrowDate, String returnDate, String status) {
	this.id = id;
	this.bookId = bookId;
	this.userId = userId;
	this.borrowDate = borrowDate;
	this.returnDate = returnDate;
	this.status = status;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id.toUpperCase();
    }

    public String getBookId() {
	return bookId;
    }

    public void setBookId(String bookId) {
	this.bookId = bookId.toUpperCase();
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    public String getBorrowDate() {
	return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
	this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
	return returnDate;
    }

    public void setReturnDate(String returnDate) {
	this.returnDate = returnDate;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    @Override
    public String toString() {
	return String.format("|%10s|%10s|%10s|%12s|%12s|%12s|", this.id, this.userId, this.bookId, this.borrowDate,
		this.returnDate, this.status);
    }
}
