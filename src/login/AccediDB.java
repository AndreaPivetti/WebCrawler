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
	String[] ultAcc = new String[1];
	Hashing hash = new Hashing();
	LoginUtils richiedi = new LoginUtils();
	public boolean puoi_scaricare;
	int limite_download;

	public void verificaPassword() throws IOException, NoSuchAlgorithmException, SQLException {
		try {
			username = richiedi.richiestaUsername();
			login();
			if(Sessione.getDownload_effettuati() >= this.limite_download) {
				setPuoi_scaricare(false);
			} else {
				setPuoi_scaricare(true);
			}
		} catch (Exception exc) {
			System.out.println("Si è verificato un errore \n");
			exc.printStackTrace();
		}
	}

	public void login() throws IOException, NoSuchAlgorithmException, SQLException {

		Connection con = null;
		UtenteDao utDao = null;
		Utente dbUser = null;
		try { 
			DatabaseConnector dn = new DatabaseConnector();
			con = dn.connessione();
			utDao = new UtenteDao(con);
			dbUser = utDao.getByUsername(username);
			if (!dbUser.getUsername().equals(null)) {
				String dbPassword = dbUser.getPassword();
				password = richiedi.richiestaPassword();
				hashedPass = hash.hashPassword(password);
				while (!dbPassword.equals(hashedPass)) {
					System.out.println("Password errata!");
					password = richiedi.richiestaPassword();
				}
				System.out.println("Accesso effettuato ");
				Sessione.setUtente_id(dbUser.getId());
				System.out.println("Bentornato/a " + username);
				String ultimoAccesso = dbUser.getUltimo_accesso();
				if (ultimoAccesso == null) {
					ultimoAccesso = "Mai";
				}
				System.out.println("Ultimo accesso: " + ultimoAccesso);
				EstraiDataOrario aggiornaAccesso = new EstraiDataOrario();
				ultAcc[0] = aggiornaAccesso.estraiData();
				utDao.update(dbUser, ultAcc);
				//System.out.println("Fino ad ora hai effettuato " + utDao.getTotalUserDownload(username) + " download su un massimo di " + dbUser.getMax_downloads());
				try {
					utDao.getTotalUserDownload(username);
					this.limite_download = dbUser.getMax_downloads();
					System.out.println("Fino ad ora hai effettuato " + Sessione.getDownload_effettuati() + " download su un massimo di " + dbUser.getMax_downloads());
				} catch(Exception exc) {
					System.out.println(exc.getMessage());
					exc.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
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

	public boolean getPuoi_scaricare() {
		return puoi_scaricare;
	}

	public void setPuoi_scaricare(boolean puoi_scaricare) {
		this.puoi_scaricare = puoi_scaricare;
	}
	
}
