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

	@Autowired
	private TeamRepository teamRepository;


	@GetMapping(path = "/devgetall")
	public @ResponseBody Iterable<Coach> getAllCoaches() {
		return coachRepository.findAll();
	}

	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<Coach> getAllActiveCoaches() {
		return coachRepository.getAllActive();
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

	@PostMapping(path = "/update")
	public @ResponseBody Messages updateCoach(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),userRepository);
		if(m.getRole() != 1) {
			return m;
		} else {
			boolean check = true;
			Integer coach_id = Integer.parseInt(body.get("coach_id").toString());
			Coach coach = coachRepository.getById(coach_id);
			if (coach == null) {
				check = false;
				m.setError("Error: Invalid coach ID");
			}
			if (check){
				Integer person_id = Integer.parseInt(body.get("person_id").toString());
				coach.setPersonId(personRepository.getById(person_id));
				coach = coachRepository.save(coach);
				m.setMessage("Success");
			}
			return m;
		}
	}

	@PostMapping(path = "/delete")
	public @ResponseBody Messages deleteCoach(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),userRepository);
		if(m.getRole() != 1) {
			return m;
		} else {
			boolean check = true;
			Integer coach_id = Integer.parseInt(body.get("coach_id").toString());
			Coach coach = coachRepository.getById(coach_id);
			List<Team> coachTeams = teamRepository.getTeamsByCoach(coach_id);
			if (coach == null) {
				check = false;
				m.setError("Error: Invalid coach ID");
			} else if(coachTeams.size() != 0){
				check = false;
				m.setError("Error: Team(s) assigned to this coach, reassign or delete them before deleting the coach");
			}
			if (check){
				coach.setStatus("inactive");
				coach = coachRepository.save(coach);
				m.setMessage("Successfully deleted");
			}
			return m;
		}
	}
}
