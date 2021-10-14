

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import config.DatabaseConnector;
import crawler.*;
import dao.*;
import login.*;
import models.Downloads;
import utils.*;

public class MainClass {
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, SQLException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int scelta = 0;
		boolean sceltaGiusta = false;
		
		String tipoAccesso = " ";
		try {
			System.out.println("A cosa vuoi accedere? \n" + "1. DataBase; \n" + "2. File; ");
			scelta = Integer.valueOf(reader.readLine());
		} catch (Exception e) {
			System.out.println("Errore! ");
		}
		while (scelta != 1 && scelta != 2) {
			System.out.println("Scelta non valida! \n" + "A cosa vuoi accedere? \n" + "1. DataBase; \n" + "2. File; ");
			scelta = Integer.valueOf(reader.readLine());
		}
		if(scelta == 1) {
			tipoAccesso = "DB";
		} else if(scelta == 2) {
			tipoAccesso = "File";
		} else {
			System.out.println("Scelta non valida! ");
		}
		Accedi accessoUtente = null;
		if (tipoAccesso.equals("File"))
			accessoUtente = new AccediFile();
		else if (tipoAccesso.equals("DB"))
			accessoUtente = new AccediDB();
		
		Controlli controllo = new Controlli();

		System.out.println("Cosa vuoi fare? \n" + "1. Accedi; \n" + "2. Registrati; \n" + "3. Esci; ");

		for (int i = 0; i < 3 && !sceltaGiusta; i++) {	// !sceltaGiusta corrisponde a sceltaGiusta == false
			try {
				scelta = Integer.valueOf(reader.readLine());

				switch (scelta) {
				case 1:
					sceltaGiusta = true;
					
					accessoUtente.verificaPassword();
					LoginUtils richiedi = new LoginUtils();

					String url = richiedi.richiestaURL();
					WebCrawler crawler = new WebCrawler();
					crawler.crawler(url);
					
					break;
				case 2:
					accessoUtente.registraNuovoUtente();
					sceltaGiusta = true;
					break;
				case 3:
					System.out.println("Arrivederci!");
					sceltaGiusta = true;
					break;
				default:
					System.out.println("Scelta non valida!");
					controllo.mostraTentativi(i);
					break;
				}
			} catch (Exception e) {
				System.out.println("Errore! ");
				controllo.mostraTentativi(i);
			}
		}
	}

}
