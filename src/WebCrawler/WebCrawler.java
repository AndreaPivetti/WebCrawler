package WebCrawler;


import java.io.*;
import java.util.Scanner;

public class WebCrawler {
	public static void main(String[] args) throws IOException{
		
		String username;
		Accedi acc = new Accedi();
				
		System.out.println("Inserisci il tuo username: ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		username = reader.readLine();
		
		acc.join(username);
		
	}
}
