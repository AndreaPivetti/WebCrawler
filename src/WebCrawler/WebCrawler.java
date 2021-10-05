package WebCrawler;

import java.io.*;
import java.security.NoSuchAlgorithmException;

public class WebCrawler {
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

		String username;
		int scelta;
		int registrazione = 0;
		Accedi accessoUtente = new Accedi();
		
		System.out.println("Cosa vuoi fare? \n" 
							+ "1. Accedi; \n"
							+ "2. Registrati; \n"
							+ "3. Esci; ");

		//accessoUtente.hashPassword();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		scelta = Integer.valueOf(reader.readLine());

		switch (scelta){
        case 1:
        	System.out.println("Inserisci il tuo username: ");
    		reader = new BufferedReader(new InputStreamReader(System.in));
    		username = reader.readLine();
    		accessoUtente.verificaPassword(username, registrazione);
            break;
        case 2:
        	registrazione = 1;
        	System.out.println("Qual è il tuo nome? ");
        	reader = new BufferedReader(new InputStreamReader(System.in));
    		username = reader.readLine();
    		accessoUtente.verificaPassword(username, registrazione);
            break;
        case 3:
        	System.out.println("Arrivederci!");
            break;
        default:
        	System.out.println("Scelta non valida!");
        	break;
		}
		
	}
}
