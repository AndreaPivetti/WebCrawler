package WebCrawler;

import java.io.*;
import java.security.NoSuchAlgorithmException;

public class WebCrawler {
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

		int scelta;
		Accedi accessoUtente = new Accedi();
		
		System.out.println("Cosa vuoi fare? \n" 
							+ "1. Accedi; \n"
							+ "2. Registrati; \n"
							+ "3. Esci; ");

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		scelta = Integer.valueOf(reader.readLine());

		switch (scelta){
        case 1:
        	accessoUtente.verificaPassword();
            break;
        case 2:
        	accessoUtente.registraNuovoUtente();
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
