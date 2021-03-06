package JDBC;
import java.sql.*;
import com.mysql.jdbc.PreparedStatement;
import OOP.*;

public class MySQLUtil 
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306";
	private static final String USER = "root";
	private static final String PASS = "*";

	private static void openDBConnection(Connection conn, Statement stmt) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
	}

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
		Statement stmt = null;
		java.sql.PreparedStatement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
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
		Statement stmt = null;
		java.sql.PreparedStatement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
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

	public static boolean isUserExisting(IUser user) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql = "select count(userName) as 'count' from hateagram.users where userName = '"
					+ user.getUsername() + "';";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			if (rs.getInt("count") == 0) {
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

	public static void like(IUser user, Post post) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql = "select ID from hateagram.posts where userID = '" + post.getOwner().getUsername()
					+ "' and postDate = '" + post.getDateAdded() + "';";
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

	public static void disLike(IUser user, Post post) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql = "select ID from hateagram.posts where userID = '" + post.getOwner().getUsername()
					+ "' and postDate = '" + post.getDateAdded() + "';";
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

	public static void addPost(IUser user, Post post) {
		Connection conn = null;
		Statement stmt = null;
		java.sql.PreparedStatement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql = "insert into hateagram.post values (?,?,?,?)";
			st = conn.prepareStatement(sql);
			st.setString(1, post.getCounter());
			st.setString(2, post.getClass().getSimpleName());
			st.setString(3, post.getOwner());
			st.setInt(4, post.getDateAdded());
			System.out.println(st.toString());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBConnection(conn);
		}
	}

	public static void deletePost(Post post) {
		Connection conn = null;
		Statement stmt = null;
		java.sql.PreparedStatement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql = "delete from hateagram.post where ID = ?";
			st = conn.prepareStatement(sql);
			st.setString(1, post.getCounter());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBConnection(conn);
		}
	}

	public static void addNewComment(Comment comment) {

		Connection conn = null;
		Statement stmt = null;
		java.sql.PreparedStatement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql = "insert into hateagram.comments values (?,?,?)";
			st = conn.prepareStatement(sql);
			st.setString(1, comment.getCounter());
			st.setString(2, comment.getText());
			st.setString(3, comment.getPost().getCounter());
			st.setInt(4, comment.getCommentator().getUsername());
			System.out.println(st.toString());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBConnection(conn);
		}
	}

	public static void showFollowers(IUser user) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql = "select * from hateagram.followers where userID = '" + user.getUsername();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString("Follower_ID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBConnection(conn);
		}
	}
	
	public static void addFollower(IUser user, IUser follower) {
		Connection conn = null;
		Statement stmt = null;
		java.sql.PreparedStatement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql = "insert into hateagram.followers values (?,?)";
			st = conn.prepareStatement(sql);
			st.setString(1, user.getUsername());
			st.setString(2, follower.getUsername());
			System.out.println(st.toString());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBConnection(conn);
		}
	}

}
