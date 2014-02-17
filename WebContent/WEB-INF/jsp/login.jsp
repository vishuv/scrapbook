<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/res/css/index.css">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/res/css/login.css">
</head>
<body>
	<div id="wrap">
		<div id="head">
			<a id="banner" href="${pageContext.servletContext.contextPath}">OBOT</a>
		</div>
		<div id="body">
			<form action="login" method="POST">
				<div class="email">
					<label class="emailLabel">Email</label>
					<input type="text" name="email" class="emailText"/>
					<label class="emailError">${EMAIL_ERROR}</label>
				</div>
				<div class="password">
					<label class="passwordLabel">Password</label>
					<input type="password" name="password" class="passwordText"/>
					<label class="passwordError">${PASSWORD_ERROR}</label>
				</div>
				<div class="submit">
					<button class="submitButton">Login</button>
					<label class="submitError">${LOGIN_ERROR}</label>
				</div>
				<c:if test="${returnTo!=null}"><input type="hidden" name="returnTo" value="${returnTo}"/></c:if>
			</form>
				<div class="newUser">
					<label>New user? <a href="register">Sign up here</a></label>
				</div>
		</div>
	</div>
<script type="text/javascript" src="res/js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		if($('input:hidden').length==0)
				$('form').append($('<input type="hidden" name="returnTo" />').val(getURLParameter("returnTo")));
		
	});
	function getURLParameter(name) {
	    return decodeURI(
	        (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]
	    );
	}
</script>	
</body>
</html>