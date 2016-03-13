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

			st.setString(1, user.getUsername());
			st.setString(2, user.getName());
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
			st.setString(1, user.getUsername());

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
						post.getOwner().getUsername() + "' and postDate = '" + post.getDateAdded() + "';";
			
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int postID = rs.getInt("ID");
			
			sql = "insert into hateagram.likes (Post_ID,User_ID) values (?,?)";
			java.sql.PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, postID);
			st.setString(2, user.getUsername());
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
						post.getOwner().getUsername() + "' and postDate = '" + post.getDateAdded() + "';";
			
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int postID = rs.getInt("ID");
			
			sql = "insert into hateagram.dislikes (Post_ID,User_ID) values (?,?)";
			java.sql.PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, postID);
			st.setString(2, user.getUsername());
			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBConnection(conn);
		}
	}
	
	public static List<Post> getAllPosts(){
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
					+ "order by postDate; ";
			
			st = conn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				System.out.println(rs.toString());
				if(rs.getString(2).equals("picture")){
					result.add(new Picture(rs.getString(5), null));
				}
				else{
					result.add(new Video(rs.getString(5), null));
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
	
}
