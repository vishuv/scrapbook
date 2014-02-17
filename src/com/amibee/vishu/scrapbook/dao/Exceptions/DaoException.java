package com.amibee.vishu.scrapbook.dao.Exceptions;

import java.sql.SQLException;


public class DaoException extends Exception {

	public DaoException(SQLException e) {
		super(e);
	}
	
	public DaoException(String reason){
		super(reason);
	}
	
}
