package com.amibee.vishu.scrapbook.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import com.amibee.vishu.scrapbook.dao.Exceptions.DaoException;
import com.amibee.vishu.scrapbook.dao.factories.MysqlDaoFactory;
import com.amibee.vishu.scrapbook.dao.interfaces.UserDao;

import com.amibee.vishu.scrapbook.model.User;

@Component
public class MysqlUserDaoImpl extends MysqlDaoFactory implements UserDao{

	private static final String CREATE_USER = "INSERT INTO users set `email`=?, `hash`=?, `name`=?";
	private static final String FIND_USER="SELECT * FROM users WHERE `email`=?";
	private static final String GET_ROLEID = "SELECT `roleId` FROM `users` WHERE `Id`=?";
	private static final String SET_ROLEID = "UPDATE `users` set `roleId`=? WHERE `Id`=?";
	private static final String SET_HASH = "UPDATE `users` set `hash`=? WHERE `Id`=?";
	private static final String GET_USERNAME = "SELECT `name` FROM `users` WHERE `id`=?";

	@Override
	public int createUser(String email, String hash, String name)throws DaoException {
		Connection con=null;
		try {
			 con = createConnection();
			 if(findUser(email)!=null)
				 return -1;
			 con = createConnection();
			 PreparedStatement ps = createPreparedStatement(con, CREATE_USER, Statement.RETURN_GENERATED_KEYS);
			 preparePS(new Object[]{email,hash,name}, ps);
			 ps.executeUpdate();
			 ResultSet rs = ps.getGeneratedKeys();
			 rs.next();
			 return rs.getInt(1);
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally{

			try {
				if(con!=null)
					closeConnection(con);
				else
					throw new DaoException("database connection is null");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e);
			}
		}
	}


	@Override
	public User findUser(String email) throws DaoException{
			Connection con=null;
			try {
				con = createConnection();
				PreparedStatement ps = createPreparedStatement(con, FIND_USER);
				preparePS(new Object[]{email}, ps);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					User user = new User();
					user.setEmail(rs.getString("email"));
					user.setUserId(rs.getInt("id"));
					user.setName(rs.getString("name"));
					user.setHash(rs.getString("hash"));
					return user;
				}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e);
			}finally{

				try {
					if(con!=null)
						closeConnection(con);
					else
						throw new DaoException("database connection is null");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new DaoException(e);
				}
			}
			return null;
			//ask babai how to handle return values during exceptions
	}

	@Override
	public byte getRoleId(int userId) throws DaoException {
		Connection con=null;
		try {
			con = createConnection();
			PreparedStatement ps = createPreparedStatement(con, GET_ROLEID);
			preparePS(new Object[]{userId}, ps);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				return rs.getByte("roleId");	
		
			else
				
				throw new DaoException("\n"+ps.toString()+"\nuser id does not exist");
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		}finally{

			try {
				if(con!=null)
					closeConnection(con);
				else
					throw new DaoException("database connection is null");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e);
			}
		}
	}

	

	@Override
	public void setHash(int userId,String hash) throws DaoException {
		// TODO Auto-generated method stub
		Connection con=null;
		try {
			con = createConnection();
			PreparedStatement ps = createPreparedStatement(con, SET_HASH);
			preparePS(new Object[]{hash, userId}, ps);
			int rows = ps.executeUpdate();
			if(rows!=1)
				throw new DaoException("\n"+ps.toString()+"\naffected "+rows+" rows, user id does not exist");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		}finally{

			try {
				if(con!=null)
					closeConnection(con);
				else
					throw new DaoException("database connection is null");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e);
			}
		}
	}

	@Override
	public void setRoleId(int userId, byte roleId) throws DaoException {
		Connection con=null;
		try {
			con = createConnection();
			PreparedStatement ps = createPreparedStatement(con, SET_ROLEID);
			preparePS(new Object[]{roleId, userId}, ps);
			int rows = ps.executeUpdate();
			if(rows!=1)
				throw new DaoException("\n"+ps.toString()+"\naffected "+rows+" rows, user id does not exist");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		}finally{

			try {
				if(con!=null)
					closeConnection(con);
				else
					throw new DaoException("database connection is null");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e);
			}
		}
	}


	@Override
	public String getUserName(int userId) throws DaoException {
		Connection con=null;
		try {
			con = createConnection();
			PreparedStatement ps = createPreparedStatement(con, GET_USERNAME);
			preparePS(new Object[]{userId}, ps);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				return rs.getString("name");	
			else
				
				throw new DaoException("\n"+ps.toString()+"\nuser id does not exist");
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		}finally{

			try {
				if(con!=null)
					closeConnection(con);
				else
					throw new DaoException("database connection is null");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e);
			}
		}
	}
}
/*
Connection con=null;
try {
	con = createConnection();
	PreparedStatement ps = createPreparedStatement(con, SQL);


} catch (SQLException e) {
	// TODO Auto-generated catch block
	throw new DaoException(e);
}finally{

	try {
		if(con!=null)
			closeConnection(con);
		else
			throw new DaoException("database connection is null");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		throw new DaoException(e);
	}
}
*/