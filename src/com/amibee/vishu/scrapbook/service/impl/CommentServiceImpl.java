package com.amibee.vishu.scrapbook.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.amibee.vishu.scrapbook.dao.Exceptions.DaoException;
import com.amibee.vishu.scrapbook.dao.interfaces.CommentsDao;
import com.amibee.vishu.scrapbook.dao.interfaces.PostDao;
import com.amibee.vishu.scrapbook.model.Comment;
import com.amibee.vishu.scrapbook.service.interfaces.CommentService;

@Component
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentsDao commentsDao;
	@Autowired
	PostDao postDao;

	private static final Logger log = Logger.getLogger(PostServiceImpl.class);

	@Override
	public HashMap<String, Object> getComments(int postId) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		try {
			List<Comment> comments = commentsDao.getNComments(10, postId, 0);
			map.put("COMMENTS", comments);

		} catch (DaoException e) {
			log.fatal(e.getMessage(), e);
			map.put("EXCEPTION",
					"The system has experienced a glitch and we are looking into it");
		}
		return map;

	}

	@Override
	public HashMap<String, Object> createComment(int postId, int lastCommentId, String name,
			String commentContent, BindingResult result) {
		return createComment(0, postId, lastCommentId, name,
				commentContent, result);
	}

	@Override
	public HashMap<String,Object> createComment(int userId, int postId, int lastCommentId,
			String name, String commentContent, BindingResult result) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (!result.hasErrors()) {
			try {
				Boolean isCommentsShow = postDao.isCommentsShow(postId);
				if (isCommentsShow != null) {
					if (isCommentsShow) {
						commentsDao.createComment(userId, postId,
								commentContent, name, true);
						log.debug("created a comment for post id: "+postId);
						List<Comment> comments = commentsDao.getRecentComments(postId, lastCommentId);
						map.put("COMMENTS",comments);
					} else {
						log.debug("Comments disabled for Post id: " + postId);
						map.put("ERROR","Comments disabled for Post");
					}
				} else {
					log.debug("Invalid Post ID: " + postId);
					map.put("404","Invalid Post ID");
				}
			} catch (DaoException e) {
				log.fatal(e.getMessage(), e);
				map.put("EXCEPTION","The system has experienced a glitch and we are looking into it");
			}
		} else {
			map.putAll(mapErrors(result));
		}
		return map;
	}

	private static HashMap<String, String> mapErrors(BindingResult result) {
		HashMap<String, String> map = new HashMap<String, String>();
		for (FieldError fieldError : result.getFieldErrors()) {
			log.debug(fieldError.getField() + "- "
					+ fieldError.getRejectedValue() + ": "
					+ fieldError.getDefaultMessage());
			map.put(fieldError.getField().toUpperCase() + "_ERROR",
					fieldError.getDefaultMessage());
		}
		return map;
	}

}
