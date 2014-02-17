package com.amibee.vishu.scrapbook.dao.tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import com.amibee.vishu.scrapbook.dao.Exceptions.DaoException;
import com.amibee.vishu.scrapbook.dao.factories.AbstractDaoFactory;
import com.amibee.vishu.scrapbook.dao.interfaces.PostDao;
import com.amibee.vishu.scrapbook.model.Post;

public class PostDaoTest extends Test {
		
	public static List<Post> getNposts(PostDao postDao,int n, int fromPostid) throws DaoException{
		List<Post> posts = postDao.getNPosts(10, 0);
		for(Post post: posts)
			System.out.println(post);
		return posts;
	}
	
	public static void createPost(PostDao postDao, int userId, String postHeading, String postContent, boolean showComments ) throws DaoException{
		postDao.createPost(userId, postHeading, postContent, showComments);
	}

	public static void deletePost(PostDao postDao, int postId) throws DaoException{
		postDao.deletePost(postId);
	}
	
	public static List<Post> getNpostsOfuser(PostDao postDao,int n, int fromPostId, int userId) throws DaoException{
		List<Post> posts = postDao.getNPostsOfUser(n, fromPostId, userId);
		for(Post post: posts)
			System.out.println(post);
		return posts;
	}
	
	public static void UpdatePost(PostDao postDao, int postId, String postHeading, String postContent) throws DaoException{
		postDao.updatePost(postId, postHeading, postContent);
	}
	
	public static void showComments(PostDao postDao, int postId, boolean show) throws DaoException{
		postDao.showComments(postId, show);
	}
	
	public static boolean isCommentsShow(PostDao postDao, int postId) throws DaoException{
		boolean show=postDao.isCommentsShow(postId);
		System.out.println(show);
		return show;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException, DaoException{
		AbstractDaoFactory daoFactory = AbstractDaoFactory.getDaoFactory(AbstractDaoFactory.MYSQL_DAO);
		PostDao postDao = daoFactory.getPostDao();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Properties prop = readPropertiesFile("tests.properties");

		do{
			System.out.println(
					"1.create Post\n"+
					"2.getPostsOfUser\n"+
					"3.getPosts\n"+
					"4.deletePost\n"+
					"5.updatePost\n"+
					"6.showCommwents\n"+
					"7.isCommentsShow");
			
			int choice=Integer.parseInt(br.readLine());
			int user;
			String postKey="post";
			int post;
			
			switch(choice){
				case 1:
					System.out.print("no of posts:");
					int n= Integer.parseInt(br.readLine());
					System.out.print("userId: ");
					user=Integer.parseInt(br.readLine());
					System.out.print("post type: ");
					post=Integer.parseInt(br.readLine());
					postKey+=post+".";
					for(int i=0;i<n;i++){
						createPost(postDao, user, (String)prop.get(postKey+"heading"), (String)prop.get(postKey+"content"), Boolean.parseBoolean((String) prop.get(postKey+"commentsEnabled")));
					}
					break;
				case 2:
					System.out.print("no of posts and postId: ");
					getNposts(postDao, Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()));
					break;
				case 3:
					System.out.print("no of posts and postId and userId: ");
					getNpostsOfuser(postDao, Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()));
					break;
				case 4:
					System.out.print("postId: ");
					deletePost(postDao, Integer.parseInt(br.readLine()));
					break;
				case 5:
					System.out.print("postId: ");
					int postId=Integer.parseInt(br.readLine());
					System.out.print("postType: ");
					post=Integer.parseInt(br.readLine());
					postKey+=post+".";
					UpdatePost(postDao, postId, (String)prop.get(postKey+"heading"), (String)prop.get(postKey+"heading"));
					break;
				case 6:
					System.out.print("postId and true/false: ");
					showComments(postDao, Integer.parseInt(br.readLine()), Boolean.parseBoolean(br.readLine()));
					break;
				case 7:
					System.out.print("postId: ");
					isCommentsShow(postDao, Integer.parseInt(br.readLine()));
					break;
				default:
					return;
			}
		}while(true);
	}
}
