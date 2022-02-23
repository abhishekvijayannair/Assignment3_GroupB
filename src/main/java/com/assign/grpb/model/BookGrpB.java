/**
 * 
 */
package com.assign.grpb.model;

/* 
 * Date -20/02/2022
 * Author - Abhishek
 * Description - This is the model class for Book with Getters and Setters
*/
public class BookGrpB {
	private int bookId;
	private String bookTitle;
	private String bookAuthor;
	private Double bookPrice;
	private Boolean bookAvailiblity;
	private int publisherId;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public Double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(Double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public Boolean getBookAvailiblity() {
		return bookAvailiblity;
	}

	public void setBookAvailiblity(Boolean bookAvailiblity) {
		this.bookAvailiblity = bookAvailiblity;
	}

	public int getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}

}
