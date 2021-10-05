package WebCrawler;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Accedi {

	public void verificaPassword(String username, int registrazione) throws IOException, NoSuchAlgorithmException {

		String password;
		String hashedPass;
		String[] account;
		BufferedReader reader;
		try {
			File file = new File("auth.txt");
			Scanner myReader = new Scanner(file);
			switch (registrazione) {
			case 0:
				while (myReader.hasNextLine()) {
					String prelevaFile = myReader.nextLine();
					if (prelevaFile.contains(":")) {
						account = prelevaFile.split(":");
						if (account[0].equals(username)) {
							System.out.println("Inserisci la tua password: ");
							reader = new BufferedReader(new InputStreamReader(System.in));
							password = reader.readLine();
							hashedPass = hashPassword(password);	
							System.out.println(hashedPass);
							while (!account[1].equals(hashedPass)) {
								System.out.println("Password errata, riprova: ");
								reader = new BufferedReader(new InputStreamReader(System.in));
								password = reader.readLine();
							}
							System.out.println("Accesso effettuato ");
						}
					}
				}
				break;
			case 1:
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
				break;
			}
		} catch (FileNotFoundException exc) {
			System.out.println("Si è verificato un errore \n");
			exc.printStackTrace();
		}

	}

	
	public String hashPassword(String password) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		String hashedPass = md.digest().toString().toUpperCase();
		return hashedPass;
	}
	
	/*
	 * public void hashPassword() throws NoSuchAlgorithmException {
	 * 
	 * String[] account;
	 * 
	 * try { File file = new File("auth.txt"); Scanner myReader = new Scanner(file);
	 * while (myReader.hasNextLine()) { String prelevaFile = myReader.nextLine(); if
	 * (prelevaFile.contains(":")) { account = prelevaFile.split(":"); MessageDigest
	 * md = MessageDigest.getInstance("MD5"); md.update(account[1].getBytes());
	 * String hashedPass = md.digest().toString().toUpperCase();
	 * System.out.println(account[1]); System.out.println(hashedPass);
	 * 
	 * try { FileWriter myWriter = new FileWriter("auth.txt", true);
	 * myWriter.write(account[0] + ":" + hashedPass); myWriter.close();
	 * System.out.println("Scrittura effettuata"); } catch (IOException err) {
	 * System.out.println("Si è verificato un errore \n"); err.printStackTrace(); }
	 * 
	 * }
	 * 
	 * } } catch (FileNotFoundException exc) {
	 * System.out.println("Si è verificato un errore \n"); exc.printStackTrace(); }
	 * 
	 * }
	 */
}
