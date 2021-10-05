package WebCrawler;

import java.io.*;
import java.security.NoSuchAlgorithmException;

public class WebCrawler {
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

		String username;
		Accedi accessoUtente = new Accedi();

		//accessoUtente.hashPassword();

		System.out.println("Inserisci il tuo username: ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		username = reader.readLine();

		accessoUtente.verificaPassword(username);

	}
}
