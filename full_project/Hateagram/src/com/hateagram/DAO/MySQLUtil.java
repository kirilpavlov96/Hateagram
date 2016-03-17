package com.hateagram.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.sql.rowset.serial.SerialException;

import com.mysql.jdbc.PreparedStatement;
import com.sun.corba.se.impl.oa.poa.POACurrent;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.hateagram.model.*;

public class MySQLUtil {
	// JDBC driver name and database URL
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306?autoReconnect=true&useSSL=false";

	// Database credentials
	private static final String USER = "root";
	private static final String PASS = "tupfaceb00k";


	private static void closeDBConnection(Connection conn) {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public static void registerUser(IUser user) {

		Connection conn = null;
		java.sql.PreparedStatement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// openDBConnection(conn, stmt);

			String sql = "insert into hateagram.users values (?,?,?,?,?)";
			st = conn.prepareStatement(sql);

			st.setString(1, user.getUserName());
			st.setString(2, user.getRealName());
			st.setInt(3, user.getAge());
			st.setString(4, user.getPassword());
			st.setString(5, user.getEmail());

			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBConnection(conn);
		}
	}

	public static void deleteUser(IUser user) {
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "delete from hateagram.users where userName = ?";
			st = conn.prepareStatement(sql);
			st.setString(1, user.getUserName());

			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBConnection(conn);
		}
	}
	
	public static boolean isUserExisting(String username,String password){
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			String sql = "select count(userName) as 'count' from hateagram.users "
					+ "where userName = ? and pass = ?;";
			st = conn.prepareStatement(sql);
			st.setString(1, username);
			st.setString(2, password);
			
			ResultSet rs = st.executeQuery();
			rs.next();
			if(rs.getInt("count")==0) {
				return false;
			}
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBConnection(conn);
		}
		return false;
	}
	
	public static void like(String username,int postID){
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "select count(ID) from hateagram.likes where User_ID = ? and Post_ID = ? ;";
			java.sql.PreparedStatement st1 = conn.prepareStatement(sql);
			st1.setString(1, username);
			st1.setInt(2, postID);
			ResultSet rs = st1.executeQuery();
			rs.next();
			int isLiked = rs.getInt(1);
			
			if(isLiked >= 1) return;

			sql = "insert into hateagram.likes (Post_ID,User_ID) values (?,?)";
			java.sql.PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, postID);
			st.setString(2, username);
			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBConnection(conn);
		}
	}
	
	public static void disLike(String username,int postID){
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "select count(ID) from hateagram.dislikes where User_ID = ? and Post_ID = ? ;";
			java.sql.PreparedStatement st1 = conn.prepareStatement(sql);
			st1.setString(1, username);
			st1.setInt(2, postID);
			ResultSet rs = st1.executeQuery();
			rs.next();
			int isLiked = rs.getInt(1);
			
			if(isLiked >= 1) return;

			sql = "insert into hateagram.dislikes (Post_ID,User_ID) values (?,?)";
			java.sql.PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, postID);
			st.setString(2, username);
			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBConnection(conn);
		}
	}
	
	public static void comment(String userID,String comment,int postID){
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "insert into hateagram.comments (CommentText,Post_ID,User_ID) values (?,?,?) ;";
			java.sql.PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, comment);
			st.setInt(2, postID);
			st.setString(3, userID);
			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBConnection(conn);
		}
	}
	
	public static IUser getUser(String username) {
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		IUser result = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "select * from hateagram.users where userName = ?";
			st = conn.prepareStatement(sql);
			st.setString(1, username);

			ResultSet rs = st.executeQuery();
			rs.next();	
			result = new User(rs.getString(1), rs.getString(2), rs.getInt(3), null, rs.getString(5));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBConnection(conn);
		}
		return result;
	}
	
	public static int getUserFollowersCount(String username) {
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "select count(Follower_ID) "+
						 "from hateagram.followers "+
						 "where User_ID = ? ;";
			st = conn.prepareStatement(sql);
			st.setString(1, username);

			ResultSet rs = st.executeQuery();
			rs.next();	
			result = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBConnection(conn);
		}
		return result;
	}
	
	public static int getUserFollowingCount(String username) {
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "select count(Following_ID) "+
						 "from hateagram.following "+
						 "where User_ID = ? ;";
			st = conn.prepareStatement(sql);
			st.setString(1, username);

			ResultSet rs = st.executeQuery();
			rs.next();	
			result = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBConnection(conn);
		}
		return result;
	}
	
	public static int getUserPostsCount(String username) {
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		int result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "select count(ID) "+
						 "from hateagram.posts "+
						 "where User_ID = ? ;";
			st = conn.prepareStatement(sql);
			st.setString(1, username);

			ResultSet rs = st.executeQuery();
			rs.next();	
			result = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBConnection(conn);
		}
		return result;
	}
	
	public static List<Post> getAllPostsOfUserByDate(String username){
		List<Post> result = new ArrayList<Post>();
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "select p.ID,o.postType,p.User_ID,p.postDate,p.filename "
					+ " from hateagram.posts p "
					+ "join hateagram.posttypes o "
					+ "on p.postType = o.ID "
					+ "and p.User_ID = ? "
					+ "order by postDate; ";
			
			st = conn.prepareStatement(sql);
			st.setString(1, username);
			
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				if(rs.getString(2).equals("picture")){
					result.add(new Picture(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5)));
				}
				else{
					result.add(new Video(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5)));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBConnection(conn);
		}
		return result;
	}
	
	public static List<Post> getAllPostsUsersFollowers(String username){
		List<Post> result = new ArrayList<Post>();
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "select p.ID,o.postType,p.User_ID,p.postDate,p.filename " +
					"from hateagram.following f  "+
					"join hateagram.posts p  "+
					"on f.Following_ID = p.User_ID "+
					"and f.User_ID = ? "+
					"join hateagram.posttypes o "+
					"on o.ID=p.postType "+
					"order by p.postDate; ";
			
			st = conn.prepareStatement(sql);
			st.setString(1, username);
			
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				if(rs.getString(2).equals("picture")){
					result.add(new Picture(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5)));
				}
				else{
					result.add(new Video(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5)));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBConnection(conn);
		}
		return result;
	}
	
	public static List<Comment> getAllCommentsOfPost(Post post){
		List<Comment> result = new ArrayList<Comment>();
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "select * "+
					"from hateagram.comments "+
					"where Post_ID = ? ;";
			
			st = conn.prepareStatement(sql);
			st.setInt(1, post.getID());
			
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				result.add(new Comment(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4)));		
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBConnection(conn);
		}
		return result;
	}

	public static int getPostLikes(Post post){
		Connection conn = null;
		int result=0;
		java.sql.PreparedStatement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "select count(ID) "+
					"from hateagram.likes "+
					"where Post_ID = ? ;";
			
			st = conn.prepareStatement(sql);
			st.setInt(1, post.getID());
			
			ResultSet rs = st.executeQuery();
			rs.next();
			result=rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBConnection(conn);
		}
		return result;
	}

	public static int getPostDisLikes(Post post){
		Connection conn = null;
		int result=0;
		java.sql.PreparedStatement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "select count(ID) "+
					"from hateagram.dislikes "+
					"where Post_ID = ? ;";
			
			st = conn.prepareStatement(sql);
			st.setInt(1, post.getID());
			
			ResultSet rs = st.executeQuery();
			rs.next();
			result=rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBConnection(conn);
		}
		return result;
	}
	
	public static void addNewPicture(Picture pic){
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "INSERT INTO hateagram.posts (postType,User_ID,filename)"
						+ " values (?,?,?)";
			
			st = conn.prepareStatement(sql);
			st.setString(1, "1");
			st.setString(2, pic.getUserID());
			st.setString(3, pic.getFilename());
			
			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBConnection(conn);
		}
	}
	
	public static void addFollowing(String user,String following) throws ServletException{	
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "insert into hateagram.following (User_ID,Following_ID) "
						+"values (?,?) ;";
			
			st = conn.prepareStatement(sql);
			st.setString(1, user);
			st.setString(2, following);
			
			st.executeUpdate();

		} catch (Exception e) {
			throw new ServletException("Unable to add user to follow!",e);
		} finally {
			closeDBConnection(conn);
		}
	}
	
	public static void addFollower(String user,String follower) throws ServletException{	
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "insert into hateagram.followers (User_ID,Follower_ID) "
						+"values (?,?) ;";
			
			st = conn.prepareStatement(sql);
			st.setString(1, user);
			st.setString(2, follower);
			
			st.executeUpdate();

		} catch (Exception e) {
			throw new ServletException("Unable to add user to follow!",e);
		} finally {
			closeDBConnection(conn);
		}
	}
	
	public static List<IUser> searchForUsers(String key) throws ServletException{
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		List<IUser> result = new ArrayList<IUser>();
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "select * from hateagram.users "
						+ "where userName like ? "
						+ "or realName like ? ;";
			st = conn.prepareStatement(sql);
			st.setString(1, "%"+key+"%");
			st.setString(2, "%"+key+"%");
			
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				result.add(new User(rs.getString(1), rs.getString(2),
							rs.getInt(3), rs.getString(4), rs.getString(5)));
			}

		} catch (Exception e) {
			throw new ServletException("Unable to connect to database!",e);
		} finally {
			closeDBConnection(conn);
		}
		return result;
	}
}

