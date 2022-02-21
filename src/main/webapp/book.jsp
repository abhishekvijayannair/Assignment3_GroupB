<%@page import="com.assign.grpb.model.PublisherGrpB"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="com.assign.grpb.model.BookGrpB"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Library Management System</title>
<style>
.display-table, .display-td, .display-th {
	border: 1px solid;
}

table {
	width: 60%;
	border-collapse: collapse;
}

label, td, th {
	text-align: left;
}
</style>
</head>
<body>
	<h2>Book Information</h2>

	<%
	String operation = (String) session.getAttribute("operation");
	if (operation == null || operation.equals("add")) {
	%>
	<form action="BookServlet" method="post">
		<input type="hidden" value="add" name="operation" />
		<table>
			<thead>
				<tr>
					<th>Add Book</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><label for="book_title">Title</label></td>
					<td>:</td>
					<td><input type="text" placeholder="Add book title"
						id="book_title" name="book_title" value="" required="required" /></td>
				</tr>
				<tr>
					<td><label for="book_author">Author</label></td>
					<td>:</td>
					<td><input type="text" placeholder="Add book author"
						id="book_author" name="book_author" required="required" /></td>
				</tr>
				<tr>
					<td><label for="book_price">Price</label></td>
					<td>:</td>
					<td><input type="text" placeholder="Price" id="book_price"
						name="book_price" required="required" /></td>
				</tr>
				<tr>
					<td><label for="book_available">Available</label></td>
					<td>:</td>
					<td><select name="book_available" id="book_available"
						required="required">
							<optgroup label="Please select availiblity">
								<option value="1">Yes</option>
								<option value="0">No</option>
							</optgroup>
					</select></td>
				</tr>
				<tr>
					<td><label for="pub_id">Publisher</label></td>
					<td>:</td>
					<td><select name="pub_id" id="pub_id" required="required">
							<optgroup label="Please select the publisher">
								<%
								for (PublisherGrpB publisher : (List<PublisherGrpB>) session.getAttribute("publishers")) {
								%>
								<option value="<%=publisher.getPublisherId()%>"><%=publisher.getPublisherName()%></option>
								<%
								}
								%>
							</optgroup>
					</select></td>
				</tr>
				<tr>
					<td><input type="submit" id="book_add" name="book_add"
						value="Submit" /></td>
				</tr>
			</tbody>
		</table>
	</form>
	<%
	} else if (operation.equals("edit")) {
	BookGrpB book = (BookGrpB) session.getAttribute("single-book-data");
	%>
	<form action="BookServlet" method="post">
		<input type="hidden" name="book_id" value="<%=book.getBookId()%>" />
		<input type="hidden" value="edit" name="operation" />
		<table>
			<thead>
				<tr>
					<th>Edit Book</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><label for="book_title">Title</label></td>
					<td>:</td>
					<td><input type="text" placeholder="Add book title"
						id="book_title" name="book_title" value="<%=book.getBookTitle()%>"
						required="required" /></td>
				</tr>
				<tr>
					<td><label for="book_author">Author</label></td>
					<td>:</td>
					<td><input type="text" placeholder="Add book author"
						id="book_author" name="book_author"
						value="<%=book.getBookAuthor()%>" required="required" /></td>
				</tr>
				<tr>
					<td><label for="book_price">Price</label></td>
					<td>:</td>
					<td><input type="text" placeholder="Price" id="book_price"
						name="book_price" value="<%=book.getBookPrice()%>"
						required="required" /></td>
				</tr>
				<tr>
					<td><label for="book_available">Available</label></td>
					<td>:</td>
					<td><select name="book_available" id="book_available"
						required="required">
							<optgroup label="Please select availiblity">
								<option value="1"
									<%=book.getBookAvailiblity() == true ? "selected" : ""%>>Yes</option>
								<option value="0"
									<%=book.getBookAvailiblity() == false ? "selected" : ""%>>No</option>
							</optgroup>
					</select></td>
				</tr>
				<tr>
					<td><label for="pub_id">Publisher</label></td>
					<td>:</td>
					<td><select name="pub_id" id="pub_id" required="required">
							<optgroup label="Please select the publisher">
								<%
								for (PublisherGrpB publisher : (List<PublisherGrpB>) session.getAttribute("publishers")) {
									System.out.println("OUT " + publisher.getPublisherId());
									if (publisher.getPublisherId() == book.getPublisherId()) {
										System.out.println("IN " + book.getPublisherId());
								%>
								<option value="<%=publisher.getPublisherId()%>"
									selected="selected"><%=publisher.getPublisherName()%></option>
								<%
								} else {
								%>
								<option value="<%=publisher.getPublisherId()%>"><%=publisher.getPublisherName()%></option>
								<%
								}
								}
								%>
							</optgroup>
					</select></td>
				</tr>
				<tr>
					<td><input type="submit" id="memb_add" name="memb_add"
						value="Update" /></td>
				</tr>
			</tbody>
		</table>
	</form>
	<form action="BookServlet" method="get">
		<input type="submit" value="Cancel" />
	</form>
	<%
	}
	%>
	<br>
	<br>
	<h4>Show Books</h4>
	<%
	List<BookGrpB> books = (List<BookGrpB>) session.getAttribute("book-data");
	if (books.size() != 0) {
	%>
	<table class="display-table">
		<thead>
			<tr>
				<th class="display-th">#</th>
				<th class="display-th">Title</th>
				<th class="display-th">Author</th>
				<th class="display-th">Price</th>
				<th class="display-th">Available</th>
				<th class="display-th">Publisher Name</th>
				<th class="display-th"></th>
				<th class="display-th"></th>
			</tr>
		</thead>
		<tbody>
			<%
			int counter = 1;
			for (BookGrpB book : books) {
			%>
			<tr>
				<td class="display-td"><%=counter%></td>
				<td class="display-td"><%=book.getBookTitle()%></td>
				<td class="display-td"><%=book.getBookAuthor()%></td>
				<td class="display-td">$<%=book.getBookPrice()%></td>
				<td class="display-td"><%=book.getBookAvailiblity() == true ? "YES" : "NO"%></td>
				<%
				for (PublisherGrpB publisher : (List<PublisherGrpB>) session.getAttribute("publishers")) {
					if (publisher.getPublisherId() == book.getPublisherId()) {
				%>
				<td class="display-td"><%=publisher.getPublisherName()%></td>
				<%
				}
				}
				%>
				<td class="display-td">
					<form action="BookServlet" method="get">
						<input type="hidden" name="operation" value="edit" /> <input
							type="hidden" name="Id" value="<%=book.getBookId()%>" /> <input
							type="submit" value="Edit" />
					</form>
				</td>
				<td class="display-td">
					<form action="BookServlet" method="get">
						<input type="hidden" name="operation" value="delete" /> <input
							type="hidden" name="Id" value="<%=book.getBookId()%>" /> <input
							type="submit" value="Delete" />
					</form>
				</td>
			</tr>
			<%
			counter++;
			}
			%>
		</tbody>
	</table>

	<%
	} else {
	%>
	<p>No record of books to show, Please add books</p>
	<%
	}
	%>
</body>
</html>