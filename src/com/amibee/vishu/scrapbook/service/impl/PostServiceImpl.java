package com.amibee.vishu.scrapbook.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.amibee.vishu.scrapbook.dao.Exceptions.DaoException;
import com.amibee.vishu.scrapbook.dao.interfaces.PostDao;
import com.amibee.vishu.scrapbook.model.Post;
import com.amibee.vishu.scrapbook.service.interfaces.CommentService;
import com.amibee.vishu.scrapbook.service.interfaces.PostService;
import com.amibee.vishu.scrapbook.service.interfaces.UserService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostDao postDao;
	@Autowired
	UserService userService;

	@Value("20")
	private static int NO_OF_POSTS;

	@Autowired
	CommentService commentservice;
	
	private static final Logger log = Logger.getLogger(PostServiceImpl.class);

	public  HashMap<String,Object> getHome() {
		HashMap<String,Object> map = new HashMap<String,Object>();
		HashMap<Long,String> authors = new HashMap<Long,String>();
		try {
			List<Post> posts = postDao.getNPosts(10, 0);
			map.put("POSTS", posts);
			for(Post post : posts){
				HashMap<String,String> nameMap=userService.getUserName(post.getUserId());
				if(nameMap.get("EXCEPTION")!=null){
					throw new DaoException(nameMap.get("EXCEPTION"));
				}else{
					authors.put((long) post.getPostId(), nameMap.get("NAME"));
				}
			}
			map.put("AUTHORS", authors);
		} catch (DaoException e) {
			log.fatal(e.getMessage(), e);
			map.put("EXCEPTION",
					"The system has experienced a glitch and we are looking into it");
	
		}
		return map;	
	}

	@Override
	public HashMap<String, Object> getPosts(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, Object> getPost(int postId) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		try {
			Post post = postDao.getPost(postId);
			if(post!=null){
				map.put("POST", post);
				if(post.isCommentsEnabled()){
					HashMap<String, Object> comments = (HashMap<String,Object>) commentservice.getComments(post.getPostId());
					map.putAll(comments);
				}
				HashMap<Long,String> authors = new HashMap<Long,String>();
				HashMap<String,String> nameMap=userService.getUserName(post.getUserId());
				if(nameMap.get("EXCEPTION")!=null){
					throw new DaoException(nameMap.get("EXCEPTION"));
				}else{
					authors.put((long) post.getPostId(), nameMap.get("NAME"));
				}
				map.put("AUTHORS", authors);
			}else{
				map.put("INVALID_POSTID", "");
			}
			return map;
		} catch (DaoException e) {
			log.fatal(e.getMessage(), e);
			map.put("EXCEPTION",
					"The system has experienced a glitch and we are looking into it");
	
		}
		return map;		
	}

	@Override
	public HashMap<String,Object> isCommentsEnabled(int postId) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		try {
			Boolean isCommentsShown=postDao.isCommentsShow(postId);
			if(isCommentsShown!=null)
				map.put("IS_COMMENTES_ENABLED",isCommentsShown);
			else
				map.put("ERROR","ERROR");
		} catch (DaoException e) {
			log.fatal(e.getMessage(), e);
			map.put("EXCEPTION",
					"The system has experienced a glitch and we are looking into it");
		}
		return map;
	}

	@Override
	public HashMap<String, Object> createPost(int userId, String postHeading,
			String postContent, boolean showComments, BindingResult result) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (!result.hasErrors()) {
			try {
				postDao.createPost(userId, postHeading, postContent, showComments);
				map.put("SUCCESS", "");
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
