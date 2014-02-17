package com.amibee.vishu.scrapbook.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amibee.vishu.scrapbook.model.formModels.PostForm;
import com.amibee.vishu.scrapbook.service.interfaces.CommentService;
import com.amibee.vishu.scrapbook.service.interfaces.PostService;
import com.amibee.vishu.scrapbook.service.interfaces.UserService;

@Controller
@RequestMapping(value = "/post")
public class PostController {
	UserService userService;
	@Autowired
	PostService postService;
	@Autowired
	CommentService commentService;
	public static final Logger log = Logger.getLogger(PostController.class);

	@RequestMapping(value = "/{postId}")
	public String post(@PathVariable int postId, Model model) {
		HashMap<String, Object> map = postService.getPost(postId);
		if (map.get("INVALID_POSTID") != null) {
			log.debug("redirecting to  view: 404");
			return "redirect:/404";
		} else if (map.get("EXCEPTION") != null) {
			log.debug("Sending view: EXCEPTION with map " + map);
			return "exception";
		}
		model.addAllAttributes(map);
		log.debug("Sending view: post with map " + map);
		return "post";
	}
	
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String create(HttpSession session){

			return "createPost";		
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@Valid PostForm postForm, BindingResult result, Model model, HttpSession session){
		Integer userId = (Integer) session.getAttribute("USERID");
		HashMap<String, Object> map = new HashMap<String, Object>();
		log.debug(postForm);
		if(userId!=null){
			map = postService.createPost(userId, postForm.getPostHeading(),postForm.getPostContent(), postForm.isEnableComments(), result);
			model.addAllAttributes(map);
			if (map.get("EXCEPTION") != null) {
				log.debug("Sending view: EXCEPTION with map " + map);
				return "exception";
			}else{
				log.debug("Sending view CREATEPOST with map "+map);
				return "createPost";
			}
		}else{
			log.debug("User not logged in");
			log.debug("redirecting user to LOGIN");
			return "redirect:/login?returnTo=CREATE_POST";
		}
	}

}
