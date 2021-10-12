package crawler;

import java.io.*;
import java.net.URL;

public class Download {

	public void scaricaImmagine(String src, String nomeImmagine) {

		String pathDownload = "C:\\Users\\Utente\\Desktop\\immagini_scaricate_webCrawler\\" + nomeImmagine;
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
		}

	}

}
