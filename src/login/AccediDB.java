package login;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import config.*;
import crawler.*;
import dao.*;
import models.*;
import utils.*;
public class AccediDB implements Accedi {

	String username;
	String password;
	String hashedPass;
	String[] account;
	Hashing hash = new Hashing();
	LoginUtils richiedi = new LoginUtils();

	public void verificaPassword() throws IOException, NoSuchAlgorithmException, SQLException {
		try {
			username = richiedi.richiestaUsername();
			login();
		} catch (Exception exc) {
			System.out.println("Si è verificato un errore \n");
			exc.printStackTrace();
		}
	}

	public void login() throws IOException, NoSuchAlgorithmException, SQLException {

		Connection con = null;
		Utente dbUser = null;
		try (DatabaseConnector dn = new DatabaseConnector()){	//Try with resources: una volta finito il try, chiude le connessioni aperte delle risorse definite tra le parentesi
			con = dn.connessione();
			UtenteDao utDao = new UtenteDao(con);
			dbUser = utDao.getByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if (!dbUser.getUsername().equals(null)) {
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

	public void registraNuovoUtente() throws NoSuchAlgorithmException, SQLException {

		Connection con = null;
		Utente user = null;
		try {
			DatabaseConnector dn = new DatabaseConnector();
			con = dn.connessione();
			UtenteDao utDao = new UtenteDao(con);
			username = richiedi.richiestaUsername();
			password = richiedi.richiestaPassword();
			hashedPass = hash.hashPassword(password);
			user = new Utente();
			user.setUsername(username);
			user.setPassword(hashedPass);
			utDao.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

	}

}

