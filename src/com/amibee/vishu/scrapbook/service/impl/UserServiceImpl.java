package com.amibee.vishu.scrapbook.service.impl;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.amibee.vishu.scrapbook.dao.Exceptions.DaoException;
import com.amibee.vishu.scrapbook.dao.factories.AbstractDaoFactory;
import com.amibee.vishu.scrapbook.dao.interfaces.UserDao;
import com.amibee.vishu.scrapbook.model.User;
import com.amibee.vishu.scrapbook.model.formModels.LoginForm;
import com.amibee.vishu.scrapbook.model.formModels.RegistrationForm;
import com.amibee.vishu.scrapbook.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger log = Logger.getLogger(UserServiceImpl.class
			.getClass());
	
	//injection defined in xml
	AbstractDaoFactory daoFactory ;
	
	@Autowired
	UserDao userDao;

	@Override
	public HashMap<String, String> createUser(
			RegistrationForm registrationForm, BindingResult result) {
		HashMap<String, String> map = new HashMap<String, String>();
		if (!result.hasErrors()) {

			// implement this method
			String hash = createHash(registrationForm.getPassword());

			int userId = -1;
			try {
				userId = userDao.createUser(registrationForm.getEmail(), hash,
						registrationForm.getName());
				if (userId == -1) {
					log.debug("An account exists with given email: "
							+ registrationForm.getEmail());
					map.put("EMAIL_ERROR", "An account with given email exists");
				} else {
					log.debug("Mail Confirmation required: "
							+ registrationForm.getEmail());
					map.put("SUCCESS", "Please confirm your email");
				}
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				// log the error
				log.fatal(e.getMessage(), e);
				map.put("EXCEPTION",
						"The system has experienced a glitch and we are looking into it");
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

	private String createHash(String password) {
		return password;
	}

	/* (non-Javadoc)
	 * @see com.amibee.vishu.scrapbook.service.UserServiceIn#authenticate(com.amibee.vishu.scrapbook.model.formModels.LoginForm, org.springframework.validation.BindingResult, org.springframework.ui.Model)
	 */
	@Override
	public HashMap<String, String> authenticate(LoginForm loginForm,
			BindingResult result) {
		HashMap<String, String> map = new HashMap<String, String>();
		if (!result.hasErrors()) {
			try {
				User user = userDao.findUser(loginForm.getEmail());
				String hash = createHash(loginForm.getPassword());
				if (user!=null && hash.equals(user.getHash())) {
					log.debug("User: " + user.getUserId() + ", "
							+ user.getEmail() + "logged in");
					map.put("USERID", "" + user.getUserId());
				} else {
					log.debug("Login failed with email: "+loginForm.getEmail());
					map.put("LOGIN_ERROR", "Email/password invalid");
				}

			} catch (DaoException e) {
				log.fatal(e.getMessage(), e);
				map.put("EXCEPTION",
						"The system has experienced a glitch and we are looking into it");
			}
		} else {
			map.putAll(mapErrors(result));
		}
		return map;
		// TODO Auto-generated method stub

	}

	@Override
	public HashMap<String, String> getUserName(int userId) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			String name = userDao.getUserName(userId);
			if(name!=null)
				map.put("NAME", name);
			else
				map.put("INVALID_ID","");
		} catch (DaoException e) {
			log.fatal(e.getMessage(), e);
			map.put("EXCEPTION",
					"The system has experienced a glitch and we are looking into it");
	
		}
		return map;
	}
}
