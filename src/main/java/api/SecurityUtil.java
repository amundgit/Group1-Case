package api;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.apache.commons.lang3.RandomStringUtils;



public class SecurityUtil {

	public static String hashPassword (String password) {
		String hashedPass = BCrypt.hashpw(password, BCrypt.gensalt());
		return hashedPass;
	}

	public static Boolean verifyPassword (String inputPassword, String storedHash) {
		if (BCrypt.checkpw(inputPassword, storedHash)) {
		    return true;
		} else {
		    return false;
		}
	}

	public static String generateSessionId () {
		String generatedString = RandomStringUtils.randomAlphabetic(20);
 		String sessionId = hashPassword(generatedString);
 		return sessionId;
	}

	public static boolean verifySessionId(String inputSession, String storedSession) {
		if(inputSession.equals(storedSession)) {
			return true;
		} else {
			return false;
		}
	}

}
