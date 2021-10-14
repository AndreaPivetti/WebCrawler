package crawler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import config.DatabaseConnector;
import dao.DownloadsDao;
import models.Downloads;
import utils.EstraiDataOrario;

public class WebCrawler {

	public boolean errori = false;
	
	public void crawler(String url) throws IOException, SQLException {
		
		Connection con;
		DatabaseConnector dn = new DatabaseConnector();
		con = dn.connessione();
		Downloads downloadInfo = new Downloads();
		downloadInfo.setPagina_web(url);
		DownloadsDao downloadDao = new DownloadsDao(con);
		ScaricaImmagine scarica = new ScaricaImmagine();
		EstraiDataOrario data = new EstraiDataOrario();
		String path;
		String pathFinale;
		SalvaImmagini destinazioneImmagine = new SalvaImmagini();
		path = destinazioneImmagine.creaDirectory();
		pathFinale = destinazioneImmagine.creaDirectoryDownloadSingolo(path);
		
		downloadInfo.setOra_download(data.estraiData());
		
		try {
			Document doc = Jsoup.connect(url).get();	//prende l'URL 
			Elements images = doc.select("img");	//seleziona e contiene le immagini presenti nell'URL inserito
			List<String> urlImmagini = new ArrayList<String>();
			long startTime = System.currentTimeMillis();	//inizio calcolo del tempo di download
			for (Element image : images) {	//scorre tutte le immagini contenute in Elements images
				urlImmagini.add(image.attr("abs:src"));
				String[] immagine;
				immagine = image.attr("src").split("/");	//divide l'URL dell'immagine in più stringhe
				String nomeImmagine = immagine[(immagine.length) - 1];	//estrae il nome dell'immagine
				scarica.scaricaImmagine(image.attr("abs:src"), nomeImmagine, pathFinale);
			}
			long finish = System.currentTimeMillis();	//fine calcolo del tempo di download
			long duration = finish - startTime;
			duration = ( finish - startTime)/1000;	//trasformazione del tempo di download da millisecondi a secondi
			downloadInfo.setDurata_download(duration);

			downloadInfo.setNumero_immagini(urlImmagini.size());
			
		} catch (Exception exc) {
			System.out.println("Si è verificato un errore \n");
			errori = true;
		}
		if (errori == false) {
			downloadInfo.setEsito_download("OK");
		} else if (errori == true) {
			downloadInfo.setEsito_download("KO");
		}
		try {
			downloadDao.save(downloadInfo);
		} catch(Exception exc) {
			System.out.println(exc.getMessage());
			exc.printStackTrace();
		}
	}

}