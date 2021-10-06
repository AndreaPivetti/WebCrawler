package WebCrawler;

import java.io.*;
import java.security.NoSuchAlgorithmException;

public class Registrati {
	
	String username;
	String password;
	String hashedPass;
	String[] account;
	Hashing hash = new Hashing();
	LoginUtils richiedi = new LoginUtils();
	
	public void registraNuovoUtente() throws NoSuchAlgorithmException {
		try {
			username = richiedi.richiestaUsername();
			FileWriter myWriter = new FileWriter("auth.txt", true);
			password = richiedi.richiestaPassword();
			hashedPass = hash.hashPassword(password);
			myWriter.write("\n" + username + ":" + hashedPass);
			myWriter.close();
		} catch (IOException err) {
			System.out.println("Si è verificato un errore \n");
			err.printStackTrace();
		}
	}
	
}
