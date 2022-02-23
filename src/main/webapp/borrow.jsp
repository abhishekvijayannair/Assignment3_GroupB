<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="com.assign.grpb.model.MemberGrpB"%>
<%@page import="com.assign.grpb.model.BookGrpB"%>
<%@page import="com.assign.grpb.model.BorrowGrpB"%>

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
	<h2>Borrow Information (Book-Member)</h2>

	<%
	String operation = (String) session.getAttribute("operation");
	if (operation == null || operation.equals("add")) {
	%>
	<form action="BorrowServlet" method="post">
		<input type="hidden" value="add" name="operation" />
		<table>
			<thead>
				<tr>
					<th>Create a new record</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><label for="book_id">Select Book Title</label></td>
					<td>:</td>
					<td><select name="book_id" id="book_id" required="required">
							<optgroup label="Please select a book for borrow">
								<%
								for (BookGrpB book : (List<BookGrpB>) session.getAttribute("books")) {
								%>
								<option value="<%=book.getBookId()%>"><%=book.getBookTitle()%></option>
								<%
								}
								%>
							</optgroup>
					</select></td>
				</tr>
				<tr>
					<td><label for="memb_id">Select Member</label></td>
					<td>:</td>
					<td><select name="memb_id" id="memb_id" required="required">
							<optgroup label="Please select the member">
								<%
								for (MemberGrpB member : (List<MemberGrpB>) session.getAttribute("members")) {
								%>
								<option value="<%=member.getMemberID()%>"><%=member.getMemberName()%></option>
								<%
								}
								%>
							</optgroup>
					</select></td>
				</tr>
				<tr>
					<td><label for="issue_date">Issue Date</label></td>
					<td>:</td>
					<td><input type="date" id="issue_date" name="issue_date"
						required="required" /></td>
				</tr>
				<tr>
					<td><label for="return_date">Return Date</label></td>
					<td>:</td>
					<td><input type="date" id="return_date" name="return_date"
						required="required" /></td>
				</tr>
				<tr>
					<td><label for="due_date">Due Date</label></td>
					<td>:</td>
					<td><input type="date" id="due_date" name="due_date"
						required="required" /></td>
				</tr>
				<tr>
					<td><input type="submit" id="borrow_add" name="borrow_add"
						value="Submit" /></td>
				</tr>
			</tbody>
		</table>
	</form>
	<%
	}
	%>
	<br>
	<br>
	<h4>Show Records</h4>
	<%
	List<BorrowGrpB> records = (List<BorrowGrpB>) session.getAttribute("record-data");
	if (records != null && records.size() != 0) {
	%>
	<table class="display-table">
		<thead>
			<tr>
				<th class="display-th">#</th>
				<th class="display-th">BookID</th>
				<th class="display-th">MemberID</th>
				<th class="display-th">Issue Date</th>
				<th class="display-th">Return Date</th>
				<th class="display-th">Due Date</th>
				<th class="display-th"></th>
			</tr>
		</thead>
		<tbody>
			<%
			int counter = 1;
			for (BorrowGrpB record : records) {
			%>
			<tr>
				<td class="display-td"><%=counter%></td>
				<%
				for (BookGrpB book : (List<BookGrpB>) session.getAttribute("books")) {
					if (book.getBookId() == record.getBookId()) {
				%>
				<td class="display-td"><%=book.getBookTitle()%></td>
				<%
				}
				}
				%>

				<%
				for (MemberGrpB member : (List<MemberGrpB>) session.getAttribute("members")) {
					if (member.getMemberID() == record.getMemberId()) {
				%>
				<td class="display-td"><%=member.getMemberName()%></td>
				<%
				}
				}
				%>

				<td class="display-td"><%=record.getIssueDate()%></td>
				<td class="display-td"><%=record.getReturnDate()%></td>
				<td class="display-td"><%=record.getDueDate()%></td>
				<td class="display-td">
					<form action="BorrowServlet" method="get">
						<input type="hidden" name="operation" value="delete" /> <input
							type="hidden" name="BookId" value="<%=record.getBookId()%>" /><input
							type="hidden" name="MemberId" value="<%=record.getMemberId()%>" />
						<input type="submit" value="Delete" />
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
	<p>No record of borrow to show, Please add borrow data</p>
	<%
	}
	%>
</body>
</html>