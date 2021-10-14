package crawler;

import java.io.File;

import models.Downloads;
import utils.EstraiDataOrario;

public class SalvaImmagini {
		
	public String creaDirectory() {
		String path = "";
		if(System.getProperty("os.name").contains("Windows")) {
		   path = System.getenv("userprofile");
		} else if(System.getProperty("os.name").contains("Linux")) {
		   path = System.getenv("HOME");
		}
		String pathFolderName = path + System.getProperty("file.separator") + "web_crawler_data";	//path della cartella da creare
		File folder = new File(pathFolderName);
		if(!folder.isDirectory()){	//Verifica che la cartella non esista già
		    folder.mkdir();	//se non esiste già viene creata
		}		
		return pathFolderName;
	}
	
	public String creaDirectoryDownloadSingolo(String path) {
		String pathFinale;
		EstraiDataOrario data = new EstraiDataOrario();
		pathFinale = path + System.getProperty("file.separator") + data.estraiData();
		File folder = new File(pathFinale);
		if(!folder.isDirectory()){	//Verifica che la cartella non esista già
		    folder.mkdir();	//se non esiste già viene creata
		}	
		return pathFinale;
	}
	
}
