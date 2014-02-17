<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/res/css/index.css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/res/css/createPost.css">

</head>
<body>
	<div id="wrap">
		<%@include file="header.jsp" %>
		<div id="body">
			<form action="${pageContext.servletContext.contextPath}/post/create" method="post">
				<div class="newPost">
					<div class="newPostHeadingDiv">
						<label class="newPostHeadingLabel">Name</label>
						<input type="text" class="newPostHeading" name="postHeading"/>
						<label class="newPostHeadingError">${POSTHEADING_ERROR}</label>
					</div>
					<div class="newPostContentDiv">
						 <label class="newPostContentLabel">Post</label>
						<textarea class="newPostContent" name="postContent"></textarea>
						<label class="newPostContentError">${POSTCONTENT_ERROR}</label>
					</div>
					<div class="enableComments">
						<input type="checkbox" name="enableComments" checked="checked">
						<label>enable comments</label>
					</div>
					<div class="newPostSubmitDiv">
						<button class="newPostSubmitButton">Submit</button>
						<label class="newPostSubmitError"></label>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/res/js/jquery.js"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>