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
public class Borrow implements Serializable, ILibraryObject {
    private String id;
    private String bookId;
    private String userId;
    private String borrowDate;
    private String returnDate;

    public Borrow() {
    }

    public Borrow(String id, String bookId, String userId, String borrowDate, String returnDate) {
	this.id = id;
	this.bookId = bookId;
	this.userId = userId;
	this.borrowDate = borrowDate;
	this.returnDate = returnDate;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getBookId() {
	return bookId;
    }

    public void setBookId(String bookId) {
	this.bookId = bookId;
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

    @Override
    public String toString() {
	return "Borrow{" + "id=" + id + ", bookId=" + bookId + ", userId=" + userId + ", borrowDate=" + borrowDate
		+ ", returnDate=" + returnDate + '}';
    }

    @Override
    public String getID() throws Exception {
	return userId;
    }

}
