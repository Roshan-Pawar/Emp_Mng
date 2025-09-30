<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee List</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/employee-list.css">
</head>
<body>

	<h2>All Employee's</h2>
	<a href="index.jsp">Main Menu</a>
	<br />
	<br />

	<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Department</th>
				<th>Salary</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody id="employeeTableBody"></tbody>
	</table>

	<!-- Modal overlay -->
	<div id="modalOverlay" onclick="closeModal()"></div>

	<!-- Edit modal -->
	<div id="editModal">
		<h3>Edit Employee</h3>
		Name: <input type="text" id="editName" /> <br /> 
		Department: <input type="text" id="editDepartment" /> <br /> 
		Salary: <input type="number" id="editSalary" /> <br />
		<button onclick="saveEdit()">Save</button>
		<button onclick="closeModal()">Cancel</button>
	</div>

	<script>
   const contextPath = "<%=request.getContextPath()%>";
	</script>
	<script src="js/employee-list.js"></script>


</body>
</html>
