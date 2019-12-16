<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
				onclick="if(!confirm('¿Está seguro?')) return false">Sign out</button>
		</form:form>
	</header>
	<div class="cls-background">
	</div>
	<h1>Hello <sec:authentication property="principal.username" /></h1>
	<div id="id-div-table">
		<a href="addUser" class="btn btn-primary mb-1">Add user</a>
		<table class="table table-striped">
			<tr>
				<th>Enabled
				<th>Name
				<th>Pasword Date
				<th>Email
				<th>
				<th>
			</tr>
			<c:forEach var="user" items="${users}">
				<c:url var="linkEditar" value="/admin/updateUser">
					<c:param name="idUser" value="${user.username }" />
				</c:url>
				<c:url var="linkBorrar" value="/admin/deleteUser">
					<c:param name="idUser" value="${user.username }" />
				</c:url>
				<tr>
					<td><input onclick="return false;" type="checkbox" ${user.enabled ? "Checked" : "" }/></td>
					<td>${user.username }</td>
					<td><fmt:formatDate value="${user.userPwdDate}" type="date" pattern="dd/MM/yyyy"/></td>
					<td>${user.userEmail}</td>
					<td><a href="${linkEditar }" class="btn btn-outline-success btn-sm">
						Edit</a></td>
					<td><a href="${linkBorrar }" onclick="if(!confirm('¿Está seguro?')) return false"
						class="btn btn-outline-danger btn-sm">Erase</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
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