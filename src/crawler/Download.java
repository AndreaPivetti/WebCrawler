package crawler;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Download {

	public void scaricaImmagine(String src, String nomeImmagine) {

		try (BufferedInputStream in = new BufferedInputStream(new URL(src).openStream());
				FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Utente\\Desktop\\immagini_scaricate_webCrawler\\" + nomeImmagine)) {
			System.out.println(src + "	" + "C:\\Users\\Utente\\Desktop\\" + nomeImmagine);
			byte dataBuffer[] = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				fileOutputStream.write(dataBuffer, 0, bytesRead);
			}
		} catch (IOException e) {
			System.out.println("Si è verificato un problema ");
		}

	}

}
