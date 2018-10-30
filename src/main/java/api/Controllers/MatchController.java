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

@CrossOrigin
@Controller // This means that this class is a Controller
@RequestMapping("/matches")
public class MatchController {
	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private SeasonRepository seasonRepository;

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private TeamRepository teamRepository;

	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<Match> getAllMatches() {
		return matchRepository.findAll();
	}

	/**
	 * This method creates a new match
	 * 
	 * @param newMatch
	 * @return
	 */
	@PostMapping(path = "/add")
	public @ResponseBody Object addMatch(@RequestBody Map<String, Object> body) {
		boolean check = false;
		check = true; //just for testing: unsure how to enforce "uniqueness" sensibly
		Messages msg = new Messages();
		/*Address address = addressRepository.getByAddress(body.get("address_line_1").toString());
		if (address == null) {
			check = true;
		}*/
		if (check) {
			String dateArr[] = body.get("match_date").toString().split("-");
			LocalDate date = LocalDate.of(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]), Integer.parseInt(dateArr[2]));
			Match m = new Match();
			m.setMatchDate(date);
			Integer season_id = Integer.parseInt(body.get("season_id").toString());
			Integer location_id = Integer.parseInt(body.get("location_id").toString());
			String home_team_id = body.get("home_team_id").toString();
			String away_team_id = body.get("away_team_id").toString();
			m.setSeasonId(seasonRepository.getById(season_id));
			m.setLocationId(locationRepository.getById(location_id));
			m.setHomeTeamId(teamRepository.getByTeamId(home_team_id));
			m.setAwayTeamId(teamRepository.getByTeamId(away_team_id));
			matchRepository.save(m);
			// Return the id the new address got in the database.
			msg.setMessage(m.getId().toString());
		} else {
			msg.setError("Failure, match was not created.");
		}
		return msg;
	}
}
