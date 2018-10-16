package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CookieValue;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;

@Controller
public class GreetingController {

	@GetMapping("/")
	public String greeting(HttpServletResponse response, HttpServletRequest request) {
		boolean cookieExist = false;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if ((c.getName().equals("username")) && (!c.getValue().equals("default"))) {
					cookieExist = true;
				}
			}
		}
		if (cookieExist) {
			return "greeting";
		} else {
			response.addCookie(new Cookie("username", "default"));
			return "login";
		}
	}

}