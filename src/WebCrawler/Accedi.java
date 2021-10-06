package WebCrawler;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Accedi {

	String username;
	String password;
	String hashedPass;
	String[] account;
	Hashing hash = new Hashing();
	LoginUtils richiedi = new LoginUtils();

	public void verificaPassword() throws IOException, NoSuchAlgorithmException {
		try {
    		username = richiedi.richiestaUsername();
    		controlloPassSuFile();
		} catch (FileNotFoundException exc) {
			System.out.println("Si è verificato un errore \n");
			exc.printStackTrace();
		}
	}

	public void controlloPassSuFile() throws IOException, NoSuchAlgorithmException {
		File file = new File("auth.txt");
		Scanner myReader = new Scanner(file);
		while (myReader.hasNextLine()) {
			String prelevaFile = myReader.nextLine();
			if (prelevaFile.contains(":")) {
				account = prelevaFile.split(":");
				if (account[0].equals(username)) {
					password = richiedi.richiestaPassword();
					hashedPass = hash.hashPassword(password);
					while (!account[1].equals(hashedPass)) {
						System.out.println("Password errata!");
						password = richiedi.richiestaPassword();
					}
					System.out.println("Accesso effettuato ");
					break;
				}
			}
		}
		myReader.close();
	}

}
