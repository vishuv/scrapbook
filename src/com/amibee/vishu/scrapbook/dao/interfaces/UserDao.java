package com.amibee.vishu.scrapbook.dao.interfaces;

import com.amibee.vishu.scrapbook.dao.Exceptions.DaoException;
import com.amibee.vishu.scrapbook.model.User;

public interface UserDao {
	
	//create a new user
	public int createUser(String email, String hash, String name) throws DaoException;
	
	//check if a user exists
	public User findUser(String email) throws DaoException ;
	
	//get and set roles
	public byte getRoleId(int userId) throws DaoException;
	
	public void setRoleId(int userId, byte roleId) throws DaoException;
	
	//set password hash for changing passwords
	public void setHash(int userId, String hash) throws DaoException;

	public String getUserName(int userId)  throws DaoException;
	
}
