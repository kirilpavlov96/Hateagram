package com.hateagram.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.sun.corba.se.impl.oa.poa.POACurrent;
import com.hateagram.model.*;

public class MySQLUtil {
	// JDBC driver name and database URL
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306";

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

			System.out.println(st.toString());

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
	
	public static void like(IUser user,Post post){
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			String sql = "select ID from hateagram.posts where userID = '" + 
						post.getUserID() + "' and postDate = '" + post.getPostDate()+ "';";
			
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int postID = rs.getInt("ID");
			
			sql = "insert into hateagram.likes (Post_ID,User_ID) values (?,?)";
			java.sql.PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, postID);
			st.setString(2, user.getUserName());
			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBConnection(conn);
		}
	}
	
	public static void disLike(IUser user,Post post){
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			String sql = "select ID from hateagram.posts where userID = '" + 
					post.getUserID() + "' and postDate = '" + post.getPostDate()+ "';";
			
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int postID = rs.getInt("ID");
			
			sql = "insert into hateagram.dislikes (Post_ID,User_ID) values (?,?)";
			java.sql.PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, postID);
			st.setString(2, user.getUserName());
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
				System.out.println(rs.toString());
				if(rs.getString(2).equals("picture")){
					result.add(new Picture(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5)));
				}
				else{
					result.add(new Video(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5)));
				}
			}

		} catch (Exception e) {
			System.out.println("Gruman we");
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
				System.out.println(rs.toString());
				if(rs.getString(2).equals("picture")){
					result.add(new Picture(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5)));
				}
				else{
					result.add(new Video(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5)));
				}
			}

		} catch (Exception e) {
			System.out.println("Gruman we");
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
			System.out.println("Gruman we");
			e.printStackTrace();
		} finally {
			closeDBConnection(conn);
		}
		return result;
	}
}

