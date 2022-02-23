package com.assign.grpb.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.assign.grpb.dao.BookDaoGrpB;
import com.assign.grpb.dao.BorrowDaoGrpB;
import com.assign.grpb.dao.MemberDaoGrpB;
import com.assign.grpb.model.BookGrpB;
import com.assign.grpb.model.BorrowGrpB;
import com.assign.grpb.model.MemberGrpB;

/* Date -21/02/2022
Author - Sanjay, Kevin
Description - This is the servlet which handles the Create, Display and Delete operation by using GET and POST method and direct to DAO with Model class
*/
@WebServlet("/BorrowServlet")
public class BorrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BorrowServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Get the type of operation to execute
		String operation = request.getParameter("operation");

		BorrowDaoGrpB borrowDao = new BorrowDaoGrpB();

		HttpSession session = request.getSession(); // Initialize Session variable

		if (operation == null) {
			operation = "add";
		} else if (operation.equals("delete")) { // Check for Delete operation
			int bookId = Integer.parseInt(request.getParameter("BookId"));
			int memberId = Integer.parseInt(request.getParameter("MemberId"));
			borrowDao.deleteOneBorrowRecord(bookId, memberId);
			operation = "add";
		} else {
			operation = "add";
		}

		List<BorrowGrpB> borrowList = borrowDao.getAllRecords(); // Get All records to show in the borrow JSP Page

		List<BookGrpB> books = null;
		List<MemberGrpB> members = null;
		try {
			books = new BookDaoGrpB().getAllBooks();
			members = new MemberDaoGrpB().getAllMembers();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Set operation type and all records in session variable for showing in
		// the JSP
		session.setAttribute("books", books);
		session.setAttribute("members", members);
		session.setAttribute("record-data", borrowList);
		session.setAttribute("operation", operation);
		response.sendRedirect("borrow.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Get form parameters and set values to the records model class
		int bookId = Integer.parseInt(request.getParameter("book_id"));
		int memberId = Integer.parseInt(request.getParameter("memb_id"));
		LocalDate issueDate = LocalDate.parse(request.getParameter("issue_date"));
		LocalDate returnDate = LocalDate.parse(request.getParameter("return_date"));
		LocalDate dueDate = LocalDate.parse(request.getParameter("due_date"));

		BorrowGrpB model = new BorrowGrpB();
		model.setBookId(bookId);
		model.setMemberId(memberId);
		model.setIssueDate(issueDate);
		model.setReturnDate(returnDate);
		model.setDueDate(dueDate);

		BorrowDaoGrpB borrowDao = new BorrowDaoGrpB();
		borrowDao.registerBorrow(model);

		doGet(request, response);
	}

}
