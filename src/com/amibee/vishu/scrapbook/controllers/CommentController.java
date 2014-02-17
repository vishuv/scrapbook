package com.amibee.vishu.scrapbook.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amibee.vishu.scrapbook.model.formModels.CommentForm;
import com.amibee.vishu.scrapbook.service.interfaces.CommentService;
import com.amibee.vishu.scrapbook.service.interfaces.PostService;
import com.amibee.vishu.scrapbook.service.interfaces.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value = "/comments")
public class CommentController {

	UserService userService;
	@Autowired
	PostService postService;
	@Autowired
	CommentService commentService;
	public static final Logger log = Logger.getLogger(Gateway.class);

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String createComment(@Valid CommentForm comment,
			BindingResult result, HttpSession session) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		HashMap<String, Object> map = new HashMap<String, Object>();
		Integer userId = (Integer) session.getAttribute("USERID");
		log.debug(comment);
		if (userId != null)
			map = commentService.createComment(userId, comment.getPostId(),
					comment.getLastCommentId(), comment.getCommentorName(),
					comment.getCommentContent(), result);
		else
			map = commentService.createComment(comment.getPostId(),
					comment.getLastCommentId(), comment.getCommentorName(),
					comment.getCommentContent(), result);
			log.debug("Sending json response "+ gson.toJson(map));
			return gson.toJson(map);
	}
}
