package WebCrawler;

import java.io.*;
import java.util.Scanner;

public class Accedi {
	
	public void join(String username) throws IOException {
		String password;
		BufferedReader reader;
		try {
            File file = new File("auth.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String prelevaFile = myReader.nextLine();
                if(prelevaFile.equals(username)) {
                	prelevaFile = myReader.nextLine();
                	System.out.println("Inserisci la tua password: ");
                	reader = new BufferedReader(new InputStreamReader(System.in));
            		password = reader.readLine();
            		while(!password.equals(prelevaFile)) {
            			System.out.println("Password errata, riprova: ");
            			reader = new BufferedReader(new InputStreamReader(System.in));
                		password = reader.readLine();
            		}
            		System.out.println("Accesso effettuato ");
                }
            }
        } catch (FileNotFoundException exc) {
            System.out.println("Si è verificato un errore \n");
            exc.printStackTrace();
        }
	}
	
}
