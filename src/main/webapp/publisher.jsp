<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Library Management System</title>
</head>
<body>
	<h2>Publisher Information</h2>
	<form action="" method="post">
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
</body>
</html>