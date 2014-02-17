package com.amibee.vishu.scrapbook.model.formModels;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.sun.istack.internal.NotNull;

public class CommentForm {
	@NotNull @Length(min=4, max=32)
	private String commentorName;
	@NotNull @NotEmpty
	private String commentContent;
	@NotNull
	private int postId;
	@NotNull
	private int lastCommentId;
	
	public String getCommentorName() {
		return commentorName;
	}
	public void setCommentorName(String commentName) {
		this.commentorName = commentName;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getLastCommentId() {
		return lastCommentId;
	}
	public void setLastCommentId(int lastCommentId) {
		this.lastCommentId = lastCommentId;
	}
	@Override
	public String toString() {
		return "CommentForm [commentorName=" + commentorName
				+ ", commentContent=" + commentContent + ", postId=" + postId
				+ ", lastCommentId=" + lastCommentId + "]";
	}
	
}
