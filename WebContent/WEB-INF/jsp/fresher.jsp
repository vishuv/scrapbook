<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/res/css/index.css">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/res/css/fresher.css">
</head>
<body>
	<div id="wrap">
		<div id="head">
			<a id="banner" href="${pageContext.servletContext.contextPath}">OBOT</a>
		</div>
		<div id="body">
			<label>Please <a href="${pageContext.servletContext.contextPath}/login">Login </a></label>
			<label>If you are a new user please <a href="${pageContext.servletContext.contextPath}/register">Sign up</a></label>
		</div>
	</div>
<script type="text/javascript" src="res/js/jquery.js"></script>
<script type="text/javascript">
</script>	
</body>
</html>