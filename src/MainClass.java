

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

import config.DatabaseConnector;
import dao.UtenteDao;
import login.Accedi;
import login.AccediDB;
import login.AccediFile;
import utils.Controlli;

public class MainClass {
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, SQLException {
		
		int scelta;
		boolean sceltaGiusta = false;
		String tipoAccesso = "DB";
		Accedi accessoUtente = null;
		if (tipoAccesso.equals("File")) accessoUtente = new AccediFile();
		else if (tipoAccesso.equals("DB")) accessoUtente = new AccediDB();
		Controlli controllo = new Controlli();

		System.out.println("Cosa vuoi fare? \n" + "1. Accedi; \n" + "2. Registrati; \n" + "3. Esci; ");

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 3 && sceltaGiusta == false; i++) {
			try {
				scelta = Integer.valueOf(reader.readLine());

				switch (scelta) {
				case 1:
					accessoUtente.verificaPassword();
					sceltaGiusta = true;
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
