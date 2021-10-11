package utils;

public class Controlli {
	
	public void mostraTentativi(int i) {
		switch(i) {
		case 0:
			System.out.println("Hai altri " + (3 - (i + 1)) + " tentativi \n" + "Cosa vuoi fare? \n" + "1. Accedi; \n" + "2. Registrati; \n" + "3. Esci;");
			break;
		case 1:
			System.out.println("Hai un ultimo tentativo \n" + "Cosa vuoi fare? \n" + "1. Accedi; \n" + "2. Registrati; \n" + "3. Esci;");
			break;
		case 2:
			System.out.println("Hai esaurito i tentativi");
			break;
		}
	}
	
}
