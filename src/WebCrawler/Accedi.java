package WebCrawler;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

public class Accedi {

	String password;
	String hashedPass;
	String[] account;
	BufferedReader reader;

	public void verificaPassword(String username) throws IOException, NoSuchAlgorithmException {
		try {
			File file = new File("auth.txt");
			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine()) {
				String prelevaFile = myReader.nextLine();
				if (prelevaFile.contains(":")) {
					account = prelevaFile.split(":");
					if (account[0].equals(username)) {
						System.out.println("Inserisci la tua password: ");
						reader = new BufferedReader(new InputStreamReader(System.in));
						password = reader.readLine();
						hashedPass = hashPassword(password);
						while (!account[1].equals(hashedPass)) {
							System.out.println("Password errata, riprova: ");
							reader = new BufferedReader(new InputStreamReader(System.in));
							password = reader.readLine();
						}
						System.out.println("Accesso effettuato ");
					}
				}
			}
			myReader.close();
		} catch (FileNotFoundException exc) {
			System.out.println("Si è verificato un errore \n");
			exc.printStackTrace();
		}
	}

	public void registraNuovoUtente(String username) throws NoSuchAlgorithmException {
		try {
			FileWriter myWriter = new FileWriter("auth.txt", true);
			System.out.println("Inserisci una password: ");
			reader = new BufferedReader(new InputStreamReader(System.in));
			password = reader.readLine();
			hashedPass = hashPassword(password);
			myWriter.write("\n" + username + ":" + hashedPass);
			myWriter.close();
		} catch (IOException err) {
			System.out.println("Si è verificato un errore \n");
			err.printStackTrace();
		}
	}

	public String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		String hashedPass = Base64.getEncoder().encodeToString(md.digest()).toUpperCase();
		return hashedPass;
	}

}
