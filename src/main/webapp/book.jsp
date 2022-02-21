<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	<form action="BookServlet" method="post">
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
</body>
</html>