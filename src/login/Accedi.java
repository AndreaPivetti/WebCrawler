package login;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public interface Accedi {

	public void verificaPassword() throws IOException, NoSuchAlgorithmException, SQLException;
	
	public void login() throws IOException, NoSuchAlgorithmException, SQLException;
	
	public void registraNuovoUtente() throws NoSuchAlgorithmException, SQLException;
	
}
