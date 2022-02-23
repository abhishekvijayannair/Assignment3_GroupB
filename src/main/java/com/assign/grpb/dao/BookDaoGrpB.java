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

import com.assign.grpb.model.BookGrpB;

/* Date - 20/02/2022
Author - Sanjay, Kevin
Description - This class contains the DAO implementation for Book with CRUD Operations
*/

public class BookDaoGrpB {

	// Method to get All members from Database
	public List<BookGrpB> getAllBooks() throws ClassNotFoundException, SQLException {

		final String GET_ALL_BOOKS_SQL = "SELECT * FROM books;";
		List<BookGrpB> bookList = new ArrayList<>();
		Connection connection = null;

		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(GET_ALL_BOOKS_SQL);
			while (rs.next()) {
				BookGrpB book = new BookGrpB();
				book.setBookId(rs.getInt("book_id"));
				book.setBookTitle(rs.getString("title"));
				book.setBookAuthor(rs.getString("author"));
				book.setBookPrice(rs.getDouble("price"));
				book.setBookAvailiblity(rs.getBoolean("available"));
				book.setPublisherId(rs.getInt("pub_id"));
				bookList.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return bookList;
	}

	// Method to Add a new Book to the Database
	public void issueBook(BookGrpB book) throws ClassNotFoundException, SQLException {
		final String ADD_BOOKS_SQL = "INSERT INTO books (title, author, price, available, pub_id) VALUES (?, ?, ?, ?, ?);";
		Connection connection = null;

		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
			PreparedStatement pstmt = connection.prepareStatement(ADD_BOOKS_SQL);
			pstmt.setString(1, book.getBookTitle());
			pstmt.setString(2, book.getBookAuthor());
			pstmt.setDouble(3, book.getBookPrice());
			pstmt.setBoolean(4, book.getBookAvailiblity());
			pstmt.setInt(5, book.getPublisherId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	// Method to get a Single Book details from Database based on Book ID
	public BookGrpB getOneBook(int Id) throws ClassNotFoundException, SQLException {

		final String GET_ONE_BOOK_SQL = "SELECT * FROM books WHERE book_id = ?;";
		Connection connection = null;
		BookGrpB book = new BookGrpB();

		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
			PreparedStatement pstmt = connection.prepareStatement(GET_ONE_BOOK_SQL);
			pstmt.setInt(1, Id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				book.setBookId(rs.getInt("book_id"));
				book.setBookTitle(rs.getString("title"));
				book.setBookAuthor(rs.getString("author"));
				book.setBookPrice(rs.getDouble("price"));
				book.setBookAvailiblity(rs.getBoolean("available"));
				book.setPublisherId(rs.getInt("pub_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return book;
	}

	// Method to Update a Single Book details in Database based on Book ID
	public void updateBook(BookGrpB book) throws ClassNotFoundException, SQLException {
		final String UPDATE_BOOK_SQL = "UPDATE books SET title = ?, author = ?, price = ?, available = ?, pub_id = ? WHERE book_id = ?;";
		Connection connection = null;

		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
			PreparedStatement pstmt = connection.prepareStatement(UPDATE_BOOK_SQL);
			pstmt.setString(1, book.getBookTitle());
			pstmt.setString(2, book.getBookAuthor());
			pstmt.setDouble(3, book.getBookPrice());
			pstmt.setBoolean(4, book.getBookAvailiblity());
			pstmt.setInt(5, book.getPublisherId());
			pstmt.setInt(6, book.getBookId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	// Method to Delete a Single Book Details from Database based on Book ID
	public void deleteOneBook(int Id) throws ClassNotFoundException, SQLException {
		final String DELETE_BOOK_SQL = "DELETE FROM books WHERE book_id = ?;";
		final String DELETE_CHILD_RECORD_SQL = "DELETE FROM borrow_book_member_map WHERE book_id = ?;";
		Connection connection = null;

		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
			
			PreparedStatement conStmt = connection.prepareStatement(DELETE_CHILD_RECORD_SQL);
			conStmt.setInt(1, Id);
			conStmt.executeUpdate();
			
			PreparedStatement pstmt = connection.prepareStatement(DELETE_BOOK_SQL);
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
