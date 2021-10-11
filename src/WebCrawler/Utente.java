package WebCrawler;

import java.sql.Date;

public class Utente {

	int id;
	String username;
	String password;
	Date ultimo_accesso;
	boolean utente_attivo;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setUsername(String name) {
		this.username = name;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setPassword(String pass) {
		this.password = pass;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setUltimo_accesso(Date data) {
		this.ultimo_accesso = data;
	}
	
	public Date getUltimo_accesso() {
		return this.ultimo_accesso;
	}
	
	public void setUtente_attivo(boolean attivo) {
		this.utente_attivo = attivo;
	}
	
	public boolean getUtente_attivo() {
		return this.utente_attivo;
	}
	
}
