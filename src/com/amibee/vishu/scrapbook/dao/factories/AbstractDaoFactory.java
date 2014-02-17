package com.amibee.vishu.scrapbook.dao.factories;

import org.springframework.stereotype.Component;

import com.amibee.vishu.scrapbook.dao.interfaces.CommentsDao;
import com.amibee.vishu.scrapbook.dao.interfaces.PostDao;
import com.amibee.vishu.scrapbook.dao.interfaces.UserDao;

@Component
public abstract class AbstractDaoFactory {
	
	public static final int MYSQL_DAO = 0;

	public abstract UserDao getUserDao();
	public abstract PostDao getPostDao();
	public abstract CommentsDao getCommentsDao();
	
	public static AbstractDaoFactory getDaoFactory(int whichFactory){
		
		switch(whichFactory){
			case MYSQL_DAO:
				return new MysqlDaoFactory();
				
			default: return  null;
		}
		
	}

}
