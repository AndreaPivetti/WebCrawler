package dao;

import java.sql.*;
import java.util.List;
import login.Sessione;
import models.Downloads;
import models.Utente;

public class UtenteDao implements Dao<Utente> {

	Connection connetti;
	

	public UtenteDao(Connection connetti) {
		this.connetti = connetti;
	}
	
	public Utente trasformaResultSetInOggetto(ResultSet rs) throws SQLException {
		Utente user = new Utente();
		user.setId(rs.getInt("id"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setUltimo_accesso(rs.getString("ultimo_accesso"));
		user.setMax_downloads(rs.getInt("max_downloads"));
		return user;
	}

	@Override
	public Utente get(int id) throws SQLException {
		PreparedStatement statement;
		statement = connetti.prepareStatement("SELECT * from utenti WHERE id = ?");
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		Utente user = new Utente();
		while (rs.next()) {
			
			user = trasformaResultSetInOggetto(rs);
			
		}
		//System.out.println(user.getId() + "  " + user.getUsername());
		return user;
	}
	
	public Utente getByUsername(String username) throws SQLException {
		PreparedStatement statement;
		statement = connetti.prepareStatement("SELECT * from utenti WHERE username = ?");
		statement.setString(1, username);
		ResultSet rs = statement.executeQuery();
		Utente user = new Utente();
		while (rs.next()) {
			
			user = trasformaResultSetInOggetto(rs);

		}
		//System.out.println(user.getId() + "  " + user.getUsername());
		return user;
	}

	public int getTotalUserDownload(String username) throws SQLException {
		int conteggio_download = 0;
		String join = "SELECT COUNT(*) AS conteggio_download FROM utenti INNER JOIN download ON utenti.id = download.utente_id WHERE utenti.username = ?;";
		PreparedStatement statement;
		statement = connetti.prepareStatement(join);
		statement.setString(1, username);
		ResultSet rs = statement.executeQuery();
		rs.next();
		conteggio_download = rs.getInt("conteggio_download");
		Sessione.setDownload_effettuati(conteggio_download);
		return conteggio_download;
	}
	
	@Override
	public List<Utente> getAll() {
		return null;
	}

	@Override
	public void save(Utente t) throws SQLException {

		String insert = "INSERT INTO `web_crawler`.`utenti` (`username`, `password`, `ultimo_accesso`, `utente_attivo`) VALUES (?, ?, NULL, NULL)";
		PreparedStatement statement;
		statement = connetti.prepareStatement(insert);
		statement.setString(1, t.getUsername());
		statement.setString(2, t.getPassword());
		statement.executeUpdate();
		
	}

	@Override
	public void update(Utente t, String[] params) throws SQLException {
		
		String update = "UPDATE `web_crawler`.`utenti` SET `ultimo_accesso` = ? WHERE id = ?";
		PreparedStatement statement;
		statement = connetti.prepareStatement(update);
		statement.setString(1, params[0]);
		statement.setInt(2, t.getId());
		statement.executeUpdate();
		
	}

	@Override
	public void delete(Utente t) {

	}

}
