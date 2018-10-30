package api;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.apache.commons.lang3.RandomStringUtils;

import api.Repositories.*;
import api.Pojos.*;

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

	public static Messages verifySession(String inputSessionId, String inputUser, UserRepository userRepository) {
		SessionVerifyInfo sessionVerifyInfo = userRepository.findSessionVerifyByUsername(inputUser);
		String sessionId = sessionVerifyInfo.getSessionId();
		Integer role = sessionVerifyInfo.getRole();
		Boolean isSessionValid;
		if (sessionId != null) {
			isSessionValid = SecurityUtil.verifySessionId(inputSessionId, sessionId);
		} else {
			isSessionValid = false;
		}
		Messages m = new Messages();
		if (isSessionValid) {
			m.setRole(role);
			return m;
		} else {
			m.setError("Invalid Session");
			return m;
		}
	}

}
