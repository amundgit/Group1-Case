package api.Controllers;

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

@Controller // This means that this class is a Controller
@RequestMapping("/players")
public class PlayerController {
	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private TeamRepository teamRepository;

	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<Player> getAllPlayers() {
		return playerRepository.findAll();
	}

	@PostMapping(path = "/assign")
	public @ResponseBody Messages addPlayer(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		boolean check = false;
		String normal_position = body.get("normal_position").toString();
		Integer number = Integer.parseInt(body.get("number").toString());
		Integer person_id = Integer.parseInt(body.get("person_id").toString());
		String team_id = body.get("team_id").toString();
		Player existenceCheck = playerRepository.getByPersonId(person_id);
		//Actually do stuff
		
		if (existenceCheck == null) {
			check = true;
		}
		if (check) {
			Player p = new Player();
			p.setNormalPosition(normal_position);
			p.setNumber(number);
			p.setPersonId(personRepository.getById(person_id));
			p.setTeamId(teamRepository.getByTeamId(team_id));
			playerRepository.save(p);
			m.setMessage("Success");
		} else {
			m.setError("Error: Person already a player");
		}
		return m;
	}
}