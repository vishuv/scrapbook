package com.amibee.vishu.scrapbook.model.formModels;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class PostForm {
	@NotNull @Length(min=4, max=32)
	private String postHeading;
	@NotNull @NotEmpty
	private String postContent;
	@NotNull 
	private boolean enableComments;
	
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
	public boolean isEnableComments() {
		return enableComments;
	}
	public void setEnableComments(boolean enableComments) {
		this.enableComments = enableComments;
	}
	@Override
	public String toString() {
		return "PostForm [postHeading=" + postHeading + ", postContent="
				+ postContent + ", enableComments="
				+ enableComments + "]";
	}
	
	
}
