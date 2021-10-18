package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import models.NotificheAdmin;
import models.Utente;

public class NotificheAdminDao implements Dao<NotificheAdmin> {

	Connection connetti;
	
	public NotificheAdminDao(Connection connetti) {
		this.connetti = connetti;
	}
	
	public String trasformaResultSetInOggetto(ResultSet rs) throws SQLException {
		String richiesta;
		richiesta = "	" + rs.getInt("utente_id") + "	|	" + rs.getInt("max_downloads");
		return richiesta;
	}
	
	public String controlloTrasformaResultSetInOggetto(ResultSet rs) throws SQLException {
		String controlla;
		controlla = rs.getInt("utente_id") + "|" + rs.getBoolean("mostra");
		return controlla;
	}
	
	@Override
	public NotificheAdmin get(int id) throws SQLException {
		return null;
	}

	@Override
	public List<NotificheAdmin> getAll() {
		return null;
	}
	
	public void mostraNotifiche() throws SQLException {
		
		String select = "SELECT utente_id, max_downloads FROM notifiche_limite_admin INNER JOIN utenti ON notifiche_limite_admin.utente_id = utenti.id WHERE notifiche_limite_admin.mostra = 1;";
		PreparedStatement statement;
		statement = connetti.prepareStatement(select);
		ResultSet rs = statement.executeQuery();
		System.out.println("id utente" + "	|	" + "limite download");
		String notifica;
		while (rs.next()) {
			notifica = trasformaResultSetInOggetto(rs);
			System.out.println(notifica);
		}
		
	}
	
	public void updateMostra(int utente_id, boolean mostra) throws SQLException {
		
		String update = "UPDATE `web_crawler`.`notifiche_limite_admin` SET `mostra` = ? WHERE utente_id = ?";
		PreparedStatement statement;
		statement = connetti.prepareStatement(update);
		statement.setBoolean(1, mostra);
		statement.setInt(2, utente_id);
		statement.executeUpdate();
		
	}
	
	public boolean controllaNotifica(int utente_id) throws SQLException {
		boolean res = true;
		String select = "SELECT utente_id, mostra FROM web_crawler.notifiche_limite_admin;";
		PreparedStatement statement;
		statement = connetti.prepareStatement(select);
		ResultSet rs = statement.executeQuery();
		String controlla;
		while (rs.next()) {
			controlla = controlloTrasformaResultSetInOggetto(rs);
			if(controlla.equals(utente_id + "|" + true)) {
				res = false;
			}
		}
		return res;
	}

	@Override
	public void save(NotificheAdmin t) throws SQLException {

		String insert = "INSERT INTO `web_crawler`.`notifiche_limite_admin` (`utente_id`, `data`, `mostra`) VALUES (?, ?, ?)";
		PreparedStatement statement;
		statement = connetti.prepareStatement(insert);
		statement.setInt(1, t.getUtente_id());
		statement.setString(2, t.getData());
		statement.setBoolean(3, t.getMostra());
		statement.executeUpdate();
		
	}

	@Override
	public void update(NotificheAdmin t, String[] params) throws SQLException {
		
	}

	@Override
	public void delete(NotificheAdmin t) {
		
	}

}
