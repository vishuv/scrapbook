package com.amibee.vishu.scrapbook.service.interfaces;

import java.util.HashMap;

import org.springframework.validation.BindingResult;

import com.amibee.vishu.scrapbook.model.formModels.LoginForm;
import com.amibee.vishu.scrapbook.model.formModels.RegistrationForm;

public interface UserService {

	public  HashMap<String, String> createUser(
			RegistrationForm registrationForm, BindingResult result);

	public HashMap<String, String> authenticate(LoginForm loginForm,
			BindingResult result);

	public HashMap<String, String> getUserName(int userId);

}