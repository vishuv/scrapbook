package com.amibee.vishu.scrapbook.controllers;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amibee.vishu.scrapbook.model.Post;
import com.amibee.vishu.scrapbook.model.formModels.LoginForm;
import com.amibee.vishu.scrapbook.model.formModels.RegistrationForm;
import com.amibee.vishu.scrapbook.service.interfaces.CommentService;
import com.amibee.vishu.scrapbook.service.interfaces.PostService;
import com.amibee.vishu.scrapbook.service.interfaces.UserService;

@Controller
public class Gateway {
	@Autowired
	UserService userService;
	@Autowired
	PostService postService;
	@Autowired
	CommentService commentService;
	public static final Logger log = Logger.getLogger(Gateway.class);

	@RequestMapping(value = "/")
	public String index(HttpServletRequest request, Model model) {
		Integer userId = null;
		HttpSession session = request.getSession(false);
		if (session != null)
			userId = (Integer) session.getAttribute("USERID");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = postService.getHome();
		@SuppressWarnings("unchecked")
		List<Post> posts = (List<Post>) map.get("POSTS");
		if (posts != null) {
			if (posts.size() == 0) {
				if (userId != null) {
					log.debug("sending user create post view");
					return "redirect:/post/create";
				}
				log.debug("Sending view FRESHER");
				return "fresher";
			}
		} else if (map.get("EXCEPTION") != null) {
			log.debug("Sending view EXCEPTION with map " + map);
			return "exception";
		}
		if (userId != null) {
			log.debug("User logged in");
			map.put("USER", userId);
		}
		model.addAllAttributes(map);
		log.debug("Sending view HOME with map" + map);
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String Login() {

		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String Login(@Valid LoginForm loginForm,
			BindingResult bindingResult, Model model, HttpServletRequest request) {
		HashMap<String, String> map = userService.authenticate(loginForm,
				bindingResult);
		log.debug(loginForm);
		String returnTo = loginForm.getReturnTo();

		if (map.get("USERID") != null) {
			if (returnTo != null) {
				returnTo = findPath(returnTo);
			}
			if (request.getSession(false) != null) {
				request.getSession(false).invalidate();
			}
			HttpSession session = request.getSession();
			log.debug("redirecting to " + returnTo.toUpperCase() + " with map "
					+ map);
			session.setAttribute("USERID", Integer.parseInt(map.get("USERID")));
			//model.addAllAttributes(map);
			return "redirect:" + returnTo;
		} else if (map.get("EXCEPTION") != null) {
			log.debug("Sending view EXCEPTION with map " + map);
			model.addAllAttributes(map);
			return "exception";
		} else {
			log.debug("Sending view: LOGIN with map " + map);
			model.addAllAttributes(map);
			if (returnTo != null) {
				model.addAttribute("returnTo", returnTo);
			}
			return "login";
		}
	}

	private String findPath(String returnTo) {
		// TODO Auto-generated method stub
		if (returnTo.equals("CREATE_POST"))
			returnTo = "/post/create";
		else if (returnTo.matches("EDIT_POST\\?\\d+"))
			returnTo = "/post/edit/" + returnTo.split("\\?")[1];
		else if (returnTo.matches("DELETE_POST\\?\\d+"))
			returnTo = "/post/delete/" + returnTo.split("\\?")[1];
		else
			returnTo = "";
		return returnTo;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {

		return "register";

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Valid RegistrationForm registrationForm,
			BindingResult result, Model model) {
		HashMap<String, String> map = userService.createUser(registrationForm,
				result);
		log.debug(registrationForm);
		model.addAllAttributes(map);
		if (map.get("SUCCESS") != null) {
			log.debug("Sending view: CONFIRMATION with map " + map);
			return "confirm";
		} else if (map.get("EXCEPTION") != null) {
			log.debug("Sending view: EXCEPTION with map " + map);
			return "exception";
		} else {
			log.debug("Sending view: REGISTER with map " + map);
			return "register";
		}
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		Integer userid = (Integer) session.getAttribute("USERID");
		if (userid != null) {
			log.debug("loggin out user: " + userid);
			session.invalidate();
		}
		return "redirect:";
	}
}
