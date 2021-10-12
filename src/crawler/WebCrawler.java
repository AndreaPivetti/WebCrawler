package crawler;

import java.io.IOException;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {

	public void crawler(String url) throws IOException {
		
		Download scarica = new Download();
		try {
			Document doc = Jsoup.connect(url).get();
			Elements images = doc.select("img");
			List<String> urlImmagini = new ArrayList<String>();
			for (Element image : images) {
				urlImmagini.add(image.attr("src"));
				String[] immagine;
				immagine = image.attr("src").split("/");
				String nomeImmagine = immagine[(immagine.length) - 1];
				scarica.scaricaImmagine(url, nomeImmagine);
			}
		} catch (Exception exc) {
			System.out.println("Si è verificato un errore \n");
		}
	}

}
