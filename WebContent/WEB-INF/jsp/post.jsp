<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/res/css/index.css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/res/css/post.css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/res/css/comments.css">
</head>
<body>
	<div id="wrap">
		<%@ include file="header.jsp" %>
		<div id="body">
			<div class="post">
				<div class="postHeading">
					<a class="postHeadingLink" href="${POST.getPostId()}">${POST.getPostHeading()}</a>
				</div>
				<div class="postContent">${POST.getPostContent()}</div>
				<div class="postTime">${POST.getCreationTime()}</div>
				<ul class="postMenu">
							<c:set var="postId" value="${POST.getPostId()+0}"/>
							<li>post by <a class="author" href="${pageContext.servletContext.contextPath}/users/${POST.getUserId()}">${AUTHORS[postId]}</a>
							</li>
							<c:if test="${USERID!=null}">
								<li> <a href="${pageContext.servletContext.contextPath}/post/edit/${POST.getPostId()}" class="edit">edit</a></li>
								<li><a class="delete" href="${pageContext.servletContext.contextPath}/post/delete/${POST.getPostId()}" >delete</a></li>
							</c:if>
				</ul>
				<input type="hidden" name="postId" value="${POST.getPostId()}">
			</div>
			<c:choose>
				<c:when test="${POST.isCommentsEnabled()}">
						<div class="newComment">
							<div class="newCommentorNameDiv">
								<label class="newCommentorNameLabel">Name</label>
								<input type="text" class="newCommentorName" />
								<label class="newCommentorNameError"></label>
							</div>
							<div class="newCommentContentDiv">
								 <label class="newCommentContentLabel">Comment</label>
								<textarea class="newCommentContent"></textarea>
								<label class="newCommentContentError"></label>
							</div>
							<div class="newCommentSubmitDiv">
								<button class="newCommentSubmitButton">Submit</button>
								<label class="newCommentSubmitError"></label>
							</div>
						</div>
					<div class="comments">
						<c:forEach var="comment" items="${COMMENTS}">
							<div class="comment">
								<input type="hidden" name="commentId"
									value="${comment.getCommentId()}" />
								<div class="commentorName">${comment.getCommentorName()}</div>
								<div class="commentContent">
									${comment.getCommentContent()}</div>
								<div class="commentTime">${comment.getTime()}</div>
							</div>

						</c:forEach>
					</div>
				</c:when>
				<c:otherwise>
					<div>
						<label>Comments are disabled for this post</label>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<script type="text/javascript"
		src="${pageContext.servletContext.contextPath}/res/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/res/js/dateFormat.js"></script>
	<script type="text/javascript">
	$(document)
    .ready(
        function () {
        	formatDates();
        	$('.newCommentSubmitButton').on('click',
                    function () {
				$('.newCommentorNameError').html('');
				$('.newCommentContentError').html(''	);
   				$('.newCommentorSubmitError').html('');
    			
                        $.post('${pageContext.servletContext.contextPath}/comments/create', {
                                    postId: $('.post input:hidden').val(),
                                    commentorName: $('.newCommentorName').val(),
                                    commentContent: $('.newCommentContent').val(),
                                    lastCommentId: $('.comment input:hidden').first().val(),
                                })
                            .done(
                                function (data) {
                                    data = $.parseJSON(data);
                                    console.log(data);
                                    if (data != null) {
                            			if(data['COMMENTORNAME_ERROR'])
                            				$('.newCommentorNameError').html(data['COMMENTORNAME_ERROR']);
                            			
                            			if(data['COMMENTCONTENT_ERROR'])
                            				$('.newCommentContentError').html(data['COMMENTCONTENT_ERROR']);
                            			
                            			if(data['ERROR'])
                            				$('.newCommentorSubmitError').html(data['ERROR']);
                            			
                            			if(data['COMMENTS']){
	                                    	$.each(data['COMMENTS'],function(index,comment){
	                            				$('.newCommentorName').val('');
		                                        $('.newCommentContent').val('');
		                                        
		                                        var commentDiv = $('<div></div>', {
		                                                class: 'comment'
		                                            });
		                                        var commentId=$('<input type="hidden" name="commentId" />',{
		                                        		value:comment.CommentId
		                                        });
		        									
		                                        var commentorNameDiv = $('<div></div>', {
		                                                class: 'commentorName'
		                                            }).html(comment.commentorName);
		                                        var commentContentDiv = $('<div></div>', {
		                                                class: 'commentContent'
		                                            }).html(comment.commentContent);
		                                        var commentTimeDiv = $('<div></div>', {
		                                                class: 'commentTime'
		                                            }).html(comment.time);
		                                        commentDiv.append(
		                                        		commentId,
		                                                commentorNameDiv,
		                                                commentContentDiv,
		                                                commentTimeDiv);
		                                        $('.comments').prepend(commentDiv);
	                                    	});
                            			}
                                    }

                                })
                            .fail(function () {
                                    console.log('comment creation failed');
                            });

                    });
        });
	</script>
</body>
</html>