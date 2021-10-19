package config;

import java.sql.*;

public class DatabaseConnector implements AutoCloseable{
	
	Connection con;
	
	public Connection connessione() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	// carica la classe del driver mysql
			con = DriverManager.getConnection("jdbc:mysql://" + System.getenv("DATABASE_HOST") + ":" + System.getenv("DATABASE_PORT") + "/web_crawler", System.getenv("DATABASE_USERNAME"), System.getenv("DATABASE_PASSWORD"));
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
