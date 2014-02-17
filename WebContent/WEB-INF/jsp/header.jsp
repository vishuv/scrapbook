<div id="head">
	<a id="banner" href ="${pageContext.servletContext.contextPath}">OBOT</a>
	<ul class="menu">
		<c:choose>
			<c:when test="${USERID == null}">
					<li>
						<a href="${pageContext.servletContext.contextPath}/login">Login</a>
					</li>
				
			</c:when>
			<c:otherwise>
				<li>
					<a href="${pageContext.servletContext.contextPath}/post/create">New post</a>
				</li>
				<li>
					<a href="${pageContext.servletContext.contextPath}/logout">Logout</a>
				</li>
			</c:otherwise>
		</c:choose>
	<ul>
</div>