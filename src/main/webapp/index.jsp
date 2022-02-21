<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Library Management Section</title>
</head>
<body>
	<table>	
		<thead>	
			<tr><th>Library Management System</th></tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<form action='MemberServlet' method="get">
						<input type='submit' value='Member Section'>
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<form action='PublisherServlet' method="get">
						<input type='submit' value='Publisher Section'>
					</form>
				</td>
			</tr>
			<tr>	
				<td>
					<form action='BookServlet' method="get">
						<input type='submit' value='Book Section'>
					</form>
				</td>
			</tr>	
		</tbody>
	</table>
</body>
</html>