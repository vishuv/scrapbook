package com.amibee.vishu.scrapbook.model;

public class User {
	private int userId;
	private String email;
	private String name;
	private String hash;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", name=" + name
				+ ", hash=" + hash + "]";
	}
	
	
	
}
