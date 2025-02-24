package com.pr.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {

	//Initialize the Connection Variable
	private static final String url = "jdbc:mysql://localhost:3306/Login_Regi_DB";
	private static final String username = "root";
	private static final String password = "1234";
	
	
	//Load The SQL Driver 
	 static { 
	 		try {
	 			Class.forName("com.mysql.cj.jdbc.Driver");
	 		}catch (Exception e) {
	 			System.out.println(e.getMessage());
	 		}
}
	 
	 //Create The Connection 
	 public static Connection getConnection() throws SQLException {
		 
		 //System.out.println("Connection are building..");
		 return DriverManager.getConnection(url,username,password);
	 }
	
}
