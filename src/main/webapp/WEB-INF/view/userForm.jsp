<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Users</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link href="<c:url value="/resources/css/index.css" />" rel="stylesheet">
</head>
<body class="cls-rol">
	<header>
		<img alt="Trip Memories" src="<c:url value="/resources/img/avion.png" />">
		<p>Users</p>
		<form:form action="${pageContext.request.contextPath}/logout"
			method="POST">
			<button type="submit" class="btn btn-info" 
				onclick="if(!confirm('Are you sure?')) return false">Sign out</button>
		</form:form>
	</header>
	<div class="cls-background">
	</div>
	<h1>${ user.username=="" ? "New" : "Edit"} user</h1>
	<form:form action="saveUser" modelAttribute="user" method="post">
		<div class="form-group cls-checkbox">
			<label for="enabled">Enabled:</label>
			<form:checkbox path="enabled" class="form-control" />
			<form:errors path="enabled" cssClass="error text-danger" />
		</div>
		<div class="form-group">
			<label for="username">Name:</label>
			<form:input path="username" class="form-control" />
			<form:errors path="username" cssClass="error text-danger" />
		</div>
		<div class="form-group">
			<label for="password">Password:</label>
			<form:input type="password" path="password" class="form-control" />
			<form:errors path="password" cssClass="error text-danger" />
		</div>
		<div class="form-group">
			<label for="userPwdDate">Password Date:</label>
			<form:input placeholder="dd/mm/yyyy" type="date" path="userPwdDate" class="form-control" />
			<form:errors path="userPwdDate" cssClass="error text-danger" />
		</div>
		<div class="form-group">
			<label for="userEmail">Email:</label>
			<form:input path="userEmail" class="form-control" />
			<form:errors path="userEmail" cssClass="error text-danger" />
		</div>
		<input type="submit" value="Guardar" class="btn btn-success" />
		<a href="${pageContext.request.contextPath}/contacto/lista"
			class="btn btn-outline-primary float-right"> Return to user list</a>
	</form:form>
	<footer>
		<sec:authorize access="hasRole('EDITOR')">
			<a href="${pageContext.request.contextPath}/editor/"
				class="btn btn-outline-primary">Sección Editorial</a>
		</sec:authorize>
		<sec:authorize access="hasRole('ADMINISTRADOR')">
			<a href="${pageContext.request.contextPath}/administrador/"
				class="btn btn-outline-primary">Sección Administrativa</a>
		</sec:authorize>
	</footer>
</body>
</html>