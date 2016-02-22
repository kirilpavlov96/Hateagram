package OOP;

import java.sql.SQLException;

import JDBC.MySQLUtil;

public class Demo {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		IUser user=new User("I.Borisov", 20, "bachiIvan", "zarata1234", "ivanborisov95@gmail.com");
		//MySQLUtil.registerUser(user);
		MySQLUtil.isUserExisting(user);
	}

}
