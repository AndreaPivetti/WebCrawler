package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import models.Downloads;

public class DownloadsDao implements Dao<Downloads> {

	Connection connetti;
	
	public DownloadsDao(Connection connetti) {
		this.connetti = connetti;
	}
	
	@Override
	public Downloads get(int id) throws SQLException {
		
		return null;
	}

	@Override
	public List getAll() {
		
		return null;
	}
	
	@Override


	public void save(Downloads t) throws SQLException {
		String insert = "INSERT INTO `web_crawler`.`download` (`pagina_web`, `numero_immagini`, `ora_download`, `durata_download`, `esito_download`) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement statement;
		statement = connetti.prepareStatement(insert);
		statement.setString(1, t.getPagina_web());
		statement.setInt(2, t.getNumero_immagini());
		statement.setString(3, t.getOra_download());
		statement.setLong(4, t.getDurata_download());
		statement.setString(5, t.getEsito_download());
		statement.executeUpdate();
	}

	@Override
	public void update(Downloads t, String[] params) {
		
		
	}

	@Override
	public void delete(Downloads t) {
		
		
	}

}
