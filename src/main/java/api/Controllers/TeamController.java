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
@RequestMapping("/teams")
public class TeamController {
	@Autowired
	private AssociationRepository associationRepository;

	@Autowired
	private CoachRepository coachRepository;

	@Autowired
	private OwnerRepository ownerRepository;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private LocationRepository locationRepository;


	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<Team> getAllTeams() {
		return teamRepository.findAll();
	}

	@PostMapping(path = "/add")
	public @ResponseBody Messages addTeam(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		boolean check = false;
		String team_id = body.get("team_id").toString();
		Integer owner_id = Integer.parseInt(body.get("owner_id").toString());
		Integer association_id = Integer.parseInt(body.get("association_id").toString());
		Integer coach_id = Integer.parseInt(body.get("coach_id").toString());
		Integer location_id = Integer.parseInt(body.get("location_id").toString());
		Team existenceCheck = teamRepository.getByTeamId(team_id);
		//Actually do stuff
		
		if (existenceCheck == null) {
			check = true;
		}
		if (check) {
			Team t = new Team(team_id);
			t.setOwnerId(ownerRepository.getById(owner_id));
			t.setAssociationId(associationRepository.getById(association_id));
			t.setCoachId(coachRepository.getById(coach_id));
			t.setLocationId(locationRepository.getById(location_id));
			teamRepository.save(t);
			m.setMessage("Success");
		} else {
			m.setError("Error: Team exists");
		}
		return m;
	}
}