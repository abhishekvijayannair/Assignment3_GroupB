<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="com.assign.grpb.model.PublisherGrpB"%>
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
	<h2>Publisher Information</h2>

	<%
	String operation = (String) session.getAttribute("operation");
	if (operation == null || operation.equals("add")) {
	%>
	<form action="PublisherServlet" method="post">
		<input type="hidden" name="operation" value="add" />
		<table>
			<thead>
				<tr>
					<th>Add Publisher</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><label for="pub_name">Name</label></td>
					<td>:</td>
					<td><input type="text" placeholder="Add publisher name"
						id="pub_name" name="pub_name" value="" required="required" /></td>
				</tr>
				<tr>
					<td><label for="pub_address">Address</label></td>
					<td>:</td>
					<td><textarea id="pub_address" name="pub_address"
							required="required"></textarea></td>
				</tr>
				<tr>
					<td><input type="submit" id="pub_add" name="pub_add"
						value="Submit" /></td>
				</tr>
			</tbody>
		</table>
	</form>
	<%
	} else if (operation.equals("edit")) {
	PublisherGrpB publisher = (PublisherGrpB) session.getAttribute("single-publisher-data");
	%>
	<form action="PublisherServlet" method="post">
		<input type="hidden" name="pub_id"
			value="<%=publisher.getPublisherId()%>" /> <input type="hidden"
			value="edit" name="operation" />
		<table>
			<thead>
				<tr>
					<th>Edit Publisher</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><label for="pub_name">Name</label></td>
					<td>:</td>
					<td><input type="text" placeholder="Add publisher name"
						id="pub_name" name="pub_name"
						value="<%=publisher.getPublisherName()%>" required="required" /></td>
				</tr>
				<tr>
					<td><label for="pub_address">Address</label></td>
					<td>:</td>
					<td><textarea id="pub_address" name="pub_address"
							required="required"><%=publisher.getPublisherAddress()%></textarea></td>
				</tr>
				<tr>
					<td><input type="submit" id="pub_add" name="pub_add"
						value="Update" /></td>
				</tr>
			</tbody>
		</table>
	</form>
	<form action="PublisherServlet" method="get">
		<input type="submit" value="Cancel" />
	</form>

	<%
	}
	%>

	<br>
	<br>
	<h4>Show Publishers</h4>
	<%
	List<PublisherGrpB> publishers = (List<PublisherGrpB>) session.getAttribute("publisher-data");
	if (publishers.size() != 0) {
	%>
	<table class="display-table">
		<thead>
			<tr>
				<th class="display-th">#</th>
				<th class="display-th">Name</th>
				<th class="display-th">Address</th>
				<th class="display-th"></th>
				<th class="display-th"></th>
			</tr>
		</thead>
		<tbody>
			<%
			int counter = 1;
			for (PublisherGrpB publisher : publishers) {
			%>
			<tr>
				<td class="display-td"><%=counter%></td>
				<td class="display-td"><%=publisher.getPublisherName()%></td>
				<td class="display-td"><%=publisher.getPublisherAddress()%></td>
				<td class="display-td">
					<form action="PublisherServlet" method="get">
						<input type="hidden" name="operation" value="edit" /> <input
							type="hidden" name="Id" value="<%=publisher.getPublisherId()%>" />
						<input type="submit" value="Edit" />
					</form>
				</td>
				<td class="display-td">
					<form action="PublisherServlet" method="get">
						<input type="hidden" name="operation" value="delete" /> <input
							type="hidden" name="Id" value="<%=publisher.getPublisherId()%>" />
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
	<p>No record of publishers to show, Please add publishers</p>
	<%
	}
	%>
</body>
</html>