package com.amibee.vishu.scrapbook.dao.interfaces;

import java.util.List;

import com.amibee.vishu.scrapbook.dao.Exceptions.DaoException;
import com.amibee.vishu.scrapbook.model.Post;

public interface PostDao {
	
	//create a post
	public void createPost(int userId, String postHeading, String postContent, boolean showComments) throws DaoException ;
	
	//delete a post
	public void deletePost(int postId) throws DaoException ;
	
	//update a post
	public void updatePost(int postId, String postHeading, String postContent ) throws DaoException ;
	
	//get N posts from a give postId
	public List<Post> getNPosts(int n, int fromPostId) throws DaoException ;
	
	//get N posts of a user from a given postId
	public List<Post> getNPostsOfUser(int n, int fromPostId, int userId) throws DaoException ;
		
	//show or hide comments
	public void showComments(int postId, boolean show) throws DaoException;
	
	//get comment Visibility status
	public Boolean isCommentsShow(int postId) throws DaoException;

	//get a particular post
	public Post getPost(int postId) throws DaoException;
		
}
