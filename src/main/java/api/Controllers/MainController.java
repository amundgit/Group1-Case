package api.Controllers;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;

//New imports
import api.Repositories.*;
import api.Models.*;
import api.Pojos.*;

//More imports
import api.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.time.*;

@CrossOrigin
@Controller // This means that this class is a Controller
public class MainController {
	@Autowired // This means to get the bean called userRepository
				// Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;

	@PostMapping(path = "/checkusertype")
	public @ResponseBody Object checkUserType(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		return m;
	}

	/*
	 * This method is used to sign up a new user. It returns an error message if the
	 * user exist, else it returns the created users Id, name and sessionid.
	 */
	@PostMapping(path = "/adduser")
	public @ResponseBody Object addNewUser(@RequestBody Map<String, Object> body) {
		if (userRepository.findByName(body.get("name").toString()) != null) {
			Messages m = new Messages();
			m.setError("Username already exists");
			return m;
		} else {
			String hashedpassword = SecurityUtil.hashPassword(body.get("password").toString());
			String hashedSessionId = SecurityUtil.generateSessionId();
			User user = new User();
			user.setName(body.get("name").toString());
			user.setPassword(hashedpassword);
			user.setSessionId(hashedSessionId);
			userRepository.save(user);
			return userRepository.findSessionByName(user.getName());
		}
	}

	/*
	 * This method is used to get the role of a user. It returns an error message if
	 * parameter is undefined, else it returns the role for the user.
	 */
	@GetMapping(path = "/getuserrole")
	public @ResponseBody Object getUserRole(@RequestParam("userid") int userid) {
		if (userid == 0) {
			Messages m = new Messages();
			m.setError("Parameter userid not defined");
			return m;
		} else {
			return userRepository.findRoleByUserid(userid);
		}
	}

	/*
	 * This method is used at login, to determine the user and what type of role it
	 * has.
	 */
	@PostMapping(path = "/getuser")
	public @ResponseBody Object getUser(@RequestBody Map<String, Object> body) {
		boolean check = false;
		Messages m = new Messages();
		User user = userRepository.findByName(body.get("name").toString());
		if (user != null) {
			if (user.getName().equals(body.get("name"))) {
				check = SecurityUtil.verifyPassword(body.get("password").toString(), user.getPassword());
			}
		}
		if (check) {
			String newSessionId = SecurityUtil.generateSessionId();
			System.out.println(newSessionId);
			user.setSessionId(newSessionId);
			userRepository.save(user);
			m.setMessage(user.getRole().toString());
			m.setSession(newSessionId);
			return m;
		} else {
			m.setError("Failure");
			return m;
		}
	}

	@GetMapping(path = "/getallusers")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}

	@PostMapping(path = "/searchuser")
	public @ResponseBody Object searchUser(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (m.getError() != null) {
			return m;
		} else {
			boolean check = false;
			User user = userRepository.findByName(body.get("inputuser").toString());
			if (user != null) {
				check = user.getStatus().equals("active");
			}
			if (check) {
				m.setMessage("Success");
				return m;
			} else {
				m.setError("Failure");
				return m;
			}
		}
	}

	@PostMapping(path = "/updateusername")
	public @ResponseBody Object updateAUserName(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (m.getError() != null) {
			return m;
		} else {
			User u = userRepository.findByName(body.get("sessionuser").toString());
			u.setName(body.get("newusername").toString());
			userRepository.save(u);
			m.setMessage("Success");
			return m;
		}
	}

	@PostMapping(path = "/deleteuser")
	public @ResponseBody Object deleteAUser(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (m.getRole() != 1) {
			return m;
		} else {
			User u = userRepository.findByName(body.get("sessionuser").toString());
			if (u.getRole() == 1) {
				m.setError("User is an admin and can not be deleted");
			} else {
				u.setStatus("inactive");
				userRepository.save(u);
				m.setMessage("Deleted");
				return m;
			}
		}
	}

	@PostMapping(path = "/makeadmin")
	public @ResponseBody Object makeAdmin(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (m.getRole() != 1) {
			return m;
		} else {
			User u = userRepository.findByName(body.get("inputuser").toString());
			u.setRole(1);
			userRepository.save(u);
			m.setMessage("Updated");
			return m;
		}
	}

	// debug only
	@PostMapping(path = "/addadmin")
	public @ResponseBody Object addNewAdmin(@RequestBody Map<String, Object> body) {
		if (userRepository.findByName(body.get("name").toString()) != null) {
			Messages m = new Messages();
			m.setError("Username already exists");
			return m;
		} else {
			String hashedpassword = SecurityUtil.hashPassword(body.get("password").toString());
			String hashedSessionId = SecurityUtil.generateSessionId();
			User user = new User();
			user.setRole(1);
			user.setName(body.get("name").toString());
			user.setPassword(hashedpassword);
			user.setSessionId(hashedSessionId);
			userRepository.save(user);
			return userRepository.findSessionByName(user.getName());
		}
	}
}