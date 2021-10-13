package models;

import java.sql.Date;
import java.sql.Time;

public class Downloads {

	int id;
	String pagina_web;
	int numero_immagini;
	String ora_download;
	Long durata_download;
	String esito_download;
	
	public String getPagina_web() {
		return pagina_web;
	}
	
	public void setPagina_web(String pagina_web) {
		this.pagina_web = pagina_web;
	}
	public int getNumero_immagini() {
		return numero_immagini;
	}
	
	public void setNumero_immagini(int numero_immagini) {
		this.numero_immagini = numero_immagini;
	}
	
	public String getOra_download() {
		return ora_download;
	}
	
	public void setOra_download(String ora_download) {
		this.ora_download = ora_download;
	}
	
	public long getDurata_download() {
		return durata_download;
	}
	
	public void setDurata_download(long durata_download) {
		this.durata_download = durata_download;
	}
	
	public String getEsito_download() {
		return esito_download;
	}
	
	public void setEsito_download(String esito_download) {
		this.esito_download = esito_download;
	}
	
}
