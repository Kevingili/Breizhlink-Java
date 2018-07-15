package com.sdzee.bdd;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlConnection {
	Connection conn = null;
	public static Connection dbConnector()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "toto", "toto");			
			return conn;
			
		} catch(Exception e)
		{
			return null;
		}
	}

}