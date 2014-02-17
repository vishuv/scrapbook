/*package com.amibee.vishu.scrapbook.dao.tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import com.amibee.vishu.scrapbook.dao.Exceptions.DaoException;
import com.amibee.vishu.scrapbook.dao.factories.AbstractDaoFactory;
import com.amibee.vishu.scrapbook.dao.interfaces.CommentsDao;
import com.amibee.vishu.scrapbook.model.Comment;

public class CommentsDaoTest extends Test {
		
		public static void main(String[] args) throws NumberFormatException, IOException, DaoException{
		AbstractDaoFactory daoFactory = AbstractDaoFactory.getDaoFactory(AbstractDaoFactory.MYSQL_DAO);
		CommentsDao commentsDao = daoFactory.getCommentsDao();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Properties prop = readPropertiesFile("tests.properties");

		do{
			System.out.println(
					"1.createComments\n"+
					"2.getcomments\n"+
					"3.deleteComments\n"+
					"4.set comment status");
			
			int choice=Integer.parseInt(br.readLine());
			int user;
			String commentKey="comment";
			int post;
			int comment;
			
			switch(choice){
				case 1:
					System.out.print("userId: ");
					user=Integer.parseInt(br.readLine());
					System.out.print("postId: ");
					post=Integer.parseInt(br.readLine());
					System.out.print("comment type: ");
					comment = Integer.parseInt(br.readLine());
					commentKey+=comment+".";
					createComment(commentsDao, user, post, (String)prop.get(commentKey+"content"));
					break;
				case 2:
					System.out.print("no of comments and postId and commentID: ");
					getNcomments(commentsDao,  Integer.parseInt(br.readLine()),  Integer.parseInt(br.readLine()),  Integer.parseInt(br.readLine()));
					break;
				case 3:
					System.out.print("commentId: ");
					deleteComment(commentsDao, Integer.parseInt(br.readLine()));
					break;
				default:
					return;
			}
		}while(true);
	}

		private static void deleteComment(CommentsDao commentsDao, int parseInt) throws DaoException {
			commentsDao.deleteComments(new int[]{parseInt});
		}

		private static void getNcomments(CommentsDao commentsDao, int parseInt,
				int parseInt2, int parseInt3) throws DaoException {
			for(Comment comment:commentsDao.getNComments(parseInt, parseInt2, parseInt3)){
				System.out.println(comment);
			}
		}

		private static void createComment(CommentsDao commentsDao, int user,
				int post, String string) throws DaoException {
			commentsDao.createComment(user, post, string);
			
		}
}
*/