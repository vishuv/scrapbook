package com.amibee.vishu.scrapbook.dao.interfaces;

import java.util.List;

import com.amibee.vishu.scrapbook.dao.Exceptions.DaoException;
import com.amibee.vishu.scrapbook.model.Comment;

public interface CommentsDao {
	
	//create a comment
	public void createComment(int userId, int postId, String commentContent, String commentorName, boolean commentStatus) throws DaoException;
	
	//get N comments for a post from a given commentId
	public List<Comment> getNComments(int n, int postId, int fromCommentId) throws DaoException;
	
	//delete a comment for a post
	public void deleteComments(int[] commentIds) throws DaoException;
	
	//set comment status ie., approved or approval pending etc
	public void setStatus(int[] commentIds, byte status) throws DaoException;
	
	//get comments later than given postId
	public List<Comment> getRecentComments(int postId, int commentId) throws DaoException;
	
}
