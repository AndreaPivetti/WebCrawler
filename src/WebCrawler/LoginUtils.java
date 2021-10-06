package WebCrawler;

import java.io.*;

public class LoginUtils {
	
	BufferedReader reader;
	
	public String richiestaUsername() throws IOException {
		String nome;
		System.out.println("Inserisci il tuo username: ");
		reader = new BufferedReader(new InputStreamReader(System.in));
		nome = reader.readLine();
		return nome;
 	}
	
	public String richiestaPassword() throws IOException {
		String pass;
		System.out.println("Inserisci la password: ");
		reader = new BufferedReader(new InputStreamReader(System.in));
		pass = reader.readLine();
		return pass;
 	}
	
}
