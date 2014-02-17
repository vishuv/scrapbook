<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/res/css/index.css">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/res/css/login.css">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/res/css/register.css">
</head>
<body>
	<div id="wrap">
		<div id="head">
			<a id="banner" href ="${pageContext.servletContext.contextPath}">OBOT</a>
			<ul class="menu">
				<li>
					<a href="${pageContext.servletContext.contextPath}/login">login</a>
				</li>
			</ul>		
		</div>
		<div id="body">
			<form action="register" method="post">
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
				<div class="name">
					<label class="nameLabel">Name</label>
					<input type="text" id="name" name="name" class="nameText">
					<label class="nameError">${NAME_ERROR}</label>
				</div>
				<div class="submit">
					<button class="submitButton">Register</button>
					<label class="submitError">${ERROR}</label>
				</div>
			</form>
		</div>
	</div>
<script type="text/javascript" src="res/js/jquery.js"></script>
<script type="text/javascript">
</script>	
</body>
</html>