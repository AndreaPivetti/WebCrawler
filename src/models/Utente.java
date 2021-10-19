package models;

public class Utente {

	int id;
	String username;
	String password;
	String ultimo_accesso;
	int max_downloads;

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
	
	public void setUltimo_accesso(String data) {
		this.ultimo_accesso = data;
	}
	
	public String getUltimo_accesso() {
		return this.ultimo_accesso;
	}
	
	public int getMax_downloads() {
		return max_downloads;
	}

	public void setMax_downloads(int max_downloads) {
		this.max_downloads = max_downloads;
	}
	
}
