package com.hateagram.model;

import java.sql.SQLException;

import com.hateagram.DAO.*;

public class Demo {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		IUser user=new User("I.Borisov1111", 20, "bachiIvaftcn", "zarata1234", "ivanb22orisov95@gmail.com");
		System.out.println(MySQLUtil.isUserExisting("bachiIvan","zarata1234"));
	}

}
