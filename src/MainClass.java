

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import config.DatabaseConnector;
import crawler.*;
import dao.*;
import login.*;
import models.*;
import utils.*;

public class MainClass {
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, SQLException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int scelta = 0;
		boolean sceltaGiusta = false;
		String contatta;
		EstraiDataOrario data = new EstraiDataOrario();
		
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

		System.out.println("Cosa vuoi fare? \n" + "1. Accedi; \n" + "2. Registrati; \n" + "3. Admin panel \n" + "4. Esci; ");

		for (int i = 0; i < 4 && !sceltaGiusta; i++) {	// !sceltaGiusta corrisponde a sceltaGiusta == false
			try {
				scelta = Integer.valueOf(reader.readLine());

				switch (scelta) {
				case 1:
					sceltaGiusta = true;
					
					accessoUtente.verificaPassword();
					if(accessoUtente.getPuoi_scaricare() == true) {	
						LoginUtils richiedi = new LoginUtils();
						String url = richiedi.richiestaURL();
						WebCrawler crawler = new WebCrawler();
						crawler.crawler(url);
					} else {
						System.out.println("Hai esaurito il numero massimo di download! \n" + "Vuoi contattare l'amministratore? (si/no)");
						contatta = reader.readLine().toLowerCase();
						if (contatta.equals("si")) {
							Connection con = null;
							NotificheAdminDao notificheAdminDao = null;
							NotificheAdmin notificheAdmin = new NotificheAdmin();
							try { 
								DatabaseConnector dn = new DatabaseConnector();
								con = dn.connessione();
								notificheAdminDao = new NotificheAdminDao(con);
								boolean controlla = notificheAdminDao.controllaNotifica(Sessione.getUtente_id());
								if(!controlla) {	// !controlla corrisponde a controlla == false
									System.out.println("Hai già una richiesta in attesa ");									
								} else if(controlla) {	// controlla corrisponde a controlla == true
									notificheAdmin.setUtente_id(Sessione.getUtente_id());
									notificheAdmin.setData(data.estraiData());
									notificheAdmin.setMostra(true);
									notificheAdminDao.save(notificheAdmin);
									System.out.println("L'admin è stato contattato ");
								}								
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								con.close();
							}
						} else if (contatta.equals("no")) {
							System.out.println("L'admin non sarà contattato ");
						} else {
							System.out.println("Scelta non valida! ");
						}
					}
					
					break;
				case 2:
					accessoUtente.registraNuovoUtente();
					sceltaGiusta = true;
					break;
				case 3:
					String adminPass;
					System.out.println("Inserisci la password: ");
					adminPass = reader.readLine();
					if(adminPass.equals(System.getenv("ADMIN_CRAWLER_KEY"))) {	// la password dell'admin è una variabile d'ambiente perciò per controllarla si usa: System.getenv("nome_variabile_d'ambiente")
						System.out.println("Cosa vuoi fare? \n" + "1. Mostra richieste; \n" + "2. Modifica limite di download; \n" + "3. Chiudi richiesta; ");
						scelta = Integer.valueOf(reader.readLine());
						switch (scelta) {
						case 1:
							Connection con = null;
							NotificheAdminDao notificheAdminDao = null;
							try { 
								DatabaseConnector dn = new DatabaseConnector();
								con = dn.connessione();
								notificheAdminDao = new NotificheAdminDao(con);
								notificheAdminDao.mostraNotifiche();
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								con.close();
							}
							break;
						case 2:
							int id = 0, nuovo_limite;
													
							con = null;
							UtenteDao utDao = null;
							Utente utente = null;
							try { 
								System.out.println("Inserisci l'id dell'utente a cui vuoi modificare il limite di download: ");
								id = Integer.valueOf(reader.readLine());
								DatabaseConnector dn = new DatabaseConnector();
								con = dn.connessione();
								utDao = new UtenteDao(con);
								utente = utDao.get(id);
								System.out.println("L'attuale limite dell'utente con id = " + id + " è: " + utente.getMax_downloads());
								System.out.println("Inserisci il nuovo limite: ");
								nuovo_limite = Integer.valueOf(reader.readLine());
								utDao.updateLimite(utente, nuovo_limite);
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								con.close();
							}
							
							notificheAdminDao = null;
							try { 
								DatabaseConnector dn = new DatabaseConnector();
								con = dn.connessione();
								notificheAdminDao = new NotificheAdminDao(con);
								notificheAdminDao.updateMostra(id, false);
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								con.close();
							}
							
							break;
						case 3:
							con = null;
							notificheAdminDao = null;
							System.out.println("Inserisci l'id dell'utente di cui vuoi chiudere la richiesta: ");
							id = Integer.valueOf(reader.readLine());
							try { 
								DatabaseConnector dn = new DatabaseConnector();
								con = dn.connessione();
								notificheAdminDao = new NotificheAdminDao(con);
								notificheAdminDao.updateMostra(id, false);
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								con.close();
							}
							break;
						default:
							System.out.println("Scelta non valida! ");
							break;
						}
					}
					sceltaGiusta = true;
					break;
				case 4:
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
