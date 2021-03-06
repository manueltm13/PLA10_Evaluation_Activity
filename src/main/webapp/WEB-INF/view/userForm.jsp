<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
<body id="user-body">
	<header>
		<img alt="Trip Memories" src="<c:url value="/resources/img/avion.png" />">
		<p>Users</p>
		<form:form action="${pageContext.request.contextPath}/logout"
			method="POST">
			<button type="submit" class="btn btn-info" 
				onclick="if(!confirm('Sign out. Are you sure?')) return false">Sign out</button>
		</form:form>
	</header>
	
	<div class="cls-background">
	</div>
	
	<!-- h1>${action=="Add" ? "New" : "Edit"} user</h1 -->
	<h1 class="cls-action">${action} user</h1>
	<p id="action" class="cls-hiden">${action}</p>
	
	<form:form id="user-form" action="addUserProcess" modelAttribute="user" method="post">
		
		<form:input id="rols" type="hidden" path="rols" class="form-control" value="${rols}"/>
		
		<div class="form-group" id="enabled-group">
			<label for="enabled">Enabled:</label>
			<form:checkbox path="enabled" class="form-control" />
			<form:errors path="enabled" cssClass="error text-danger" />
		</div>
		
		<div class="form-group" id="username-group">
			<label for="username">Name:</label>
			<form:input type="text" path="username" class="form-control" autocomplete="off"/>
			<form:errors path="username" cssClass="error text-danger" />
		</div>
		<form:input path="username" id="hiden-username-input" class="cls-hiden" type="text" disabled="true" />
		
		<div class="form-group" id="password-group">
			<label for="password">Password:</label>
			<form:input type="password" path="password" class="form-control" autocomplete="off" />
			<form:errors path="password" cssClass="error text-danger" />
		</div>
		
		<div class="form-group">
			<label for="userPwdDate">Password Date:</label>
			<!-- fmt:formatDate pattern='yyyy-MM-dd' value='${user.userPwdDate}' /-->
			<!-- form:input placeholder="dd/mm/yyyy" type="date" path="userPwdDate" class="form-control" /-->
			<fmt:formatDate value="${user.userPwdDate}" var="dateString" pattern="yyyy-MM-dd" />
			<form:input placeholder="dd/mm/yyyy" type="date" path="userPwdDate" class="form-control" 
				value="${dateString}"/>
			<form:errors path="userPwdDate" cssClass="error text-danger" />
		</div>
		
		<div class="form-group">
			<label for="userEmail">Email:</label>
			<form:input type="text" path="userEmail" class="form-control" autocomplete="off" />
			<form:errors path="userEmail" cssClass="error text-danger" />
		</div>
		
		<div class="form-group cls-auths">
		</div>
		
		<!-- Links -->
		
		<c:url var="linkEliminar" value="/admin/deleteUserProcess">
			<c:param name="username" value="${user.username }" />
		</c:url>
		<a id="delete-link" href="${linkEliminar}" class="cls-hiden"></a>

		<footer class="cls-buttons">
			<input type="submit" value="Save" class="btn btn-success" onclick="if(!confirm('Save data. Are you sure?')) return false"/>
			<input type="button" value="Add Rol" class="btn btn-success" onclick="addAuth()"/>
			<a href="${pageContext.request.contextPath}/admin/users"
			class="btn btn-outline-primary float-right" onclick="if(!confirm('Return to user list. Are you sure?')) return false"> Return to user list</a>
		</footer>
	</form:form>
	<script>
		if($("input#rols").val().length > 0){
			rols = $("input#rols").val().split("|");
			for(i = 0; i < rols.length; i++){
				console.log("ROLS", i, rols[i]);
				rol = $("<div></div>").addClass("form-group").addClass("cls-old");
				if(rols[i].slice(0, 1) === "+")
					rol.append($("<input type='checkbox' Checked/>"));
				else
					rol.append($("<input type='checkbox'/>"));
				rol.append($("<input readonly type='text' value='" + rols[i].slice(1) + "'/>"));
				rol.appendTo($(".form-group.cls-auths"));
			}
		}

		if((action = $("p#action").text()) === "Delete"){
			$("div#password-group").addClass("cls-hiden");
			$("div.form-group>input").prop("disabled", true);
			$("footer>input[type=button]").prop("disabled", true);
			$("footer>input[type=submit]").val("Delete").removeClass("btn-success")
				.addClass("btn-danger").attr("type", "button").removeAttr("onclick")
				.off("click").on("click", () => {
					$('a#delete-link')[0].click();
					return false;
				});
		}else if(action === "Update"){
			$("div#username-group>input").prop("disabled", true);
			$("input#hiden-username-input").prop("disabled", false);
			$("div#password-group>input").val("");
			$("form#user-form").attr("action", "updateUserProcess");
			$("footer>input[type=submit]").val("Update").removeAttr("onclick")
				.off("click").on("click", () => {
					if(!confirm('Update data. Are you sure?')){
						return false;
				}
			});
		}
		console.log("ACTION", action);
		function addAuth(){
			cnt = 0;
			var str = "";
			var exists = true;
			while(exists){
				exists = false;
				str = "000000000" + ++cnt;
			    str = "NEWROLE" + str.slice(str.length - 3);
				$("div.form-group.cls-auths div.form-group").each(function(idx, ele){
					if($(ele).find("input[type=text]").val() === str)
						exists = true;
				})
			}
			rol = $("<div></div>").addClass("form-group").addClass("cls-new");
			rol.append($("<input type='checkbox' Checked/>"));
			rol.append($("<input style='background-color: white;' type='text' value='" + str + "'/>"));
			rol.appendTo($(".form-group.cls-auths"));
		}
		$('form').submit(function() {
			out = "";
			$("div.form-group.cls-auths div.form-group").each(function(idx, ele){
				if($(ele).find("input[type=checkbox]").prop("checked")){
					if(out === "")
						out += "+" + $(ele).find("input[type=text]").val();
					else
						out += "|+" + $(ele).find("input[type=text]").val();
				}else{
					if(out === "")
						out += "-" + $(ele).find("input[type=text]").val();
					else
						out += "|-" + $(ele).find("input[type=text]").val();
				}
			})
			$("input#rols").val(out)
		    return true; // return false to cancel form action
		});
	</script>
</body>
</html>