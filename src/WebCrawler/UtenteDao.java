package WebCrawler;

import java.sql.*;
import java.util.List;

public class UtenteDao implements Dao<Utente> {

	Connection connetti;
	

	public UtenteDao(Connection connetti) {
		this.connetti = connetti;
	}

	@Override
	public Utente get(int id) throws SQLException {
		PreparedStatement statement;
		statement = connetti.prepareStatement("SELECT * from utenti WHERE id= ?");
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		Utente user = new Utente();
		while (rs.next()) {
			
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));

		}
		System.out.println(user.getId() + "  " + user.getUsername());
		return user;
	}

	@Override
	public List<Utente> getAll() {
		return null;
	}

	@Override
	public void save(Utente t) {

	}

	@Override
	public void update(Utente t, String[] params) {

	}

	@Override
	public void delete(Utente t) {

	}

}
