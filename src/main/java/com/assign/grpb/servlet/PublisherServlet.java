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

import com.assign.grpb.dao.PublisherDaoGrpB;
import com.assign.grpb.model.PublisherGrpB;

/* Date -21/02/2022
Author - Abhishek
Description - This is the servlet which handles the CRUD operation by using GET and POST method and direct to DAO with Model class
*/
@WebServlet(description = "Servlet for Add/Update/Get/Delete Publishers", urlPatterns = { "/PublisherServlet" })
public class PublisherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublisherServlet() {
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

		PublisherGrpB model = new PublisherGrpB();
		PublisherDaoGrpB publisherDao = new PublisherDaoGrpB();

		HttpSession session = request.getSession(); // Initialize Session variable

		if (operation != null && request.getAttribute("operation") == null && operation.equals("edit")) { // Check for
			// Edit
			// operation
			operation = "edit";
			String id = request.getParameter("Id");
			try {
				model = publisherDao.getOnePublisher(Integer.parseInt(id)); // Get single publisher details based on ID
																			// from
																			// Publisher DAO class
				session.setAttribute("single-publisher-data", model);
			} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (operation != null && operation.equals("delete")) { // Check for Delete operation
			int Id = Integer.parseInt(request.getParameter("Id"));
			try {
				publisherDao.deleteOneMember(Id); // Delete single publisher details from database through Publisher DAO
													// class
				operation = "add";
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			operation = "add";
		}

		List<PublisherGrpB> publisherList = null;
		try {
			publisherList = publisherDao.getAllPublishers(); // Get All publishers details to show in the Publisher JSP
																// Page
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Set operation type and all publisher details in session variable for showing
		// in
		// the JSP
		session.setAttribute("publisher-data", publisherList);
		session.setAttribute("operation", operation);

		response.sendRedirect("publisher.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operation = request.getParameter("operation"); // Get operation type to know UPDATE/INSERT

		// Get form parameters and set values to the publisher model class
		String publisherName = request.getParameter("pub_name");
		String publisherAddress = request.getParameter("pub_address");

		PublisherGrpB model = new PublisherGrpB();
		model.setPublisherName(publisherName);
		model.setPublisherAddress(publisherAddress);

		PublisherDaoGrpB publisherDao = new PublisherDaoGrpB();

		if (operation.equals("add")) { // Operation to do when action is to add new publisher in database
			try {
				publisherDao.addPublisher(model); // Insert new publisher details to database by accessing Publisher DAO
													// class
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (operation.equals("edit")) { // Operation to do when action is to edit existing publisher in database
			int publisherId = Integer.parseInt(request.getParameter("pub_id"));
			model.setPublisherId(publisherId);
			try {
				publisherDao.updatePublisher(model); // Update one publisher in database based on the ID
				request.setAttribute("operation", "after-edit");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Go back to doGet method to show all publisher details and newly added
		// publisher
		// details in the Publisher JSP

		doGet(request, response);
	}

}
