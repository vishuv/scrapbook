package com.amibee.vishu.scrapbook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amibee.vishu.scrapbook.dao.Exceptions.DaoException;
import com.amibee.vishu.scrapbook.dao.factories.MysqlDaoFactory;
import com.amibee.vishu.scrapbook.dao.interfaces.PostDao;
import com.amibee.vishu.scrapbook.model.Post;

@Component
public class MysqlPostsDaoImpl extends MysqlDaoFactory implements PostDao {

	private static final String CREATE_POST = "INSERT INTO `posts` SET `userId`=?, `postContent`=?, `postHeading`=?, `showComments`=?";
	private static final String UPDATE_POST = "UPDATE `posts` SET `postContent`=?, `postHeading`=? where `postId`=?";
	private static final String DELETE_POST = "DELETE FROM `posts` WHERE `postId`=?";
	private static final String GET_N_POSTS_SQL = "SELECT * FROM `posts` ORDER BY `creationTime` DESC LIMIT ?,? ";
	private static final String GET_N_POSTS_OF_USER_SQL = "SELECT * FROM `posts` WHERE `userId`=? ORDER BY `creationTime` DESC LIMIT ?,?";
	private static final String SHOW_OR_HIDE_COMMENTS_SQL = "UPDATE `posts` SET `showComments`=? where `postId`=?";
	private static final String GET_SHOW_COMMENTS_SQL = "SELECT `showcomments` FROM `posts` WHERE `postId` = ?";
	private static final String GET_POST_SQL = "SELECT * FROM `posts` WHERE `postId`=?";
	

	@Override
	public void createPost( int userId, String postHeading,
		String postContent, boolean showComments) throws DaoException {
		Connection con=null;
		try {
			con = createConnection();
			PreparedStatement ps = createPreparedStatement(con, CREATE_POST);
			preparePS(new Object[]{userId,postContent,postHeading, showComments}, ps);
			ps.executeUpdate();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		}finally{
			try {
				if(con!=null)
					closeConnection(con);
				else
					throw new DaoException("problem in database connection");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e);
			}
		}
	}

	@Override
	public void deletePost(int postId) throws DaoException {
			Connection con=null;
			try {
				con = createConnection();
				PreparedStatement ps = createPreparedStatement(con, DELETE_POST);
				preparePS(new Object[]{postId}, ps);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e);
			}finally{
				try {
					if(con!=null)
						closeConnection(con);
					else
						throw new DaoException("problem in database connection");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new DaoException(e);
				}
			}
	}

	public void updatePost(int postId, String postHeading, String postContent)
			throws DaoException {
		Connection con=null;
		try {
			con = createConnection();
			PreparedStatement ps = createPreparedStatement(con, UPDATE_POST);
			preparePS(new Object[]{postContent,postHeading, postId}, ps);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		}finally{
			try {
				if(con!=null)
					closeConnection(con);
				else
					throw new DaoException("problem in database connection");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e);
			}
		}	
	}

	@Override
	public List<Post> getNPosts(int n, int fromPostId) throws DaoException {
		Connection con=null;
		try {
			con = createConnection();
			PreparedStatement ps = createPreparedStatement(con, GET_N_POSTS_SQL);
			preparePS(new Object[]{fromPostId,n}, ps);
			ResultSet rs = ps.executeQuery();
			List <Post> posts = new ArrayList<Post>();
			while(rs.next()){
				Post post = new Post();
				post.setPostId(rs.getInt("postId"));
				post.setUserId(rs.getInt("userId"));
				post.setCreationTime(rs.getTimestamp("creationTime"));
				post.setPostHeading(rs.getString("postHeading"));
				post.setPostContent(rs.getString("postContent"));
				post.setCommentsEnabled(rs.getBoolean("showComments"));
				posts.add(post);
			}
			return posts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		}finally{

			try {
				if(con!=null)
					closeConnection(con);
				else
					throw new DaoException("problem in database connection");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e);
			}
		}
	}

	@Override
	public List<Post> getNPostsOfUser(int n, int fromPostId, int userId)
			throws DaoException {
		Connection con=null;
		try {
			con = createConnection();
			PreparedStatement ps = createPreparedStatement(con, GET_N_POSTS_OF_USER_SQL);
			preparePS(new Object[]{ userId, fromPostId,n}, ps);
			ResultSet rs = ps.executeQuery();
			List <Post> posts = new ArrayList<Post>();
			while(rs.next()){
				Post post = new Post();
				post.setPostId(rs.getInt("postId"));
				post.setUserId(rs.getInt("userId"));
				post.setCreationTime(rs.getTimestamp("creationTime"));
				post.setPostHeading(rs.getString("postHeading"));
				post.setPostContent(rs.getString("postContent"));
				post.setCommentsEnabled(rs.getBoolean("showComments"));
				posts.add(post);
			}
			return posts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		}finally{

			try {
				if(con!=null)
					closeConnection(con);
				else
					throw new DaoException("problem in database connection");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e);
			}
		}
	}

	@Override
	public void showComments(int postId, boolean show) throws DaoException {
		Connection con=null;
		try {
			con = createConnection();
			PreparedStatement ps = createPreparedStatement(con, SHOW_OR_HIDE_COMMENTS_SQL);
			preparePS(new Object[]{show,postId}, ps);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		}finally{

			try {
				if(con!=null)
					closeConnection(con);
				else
					throw new DaoException("problem in database connection");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e);
			}
		}
	}

	@Override
	public Boolean isCommentsShow(int postId) throws DaoException {
		Connection con=null;
		try {
			con = createConnection();
			PreparedStatement ps = createPreparedStatement(con, GET_SHOW_COMMENTS_SQL);
			preparePS(new Object[]{ postId}, ps);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				return rs.getBoolean("showComments");
			else 
				return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		}finally{

			try {
				if(con!=null)
					closeConnection(con);
				else
					throw new DaoException("problem in database connection");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e);
			}
		}		
	}

	@Override
	public Post getPost(int postId) throws DaoException {
		Connection con=null;
		try {
			con = createConnection();
			PreparedStatement ps = createPreparedStatement(con, GET_POST_SQL);
			preparePS(new Object[]{postId}, ps);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				Post post = new Post();
				post.setPostId(rs.getInt("postId"));
				post.setUserId(rs.getInt("userId"));
				post.setCreationTime(rs.getTimestamp("creationTime"));
				post.setPostHeading(rs.getString("postHeading"));
				post.setPostContent(rs.getString("postContent"));
				post.setCommentsEnabled(rs.getBoolean("showComments"));
				return post;
			}else
				return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		}finally{

			try {
				if(con!=null)
					closeConnection(con);
				else
					throw new DaoException("problem in database connection");
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
			throw new DaoException("problem in database connection");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		throw new DaoException(e);
	}
}
*/