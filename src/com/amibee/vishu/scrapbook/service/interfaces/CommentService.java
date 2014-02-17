package com.amibee.vishu.scrapbook.service.interfaces;

import java.util.HashMap;

import org.springframework.validation.BindingResult;

import com.amibee.vishu.scrapbook.dao.Exceptions.DaoException;

public interface CommentService {
	
	public HashMap<String, Object> getComments(int postId) throws DaoException ;

	public HashMap<String, Object> createComment(int postId, int lastCommentId, String name, String commentContent, BindingResult result);
	
	public HashMap<String, Object> createComment(int userId, int postId, int lastCommentId, String name, String commentContent, BindingResult result);
	
}
