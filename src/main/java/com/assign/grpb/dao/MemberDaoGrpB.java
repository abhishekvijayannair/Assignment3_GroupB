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

import com.assign.grpb.model.MemberGrpB;

/* Date -21/02/2022
Author - Abhishek
Description - This code contains the methods to do CRUD operation on Members class
*/

public class MemberDaoGrpB {

	// Method to Add a new Member to the Database
	public void registerMember(MemberGrpB member) throws ClassNotFoundException, SQLException {
		final String ADD_MEMBERS_SQL = "INSERT INTO members (name, address, memb_type, memb_date, expiry_date) VALUES (?, ?, ?, ?, ?);";
		Connection connection = null;

		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
			PreparedStatement pstmt = connection.prepareStatement(ADD_MEMBERS_SQL);
			pstmt.setString(1, member.getMemberName());
			pstmt.setString(2, member.getMemberAddress());
			pstmt.setString(3, member.getMemberType());
			pstmt.setDate(4, java.sql.Date.valueOf(member.getMemberJoinDate()));
			pstmt.setDate(5, java.sql.Date.valueOf(member.getMemberExpireDate()));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	// Method to Update a Single Member details in Database based on Member ID
	public void updateMember(MemberGrpB member) throws ClassNotFoundException, SQLException {
		final String UPDATE_MEMBER_SQL = "UPDATE members SET name = ?, address = ?, memb_type = ?, memb_date = ?, expiry_date = ? WHERE memb_id = ?;";
		Connection connection = null;

		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
			PreparedStatement pstmt = connection.prepareStatement(UPDATE_MEMBER_SQL);
			pstmt.setString(1, member.getMemberName());
			pstmt.setString(2, member.getMemberAddress());
			pstmt.setString(3, member.getMemberType());
			pstmt.setDate(4, java.sql.Date.valueOf(member.getMemberJoinDate()));
			pstmt.setDate(5, java.sql.Date.valueOf(member.getMemberExpireDate()));
			pstmt.setInt(6, member.getMemberID());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	// Method to get All members from Database
	public List<MemberGrpB> getAllMembers() throws ClassNotFoundException, SQLException {

		final String GET_ALL_MEMBERS_SQL = "SELECT * FROM members;";
		List<MemberGrpB> memberList = new ArrayList<>();
		Connection connection = null;

		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(GET_ALL_MEMBERS_SQL);
			while (rs.next()) {
				MemberGrpB member = new MemberGrpB();
				member.setMemberID(rs.getInt("memb_id"));
				member.setMemberName(rs.getString("name"));
				member.setMemberAddress(rs.getString("address"));
				member.setMemberType(rs.getString("memb_type"));
				member.setMemberJoinDate(rs.getDate("memb_date").toLocalDate());
				member.setMemberExpireDate(rs.getDate("expiry_date").toLocalDate());
				memberList.add(member);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return memberList;
	}

	// Method to get a Single Member details from Database based on Member ID
	public MemberGrpB getOneMembers(int Id) throws ClassNotFoundException, SQLException {

		final String GET_ONE_MEMBERS_SQL = "SELECT * FROM members WHERE memb_id = ?;";
		Connection connection = null;
		MemberGrpB member = new MemberGrpB();

		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
			PreparedStatement pstmt = connection.prepareStatement(GET_ONE_MEMBERS_SQL);
			pstmt.setInt(1, Id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				member.setMemberID(rs.getInt("memb_id"));
				member.setMemberName(rs.getString("name"));
				member.setMemberAddress(rs.getString("address"));
				member.setMemberType(rs.getString("memb_type"));
				member.setMemberJoinDate(rs.getDate("memb_date").toLocalDate());
				member.setMemberExpireDate(rs.getDate("expiry_date").toLocalDate());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return member;
	}

	// Method to Delete a Single Member Details from Database based on Member ID
	public void deleteOneMember(int Id) throws ClassNotFoundException, SQLException {
		final String DELETE_MEMBER_SQL = "DELETE FROM members WHERE memb_id = ?;";
		final String DELETE_CHILD_RECORD_SQL = "DELETE FROM borrow_book_member_map WHERE member_id = ?;";
		Connection connection = null;

		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
			
			PreparedStatement conStmt = connection.prepareStatement(DELETE_CHILD_RECORD_SQL);
			conStmt.setInt(1, Id);
			conStmt.executeUpdate();
			
			PreparedStatement pstmt = connection.prepareStatement(DELETE_MEMBER_SQL);
			pstmt.setInt(1, Id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
}
