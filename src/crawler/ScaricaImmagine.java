package crawler;

import java.io.*;
import java.net.URL;

public class ScaricaImmagine {
	
	WebCrawler web_crawler = new WebCrawler();

	public void scaricaImmagine(String src, String nomeImmagine, String pathFinale) {

		if(nomeImmagine.contains("?")){
			nomeImmagine = "nuova_immagine.png";
		}
		String pathDownload = pathFinale + System.getProperty("file.separator") + nomeImmagine;
		try (BufferedInputStream in = new BufferedInputStream(new URL(src).openStream());
				FileOutputStream fileOutputStream = new FileOutputStream(pathDownload);
				BufferedOutputStream bout = new BufferedOutputStream(fileOutputStream);) {
			System.out.println(pathDownload);
			byte dataBuffer[] = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				bout.write(dataBuffer, 0, bytesRead);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			web_crawler.errori = true;
		}

	}

}
