/**
 * 
 */
package com.assign.grpb.model;

import java.time.LocalDate;

/* 
 * Date -20/02/2022
 * Author - Abhishek
 * Description - This is the model class for Borrow with Getters and Setters
*/
public class BorrowGrpB {

	private int memberId;
	private int bookId;
	private LocalDate issueDate;
	private LocalDate returnDate;
	private LocalDate dueDate;

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

}
