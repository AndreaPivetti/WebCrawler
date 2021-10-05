package WebCrawler;


import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class WebCrawler {
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException{
		
		String username;
		String pass[] = new String[100];
		Accedi acc = new Accedi();
		
		acc.hashPassword();
		
		System.out.println("Inserisci il tuo username: ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		username = reader.readLine();
		
		acc.join(username);
		
	}
}
