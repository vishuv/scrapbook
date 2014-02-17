package com.amibee.vishu.scrapbook.model;

import java.sql.Timestamp;

public class Post {
	private int postId;
	private int userId;
	private Timestamp creationTime;
	private String postHeading;
	private String postContent;
	private boolean commentsEnabled;
	
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Timestamp getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}
	public String getPostHeading() {
		return postHeading;
	}
	public void setPostHeading(String postHeading) {
		this.postHeading = postHeading;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public boolean isCommentsEnabled() {
		return commentsEnabled;
	}
	public void setCommentsEnabled(boolean commentsEnabled) {
		this.commentsEnabled = commentsEnabled;
	}
	@Override
	public String toString() {
		return "Post: postId=" + postId + ", userId=" + userId
				+ ", creationTime=" + creationTime + ", postHeading="
				+ postHeading + ", postContent=" + postContent;
	}
	
}
