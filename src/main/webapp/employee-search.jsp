<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Employee</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/employee-search.css">
</head>
<body>
	<a href="index.jsp">Main Menu</a>

	<h2>Search Employee by ID</h2>
	<form id="employeeSearch" onsubmit="searchEmployee(event)">
		ID <input placeholder="enter id" type="number" id="id" required /> <br>
		<input type="submit" value="Search" />
	</form>

	<table border="1" style="margin-top: 20px; display: none;"
		id="resultTable">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Department</th>
				<th>Salary</th>
			</tr>
		</thead>
		<tbody id="resultBody"></tbody>
	</table>

	<script>
   		const contextPath = "<%=request.getContextPath()%>";
	</script>
	<script src="js/employee-search.js"></script>
	
</body>
</html>