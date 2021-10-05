package WebCrawler;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Accedi {

	String username;
	String password;
	String hashedPass;
	String[] account;
	BufferedReader reader;
	Hashing hash = new Hashing();

	public void verificaPassword() throws IOException, NoSuchAlgorithmException {
		try {
    		username = richiestaUsername();
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
					System.out.println("Inserisci la tua password: ");
					reader = new BufferedReader(new InputStreamReader(System.in));
					password = reader.readLine();
					hashedPass = hash.hashPassword(password);
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
	}
	
	public String richiestaUsername() throws IOException {
		String nome;
		System.out.println("Inserisci il tuo username: ");
		reader = new BufferedReader(new InputStreamReader(System.in));
		nome = reader.readLine();
		return nome;
 	}
	
	public void registraNuovoUtente() throws NoSuchAlgorithmException {
		try {
			username = richiestaUsername();
			FileWriter myWriter = new FileWriter("auth.txt", true);
			System.out.println("Inserisci una password: ");
			reader = new BufferedReader(new InputStreamReader(System.in));
			password = reader.readLine();
			hashedPass = hash.hashPassword(password);
			myWriter.write("\n" + username + ":" + hashedPass);
			myWriter.close();
		} catch (IOException err) {
			System.out.println("Si è verificato un errore \n");
			err.printStackTrace();
		}
	}

}
