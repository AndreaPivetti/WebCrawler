package WebCrawler;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Accedi {
	
	public void join(String username) throws IOException {
		
		
		String password;
		String[] account; 
		BufferedReader reader;
		try {
            File file = new File("auth.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String prelevaFile = myReader.nextLine();
                account = prelevaFile.split(":");
                if(account[0].equals(username)) {
                	System.out.println("Inserisci la tua password: ");
                	reader = new BufferedReader(new InputStreamReader(System.in));
            		password = reader.readLine();
            		while(!password.equals(account[1])) {
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
	
	public void hashPassword() throws NoSuchAlgorithmException {
		
		String hash = "35454B055CC325EA1AF2126E27707052";
		String password = "ILoveJava";
		String[] account;
		
		try {
            File file = new File("auth.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String prelevaFile = myReader.nextLine();
                account = prelevaFile.split(":");
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(password.getBytes());
                String hashedPass = new String( md.digest()).toUpperCase();
                System.out.println(hash);
                System.out.println(hashedPass);
            }
        } catch (FileNotFoundException exc) {
            System.out.println("Si è verificato un errore \n");
            exc.printStackTrace();
        }
		

	}
	
}
