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
@RequestMapping("/coaches")
public class CoachController {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private CoachRepository coachRepository;

	@Autowired
	private UserRepository userRepository;


	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<Coach> getAllCoaches() {
		return coachRepository.findAll();
	}

	@PostMapping(path = "/assign")
	public @ResponseBody Messages assignCoach(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),userRepository);
		if(m.getRole() != 1) {
			return m;
		} else {
			boolean check = false;
			Integer person_id = Integer.parseInt(body.get("person_id").toString());
			Coach existenceCheck = coachRepository.getByPersonId(person_id);
			if (existenceCheck == null) {
				check = true;
			}
			if (check){
				Coach c = new Coach();
				c.setPersonId(personRepository.getById(person_id));
				c = coachRepository.save(c);
				m.setMessage("Success");
			} else {
				m.setError("Person already a coach");
			}
			return m;
		}
	}
}
