package WebCrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

public class WebCrawler {
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, SQLException {
		
		Connection con = null;
		try{
			DatabaseConnector dn = new DatabaseConnector();
			con = dn.connessione();
			UtenteDao utDao = new UtenteDao(con);
			utDao.get(1);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		
		
		int scelta;
		boolean sceltaGiusta = false;
		Accedi accessoUtente = new Accedi();
		Registrati registraUtente = new Registrati();
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
					registraUtente.registraNuovoUtente();
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
				System.out.println("Nessun numero intero inserito! ");
				controllo.mostraTentativi(i);
			}
		}
	}

}
