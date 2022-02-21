<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="com.assign.grpb.model.MemberGrpB"%>
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
	width: 50%;
	border-collapse: collapse;
}
</style>
</head>
<body>
	<h2>Member Information</h2>

	<%
	String operation = (String) session.getAttribute("operation");
	if (operation == null || operation.equals("add")) {
	%>
	<form action="MemberServlet" method="post">
		<input type="hidden" value="add" name="operation" />
		<table>
			<thead>
				<tr>
					<th>Add Member</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><label for="memb_name">Name</label></td>
					<td>:</td>
					<td><input type="text" placeholder="Add member name"
						id="memb_name" name="memb_name" value="" required="required" /></td>
				</tr>
				<tr>
					<td><label for="memb_address">Address</label></td>
					<td>:</td>
					<td><textarea id="memb_address" name="memb_address"
							required="required"></textarea></td>
				</tr>
				<tr>
					<td><label for="memb_type">Type</label></td>
					<td>:</td>
					<td><select name="memb_type" id="memb_type"
						required="required">
							<optgroup label="Please select a membership type">
								<option value="student">Student</option>
								<option value="adult">Adult</option>
								<option value="senior">Senior</option>
							</optgroup>
					</select></td>
				</tr>
				<tr>
					<td><label for="memb_join_date">Join Date</label></td>
					<td>:</td>
					<td><input type="date" id="memb_join_date"
						name="memb_join_date" required="required" /></td>
				</tr>
				<tr>
					<td><label for="memb_expire_date">Expire Date</label></td>
					<td>:</td>
					<td><input type="date" id="memb_expire_date"
						name="memb_expire_date" required="required" /></td>
				</tr>
				<tr>
					<td><input type="submit" id="memb_add" name="memb_add"
						value="Submit" /></td>
				</tr>
			</tbody>
		</table>
	</form>
	<%
	} else if (operation.equals("edit")) {
	MemberGrpB member = (MemberGrpB) session.getAttribute("single-member-data");
	%>

	<form action="MemberServlet" method="post">
		<input type="hidden" name="memb_id" value="<%=member.getMemberID()%>" />
		<input type="hidden" value="edit" name="operation" />
		<table>
			<thead>
				<tr>
					<th>Edit Member</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><label for="memb_name">Name</label></td>
					<td>:</td>
					<td><input type="text" placeholder="Add member name"
						id="memb_name" name="memb_name"
						value="<%=member.getMemberName()%>" required="required" /></td>
				</tr>
				<tr>
					<td><label for="memb_address">Address</label></td>
					<td>:</td>
					<td><textarea id="memb_address" name="memb_address"
							required="required"><%=member.getMemberAddress()%></textarea></td>
				</tr>
				<tr>
					<td><label for="memb_type">Type</label></td>
					<td>:</td>
					<td><select name="memb_type" id="memb_type"
						required="required">
							<optgroup label="Please select a membership type">
								<%
								if (member.getMemberType().equals("student")) {
								%>
								<option value="student" selected="selected">Student</option>
								<option value="adult">Adult</option>
								<option value="senior">Senior</option>
								<%
								}
								%>
								<%
								if (member.getMemberType().equals("adult")) {
								%>
								<option value="student">Student</option>
								<option value="adult" selected="selected">Adult</option>
								<option value="senior">Senior</option>
								<%
								}
								%>
								<%
								if (member.getMemberType().equals("senior")) {
								%>
								<option value="student">Student</option>
								<option value="adult">Adult</option>
								<option value="senior" selected="selected">Senior</option>
								<%
								}
								%>
							</optgroup>
					</select></td>
				</tr>
				<tr>
					<td><label for="memb_join_date">Join Date</label></td>
					<td>:</td>
					<td><input type="date" id="memb_join_date"
						name="memb_join_date" required="required"
						value="<%=member.getMemberJoinDate()%>" /></td>
				</tr>
				<tr>
					<td><label for="memb_expire_date">Expire Date</label></td>
					<td>:</td>
					<td><input type="date" id="memb_expire_date"
						name="memb_expire_date" required="required"
						value="<%=member.getMemberExpireDate()%>" /></td>
				</tr>
				<tr>
					<td><input type="submit" id="memb_add" name="memb_add"
						value="Update" /></td>
				</tr>
			</tbody>
		</table>
	</form>
	<form action="MemberServlet" method="get">
		<input type="submit" value="Cancel" />
	</form>

	<%
	}
	%>
	<br>
	<br>
	<h4>Show Members</h4>
	<%
	List<MemberGrpB> members = (List<MemberGrpB>) session.getAttribute("member-data");
	if (members.size() != 0) {
	%>
	<table class="display-table">
		<thead>
			<tr>
				<th class="display-th">#</th>
				<th class="display-th">Name</th>
				<th class="display-th">Address</th>
				<th class="display-th">Membership Type</th>
				<th class="display-th">Date Joined</th>
				<th class="display-th">Expire Date</th>
				<th class="display-th"></th>
				<th class="display-th"></th>
			</tr>
		</thead>
		<tbody>
			<%
			int counter = 1;
			for (MemberGrpB member : members) {
			%>
			<tr>
				<td class="display-td"><%=counter%></td>
				<td class="display-td"><%=member.getMemberName()%></td>
				<td class="display-td"><%=member.getMemberAddress()%></td>
				<td class="display-td"><%=member.getMemberType()%></td>
				<td class="display-td"><%=member.getMemberJoinDate()%></td>
				<td class="display-td"><%=member.getMemberExpireDate()%></td>
				<td class="display-td">
					<form action="MemberServlet" method="get">
						<input type="hidden" name="operation" value="edit" /> <input
							type="hidden" name="Id" value="<%=member.getMemberID()%>" /> <input
							type="submit" value="Edit" />
					</form>
				</td>
				<td class="display-td">
					<form action="MemberServlet" method="get">
						<input type="hidden" name="operation" value="delete" /> <input
							type="hidden" name="Id" value="<%=member.getMemberID()%>" /> <input
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
	<p>No record of members to show, Please add members</p>
	<%
	}
	%>
</body>
</html>