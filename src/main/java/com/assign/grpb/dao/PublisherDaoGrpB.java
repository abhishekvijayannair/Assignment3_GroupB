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
import com.assign.grpb.model.PublisherGrpB;

/**
 * @author abhis
 *
 */
public class PublisherDaoGrpB {

	// Method to Add a new Publisher to the Database
	public void addPublisher(PublisherGrpB publisher) throws ClassNotFoundException, SQLException {
		final String ADD_PUBLISHER_SQL = "INSERT INTO publishers (name, address) VALUES (?, ?);";
		Connection connection = null;

		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
			PreparedStatement pstmt = connection.prepareStatement(ADD_PUBLISHER_SQL);
			pstmt.setString(1, publisher.getPublisherName());
			pstmt.setString(2, publisher.getPublisherAddress());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	// Method to get All publishers from Database
	public List<PublisherGrpB> getAllPublishers() throws ClassNotFoundException, SQLException {

		final String GET_ALL_PUBLISHERS_SQL = "SELECT * FROM publishers;";
		List<PublisherGrpB> publisherList = new ArrayList<>();
		Connection connection = null;

		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(GET_ALL_PUBLISHERS_SQL);
			while (rs.next()) {
				PublisherGrpB publisher = new PublisherGrpB();
				publisher.setPublisherId(rs.getInt("pub_id"));
				publisher.setPublisherName(rs.getString("name"));
				publisher.setPublisherAddress(rs.getString("address"));
				publisherList.add(publisher);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return publisherList;
	}

	// Method to get a Single Publisher details from Database based on Publisher ID
	public PublisherGrpB getOnePublisher(int Id) throws ClassNotFoundException, SQLException {

		final String GET_ONE_PUBLISHER_SQL = "SELECT * FROM publishers WHERE pub_id = ?;";
		Connection connection = null;
		PublisherGrpB publisher = new PublisherGrpB();

		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
			PreparedStatement pstmt = connection.prepareStatement(GET_ONE_PUBLISHER_SQL);
			pstmt.setInt(1, Id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				publisher.setPublisherId(Id);
				publisher.setPublisherName(rs.getString("name"));
				publisher.setPublisherAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return publisher;
	}

	// Method to Update a Single Publisher details in Database based on Publisher ID
	public void updatePublisher(PublisherGrpB publisher) throws ClassNotFoundException, SQLException {
		final String UPDATE_PUBLISHER_SQL = "UPDATE publishers SET name = ?, address = ? WHERE pub_id = ?;";
		Connection connection = null;

		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
			PreparedStatement pstmt = connection.prepareStatement(UPDATE_PUBLISHER_SQL);
			pstmt.setString(1, publisher.getPublisherName());
			pstmt.setString(2, publisher.getPublisherAddress());
			pstmt.setInt(3, publisher.getPublisherId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	// Method to Delete a Single Publisher Details from Database based on Publisher ID
	public void deleteOneMember(int Id) throws ClassNotFoundException, SQLException {
		final String DELETE_PUBLISHER_SQL = "DELETE FROM publishers WHERE pub_id = ?;";
		Connection connection = null;

		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
			PreparedStatement pstmt = connection.prepareStatement(DELETE_PUBLISHER_SQL);
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
