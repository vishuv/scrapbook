package com.amibee.vishu.scrapbook.service.interfaces;

import java.util.HashMap;

import org.springframework.validation.BindingResult;

public interface PostService {
	//get Home screen
	public HashMap<String,Object> getHome();
	
	//get Posts of a User
	public HashMap<String,Object> getPosts(int userId);

	//get a particular post
	public HashMap<String,Object> getPost(int postId);
	
	//are comments enabled?
	public HashMap<String,Object> isCommentsEnabled(int postId);

	//create a new post
	public HashMap<String, Object> createPost( int userId, String postHeading, String postContent, boolean showComments, BindingResult result);
	
}
