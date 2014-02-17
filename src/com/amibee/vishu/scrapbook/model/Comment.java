package com.amibee.vishu.scrapbook.model;

import java.sql.Timestamp;


public class Comment {
	private int commentId;
	private int userId;
	private int postId;
	private String commentContent;
	private Timestamp time;
	private String commentorName;
	private boolean commentStatus;
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public boolean isCommentStatus() {
		return commentStatus;
	}
	public void setCommentStatus(boolean commentStatus) {
		this.commentStatus = commentStatus;
	}
	public String getCommentorName() {
		return commentorName;
	}
	public void setCommentorName(String commentorName) {
		this.commentorName = commentorName;
	}
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", userId=" + userId
				+ ", postId=" + postId + ", commentContent=" + commentContent
				+ ", time=" + time + ", CommentorName=" + commentorName
				+ ", commentStatus=" + commentStatus + "]";
	}
	
	
}
