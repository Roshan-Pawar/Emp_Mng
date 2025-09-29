<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Management</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/index.css">
</head>
<body>

	<h1>Employee Management System</h1>

	<button onclick="location.href='employee-form.jsp'">Insert a Record</button>
	<button onclick="location.href='employee-list.jsp'">Get All Records</button>
	<button onclick="location.href='employee-search.jsp'">Search Employee</button>

</body>
</html>