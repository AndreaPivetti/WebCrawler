package WebCrawler;

public class Controlli {
	
	public void mostraTentativi(int i) {
		switch(i) {
		case 0:
			System.out.println("Hai altri " + (3 - (i + 1)) + " tentativi");
			break;
		case 1:
			System.out.println("Hai un ultimo tentativo");
			break;
		case 2:
			System.out.println("Hai esaurito i tentativi");
			break;
		}
	}
	
}
