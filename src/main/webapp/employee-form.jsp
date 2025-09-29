<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Employee</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/employee-form.css">
</head>
<body>

	<form id="employeeForm" onsubmit="saveEmployee(event)">
		<h2>Add Employee</h2>
		ID <input type="number" id="id" required /> <br> 
		Name <input type="text" id="name" required /><br /> 
		Department <input type="text" id="department" required /><br /> 
		Salary <input type="number" id="salary" required /><br /> 
		<input type="submit" value="Save" /> 
		<a href="index.jsp">Main Menu</a>
	</form>

	<script>
   		const contextPath = "<%=request.getContextPath()%>";
	</script>
	<script src="js/employee-form.js"></script>
</body>
</html>