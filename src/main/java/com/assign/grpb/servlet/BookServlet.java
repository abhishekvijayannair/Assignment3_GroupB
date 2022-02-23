package com.assign.grpb.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.assign.grpb.dao.BookDaoGrpB;
import com.assign.grpb.dao.PublisherDaoGrpB;
import com.assign.grpb.model.BookGrpB;
import com.assign.grpb.model.PublisherGrpB;

/* Date -21/02/2022
Author - Abhishek, Suchitra
Description - This is the servlet which handles the CRUD operation by using GET and POST method and direct to DAO with Model class
*/

@WebServlet(description = "Servlet to handle request for Issue/Update/Delete Book", urlPatterns = { "/BookServlet" })
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookServlet() {
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

		BookDaoGrpB bookDao = new BookDaoGrpB();
		PublisherDaoGrpB publisherDao = new PublisherDaoGrpB();

		BookGrpB model = new BookGrpB();
		HttpSession session = request.getSession(); // Initialize Session variable

		List<PublisherGrpB> publisherList = null;
		try {
			publisherList = publisherDao.getAllPublishers(); // Get All publishers details to show in the Book JSP Page
																// Drop down
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (operation != null && request.getAttribute("operation") == null && operation.equals("edit")) { // Check for
																											// Edit
																											// operation
			operation = "edit";
			String id = request.getParameter("Id");
			try {
				model = bookDao.getOneBook(Integer.parseInt(id)); // Get single member details based on ID from
																	// Member DAO class
				session.setAttribute("single-book-data", model);
			} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (operation != null && operation.equals("delete")) { // Check for Delete operation
			int Id = Integer.parseInt(request.getParameter("Id"));
			try {
				bookDao.deleteOneBook(Id); // Delete single book details from database through book DAO class
				operation = "add";
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			operation = "add";
		}

		List<BookGrpB> bookList = null;
		try {
			bookList = bookDao.getAllBooks(); // Get All books details to show in the Book JSP Page
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Set operation type and all books details in session variable for showing in
		// the JSP
		session.setAttribute("book-data", bookList);
		session.setAttribute("publishers", publisherList);
		session.setAttribute("operation", operation);
		response.sendRedirect("book.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operation = request.getParameter("operation"); // Get operation type to know UPDATE/INSERT

		// Get form parameters and set values to the book model class
		String bookTitle = request.getParameter("book_title");
		String bookAuthor = request.getParameter("book_author");
		String bookPrice = request.getParameter("book_price");
		String bookAvailable = request.getParameter("book_available");
		String publisherId = request.getParameter("pub_id");

		BookGrpB model = new BookGrpB();
		model.setBookTitle(bookTitle);
		model.setBookAuthor(bookAuthor);
		model.setBookPrice(Double.parseDouble(bookPrice));
		model.setBookAvailiblity(bookAvailable.trim().equals("1") ? true : false);
		model.setPublisherId(Integer.parseInt(publisherId));

		BookDaoGrpB bookDao = new BookDaoGrpB();

		if (operation.equals("add")) { // Operation to do when action is to add new book in database
			try {
				bookDao.issueBook(model); // Insert new member details to database by accessing Book DAO class
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (operation.equals("edit")) { // Operation to do when action is to edit existing book in database
			int bookId = Integer.parseInt(request.getParameter("book_id"));
			model.setBookId(bookId);
			try {
				bookDao.updateBook(model); // Update one book in database based on the ID
				request.setAttribute("operation", "after-edit");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Go back to doGet method to show all book details and newly added book
		// details in the Book JSP
		doGet(request, response);
	}

}
