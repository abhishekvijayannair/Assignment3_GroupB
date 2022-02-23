/**
 * 
 */
package com.assign.grpb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.assign.grpb.model.BorrowGrpB;

/* Date -21/02/2022
Author - Jith
Description - This DAO implementation contains code for Get all borrowed information and to create and delete borrow information
*/
public class BorrowDaoGrpB {

	// Method to get All borrow records from Database
	public List<BorrowGrpB> getAllRecords() {

		final String GET_ALL_BORROW_SQL = "SELECT * FROM borrow_book_member_map;";
		List<BorrowGrpB> recordList = new ArrayList<>();
		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(GET_ALL_BORROW_SQL);
			while (rs.next()) {
				BorrowGrpB record = new BorrowGrpB();
				record.setBookId(rs.getInt("book_id"));
				record.setMemberId(rs.getInt("member_id"));
				record.setIssueDate(rs.getDate("issue_date").toLocalDate());
				record.setReturnDate(rs.getDate("return_date").toLocalDate());
				record.setDueDate(rs.getDate("due_date").toLocalDate());

				recordList.add(record);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return recordList;
	}

	// Method to Add a new records to the Database
	public void registerBorrow(BorrowGrpB record) {
		final String ADD_BORROW_SQL = "INSERT INTO borrow_book_member_map (book_id, member_id, issue_date, return_date, due_date) VALUES (?, ?, ?, ?, ?);";
		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
			PreparedStatement pstmt = connection.prepareStatement(ADD_BORROW_SQL);
			pstmt.setInt(1, record.getBookId());
			pstmt.setInt(2, record.getMemberId());
			pstmt.setDate(3, java.sql.Date.valueOf(record.getIssueDate()));
			pstmt.setDate(4, java.sql.Date.valueOf(record.getReturnDate()));
			pstmt.setDate(5, java.sql.Date.valueOf(record.getDueDate()));
			pstmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// Method to Delete a Single Record Details from Database based on Member ID and
	// Book ID
	public void deleteOneBorrowRecord(int bookId, int memberId) {
		final String DELETE_BORROW_SQL = "DELETE FROM borrow_book_member_map WHERE member_id = ? AND book_id = ?;";
		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
			PreparedStatement pstmt = connection.prepareStatement(DELETE_BORROW_SQL);
			pstmt.setInt(1, memberId);
			pstmt.setInt(2, bookId);
			pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
