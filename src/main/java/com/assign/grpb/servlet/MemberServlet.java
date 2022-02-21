package com.assign.grpb.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.assign.grpb.dao.MemberDaoGrpB;
import com.assign.grpb.model.MemberGrpB;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet(description = "Servlet for Register/Update/Get Members", urlPatterns = { "/MemberServlet" })
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
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

		MemberDaoGrpB memberDao = new MemberDaoGrpB();
		MemberGrpB model = new MemberGrpB();
		HttpSession session = request.getSession(); // Initialize Session variable

		if (operation != null && request.getAttribute("operation") == null && operation.equals("edit")) { // Check for
																											// Edit
																											// operation
			operation = "edit";
			String id = request.getParameter("Id");
			try {
				model = memberDao.getOneMembers(Integer.parseInt(id)); // Get single member details based on ID from
																		// Member DAO class
				session.setAttribute("single-member-data", model);
			} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (operation != null && operation.equals("delete")) { // Check for Delete operation
			int Id = Integer.parseInt(request.getParameter("Id"));
			try {
				memberDao.deleteOneMember(Id); // Delete single member details from database through Member DAO class
				operation = "add";
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			operation = "add";
		}

		List<MemberGrpB> memberList = null;
		try {
			memberList = memberDao.getAllMembers(); // Get All members details to show in the Member JSP Page
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Set operation type and all member details in session variable for showing in
		// the JSP
		session.setAttribute("member-data", memberList);
		session.setAttribute("operation", operation);
		response.sendRedirect("member.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operation = request.getParameter("operation"); // Get operation type to know UPDATE/INSERT

		// Get form parameters and set values to the member model class
		String memberName = request.getParameter("memb_name");
		String memberAddress = request.getParameter("memb_address");
		String memberType = request.getParameter("memb_type");
		LocalDate memberJoinDate = LocalDate.parse(request.getParameter("memb_join_date"));
		LocalDate memberExpireDate = LocalDate.parse(request.getParameter("memb_expire_date"));

		MemberGrpB model = new MemberGrpB();
		model.setMemberName(memberName);
		model.setMemberAddress(memberAddress);
		model.setMemberType(memberType);
		model.setMemberJoinDate(memberJoinDate);
		model.setMemberExpireDate(memberExpireDate);

		MemberDaoGrpB memberDao = new MemberDaoGrpB();

		if (operation.equals("add")) { // Operation to do when action is to add new member in database
			try {
				memberDao.registerMember(model); // Insert new member details to database by accessing Member DAO class
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (operation.equals("edit")) { // Operation to do when action is to edit existing member in database
			int memberId = Integer.parseInt(request.getParameter("memb_id"));
			model.setMemberID(memberId);
			try {
				memberDao.updateMember(model); // Update one member in database based on the ID
				request.setAttribute("operation", "after-edit");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Go back to doGet method to show all member details and newly added member
		// details in the Member JSP

		doGet(request, response);
	}

}
