
public class Sessione {

	static private int utente_id;
	static private int download_effettuati;

	public static int getUtente_id() {
		return utente_id;
	}

	public static void setUtente_id(int utente_id) {
		Sessione.utente_id = utente_id;
	}
	
	public static int getDownload_effettuati() {
		return download_effettuati;
	}

	public static void setDownload_effettuati(int download_effettuati) {
		Sessione.download_effettuati = download_effettuati;
	}

}
