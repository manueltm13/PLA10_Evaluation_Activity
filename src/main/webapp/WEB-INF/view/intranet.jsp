<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Trip Memories</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link href="<c:url value="/resources/css/index.css" />" rel="stylesheet">
</head>
<body id="intranet-body">
	<header>
		<img alt="Trip Memories"
			src="<c:url value="/resources/img/avion.png" />">
		<p>Intranet</p>
		<form:form action="${pageContext.request.contextPath}/logout"
			method="POST">
			<button type="submit" class="btn btn-info"
				onclick="if(!confirm('Are you sure?')) return false">Sign
				out</button>
		</form:form>
	</header>
	<div class="cls-background"></div>
	<h1>
		Hola
		<sec:authentication property="principal.username" />
	</h1>
	<div id="container-intranet">
		<div id="columna1-intranet">
			<h2>Lorem Ipsum</h2>
			<textarea rows="100" id="textarea1"><c:import
					url="/resources/txt/loremipsum.txt" /></textarea>
		</div>
		<div id="columna2-intranet">
			<textarea rows="20" id="textarea2"><c:import
					url="/resources/txt/loremipsum.txt" /></textarea>
			<img alt="Trip Memories" id="img1-intranet"
				src="<c:url value="/resources/img/avion.png" />">
			<textarea rows="75" id="textarea3"><c:import
					url="/resources/txt/loremipsum.txt" /></textarea>
		</div>
		<div id="columna3-intranet">
			<img alt="Trip Memories" id="img2-intranet"
				src="<c:url value="/resources/img/avion.png" />">
			<textarea rows="100" id="textarea4"><c:import
					url="/resources/txt/loremipsum.txt" /></textarea>
		</div>
	</div>
	<footer>
		<sec:authorize access="hasAnyRole('ADMIN', 'EDITOR')">
			<a href="${pageContext.request.contextPath}/edicion/"
				class="btn btn-outline-primary">Sección Editorial</a>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ADMIN', 'ADMINISTRATIVO')">
			<a href="${pageContext.request.contextPath}/administracion/"
				class="btn btn-outline-primary">Sección Administrativa</a>
		</sec:authorize>
		<sec:authorize access="hasRole('ADMIN')">
			<a href="${pageContext.request.contextPath}/admin/users/"
				class="btn btn-outline-primary">SISTEMAS</a>
		</sec:authorize>
	</footer>
</body>
</html>