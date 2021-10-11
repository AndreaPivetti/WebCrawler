package config;

import java.sql.*;

public class DatabaseConnector {
	
	Connection con;
	
	public Connection connessione() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_crawler", "root", "RootSQL");
			//Statement stmt = con.createStatement();
			//ResultSet rs = stmt.executeQuery("select * from utenti");
			/*
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
			}
			*/
			//con.close();
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	public void close() throws SQLException {
		con.close();
	}
	
}
