<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/res/css/index.css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/res/css/post.css">
</head>
<body>
	<div id="wrap">
		<%@ include file="header.jsp" %>
		<div id="body">
			<c:forEach var="post" items="${POSTS}">
				<div class="post">
					<div class="postHeading">
						<a href="post/${post.getPostId()}" class="postHeadingLink" >${post.getPostHeading()}</a>
					</div>
					<div class="postTime">${post.getCreationTime()}</div>
					<div class="postContent">${post.getPostContent()}</div>
					<div class="commentsEnabled">
						<a href="post/${post.getPostId()}">more...</a>
					</div>
					<ul class="postMenu">
							<c:set var="postId" value="${post.getPostId()+0}"/>
							<li>post by <a class="author" href="${pageContext.servletContext.contextPath}/users/${post.getUserId()}">${AUTHORS[postId]}</a>
							</li>
							<c:if test="${USERID!=null}">
								<li> <a href="${pageContext.servletContext.contextPath}/post/edit/${post.getPostId()}" class="edit">edit</a></li>
								<li><a class="delete" href="${pageContext.servletContext.contextPath}/post/delete/${post.getPostId()}" >delete</a></li>
							</c:if>
					</ul>
					<input type="hidden" name="postId" value="${post.getPostId() }">
				</div>
			</c:forEach>
		</div>
	</div>
	<script type="text/javascript" src="res/js/jquery.js"></script>
	<script type="text/javascript" src="res/js/dateFormat.js"></script>
	<script type="text/javascript">
		formatDates();
	</script>
</body>
</html>