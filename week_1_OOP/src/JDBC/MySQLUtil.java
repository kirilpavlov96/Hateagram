package JDBC;

import java.sql.*;

import com.mysql.jdbc.PreparedStatement;

import OOP.*;

public class MySQLUtil {
	// JDBC driver name and database URL
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306";

	// Database credentials
	private static final String USER = "root";
	private static final String PASS = "tupfaceb00k";

	private static void openDBConnection(Connection conn, Statement stmt) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
	}

	private static void closeDBConnection(Connection conn, Statement stmt) {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public static void registerUser(IUser user) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Statement stmt = null;
		java.sql.PreparedStatement st = null;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		try {
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
			closeDBConnection(conn, stmt);
		}
	}
}
