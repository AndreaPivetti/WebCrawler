package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Hashing {
	
	public String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		String hashedPass = Base64.getEncoder().encodeToString(md.digest()).toUpperCase();
		return hashedPass;
	}
	
}
