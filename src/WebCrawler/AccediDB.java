package WebCrawler;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class AccediDB {

	String username;
	String password;
	String hashedPass;
	String[] account;
	Hashing hash = new Hashing();
	LoginUtils richiedi = new LoginUtils();

	public void verificaPassword() throws IOException, NoSuchAlgorithmException, SQLException {
		try {
			username = richiedi.richiestaUsername();
			controlloPassSuFile();
		} catch (FileNotFoundException exc) {
			System.out.println("Si è verificato un errore \n");
			exc.printStackTrace();
		}
	}

	public void controlloPassSuFile() throws IOException, NoSuchAlgorithmException, SQLException {

		Connection con = null;
		Utente dbUser = null;
		try {
			DatabaseConnector dn = new DatabaseConnector();
			con = dn.connessione();
			UtenteDao utDao = new UtenteDao(con);
			dbUser = utDao.getByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		String dbPassword = dbUser.getPassword();
		password = richiedi.richiestaPassword();
		hashedPass = hash.hashPassword(password);
		while (!dbPassword.equals(hashedPass)) {
			System.out.println("Password errata!");
			password = richiedi.richiestaPassword();
		}
		System.out.println("Accesso effettuato ");

	}

}
