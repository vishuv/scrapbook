package com.amibee.vishu.scrapbook.model.formModels;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class RegistrationForm {
	@NotNull @Email @NotEmpty
	private String email;
	@NotNull @Length(min=6, max=20)
	private String password;
	@NotNull @Length(min=4, max=32)
	private String name;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "RegistrationForm [email=" + email + ", password=" + password
				+ ", name=" + name + "]";
	}
	
}
