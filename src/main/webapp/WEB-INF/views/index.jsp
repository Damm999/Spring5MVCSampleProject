<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome To Task Manager</title>
</head>
<body>

	<%@ include file="Header.jsp"%>

	<h1>Manager Task here..</h1>
	
	<a href="/TaskMng/assign" class="btn btn-lg btn-success">ASSIGN TASK</a>
	<a href="/TaskMng/task" class="btn btn-lg btn-success">TASK VIEW</a>

	<%@ include file="Footer.jsp"%>
</body>
</html>