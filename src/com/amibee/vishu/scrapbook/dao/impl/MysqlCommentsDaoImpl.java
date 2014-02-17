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
import com.amibee.vishu.scrapbook.dao.interfaces.CommentsDao;
import com.amibee.vishu.scrapbook.model.Comment;

@Component
public class MysqlCommentsDaoImpl extends MysqlDaoFactory implements CommentsDao {

	private static final String GET_N_COMMENTS = "SELECT * FROM `comments` WHERE `postId`=? AND `commentStatus`=1 ORDER BY `commentTime`	DESC LIMIT ?, ?";
	private static final String DELETE_COMMENTS = "DELETE FROM `comments` WHERE `commentId` in (?)";
	private static final String CREATE_COMMENT = "INSERT INTO `comments` set `postId`=?, `userId`=?, `commentContent`=?, `name`=?, `commentStatus`=?";
	private static final String SET_COMMENTS_STATUS_SQL = "UPDATE `comments` SET `commentStatus` WHERE `commentId` in (?)";
	private static final String GET_RECENT_COMMENTS = "SELECT * FROM `comments` WHERE `postId`=? AND `commentId` > ? AND `commentStatus`=1 ORDER BY `commentTime`	DESC";
	@Override
	public void createComment(int userId, int postId,
			String commentContent, String name, boolean commentStatus) throws DaoException {
		Connection con=null;
		try {
			con = createConnection();
			PreparedStatement ps = createPreparedStatement(con, CREATE_COMMENT);
			preparePS(new Object[]{postId, userId, commentContent, name, commentStatus}, ps);
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
	public List<Comment> getNComments(int n, int postId, int fromCommentId) throws DaoException {
		Connection con=null;
		try {
			con = createConnection();
			PreparedStatement ps = createPreparedStatement(con, GET_N_COMMENTS);
			preparePS(new Object[]{postId, fromCommentId, n}, ps);
			ResultSet rs = ps.executeQuery();
			List<Comment> comments = new ArrayList<Comment>();
			while(rs.next()){
				Comment comment = new Comment();
				comment.setUserId(rs.getInt("userId"));
				comment.setPostId(rs.getInt("postId"));
				comment.setCommentId(rs.getInt("commentId"));
				comment.setCommentContent(rs.getString("commentContent"));
				comment.setTime(rs.getTimestamp("commentTime"));
				comment.setCommentorName(rs.getString("name"));
				comment.setCommentStatus(rs.getBoolean("commentStatus"));
				comments.add(comment);
			}
			return comments;
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally{
			try {
				if(con!=null)
					closeConnection(con);
				else 
					throw new DaoException("Database connection null");
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
	}

	@Override
	public void deleteComments(int[] commentIds) throws DaoException {
		if(commentIds!=null){
			Connection con=null;
			try {
				con = createConnection();
				PreparedStatement ps = createPreparedStatement(con, DELETE_COMMENTS);
				String commentsIdsToDelete="";
				for(int commentId: commentIds){
					commentsIdsToDelete+=(commentId+",");
				}
				commentsIdsToDelete=commentsIdsToDelete.substring(0,commentsIdsToDelete.length()-1);
				preparePS(new Object[]{commentsIdsToDelete},ps);
				int rows = ps.executeUpdate();
				if(rows!=1)
					throw new DaoException("\n"+ps.toString()+"\naffected "+rows+" rows ");
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

	@Override
	public void setStatus(int[] commentIds, byte status) throws DaoException {
		Connection con=null;
		try {
			con = createConnection();
			PreparedStatement ps = createPreparedStatement(con, SET_COMMENTS_STATUS_SQL);
			String commentsIdsToDelete="";
			for(int commentId: commentIds){
				commentsIdsToDelete+=(commentId+",");
			}
			commentsIdsToDelete=commentsIdsToDelete.substring(0,commentsIdsToDelete.length()-1);
			preparePS(new Object[]{commentsIdsToDelete}, ps);
			int rows = ps.executeUpdate();
			if(rows!=1)
				throw new DaoException("\n"+ps.toString()+"\naffected "+rows+" rows ");
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
	public List<Comment> getRecentComments(int postId, int commentId) throws DaoException {
		Connection con=null;
		try {
			con = createConnection();
			PreparedStatement ps = createPreparedStatement(con, GET_RECENT_COMMENTS);
			preparePS(new Object[]{postId, commentId}, ps);
			ResultSet rs = ps.executeQuery();
			List<Comment> comments = new ArrayList<Comment>();
			while(rs.next()){
				Comment comment = new Comment();
				comment.setUserId(rs.getInt("userId"));
				comment.setPostId(rs.getInt("postId"));
				comment.setCommentId(rs.getInt("commentId"));
				comment.setCommentContent(rs.getString("commentContent"));
				comment.setTime(rs.getTimestamp("commentTime"));
				comment.setCommentorName(rs.getString("name"));
				comment.setCommentStatus(rs.getBoolean("commentStatus"));
				comments.add(comment);
			}
			return comments;
		} catch (SQLException e) {
			throw new DaoException(e);
		}finally{
			try {
				if(con!=null)
					closeConnection(con);
				else 
					throw new DaoException("Database connection null");
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
	}	

}
