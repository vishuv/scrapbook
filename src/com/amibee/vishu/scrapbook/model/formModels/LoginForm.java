package com.amibee.vishu.scrapbook.model.formModels;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.sun.istack.internal.NotNull;

public class LoginForm {
	@NotEmpty @NotNull @Email
	private String email;
	@NotNull @NotEmpty
	private String password;
	@NotNull
	private String returnTo;
	
	public String getReturnTo() {
		return returnTo;
	}
	public void setReturnTo(String returnTo) {
		this.returnTo = returnTo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginForm [email=" + email + ", password=" + password
				+ ", returnTo=" + returnTo + "]";
	}
	
}
