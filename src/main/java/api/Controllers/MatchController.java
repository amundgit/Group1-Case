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

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Match_goalRepository match_goalRepository;

	@Autowired
	private Match_positionRepository match_positionRepository;

	@Autowired
	private ResultRepository resultRepository;

	@GetMapping(path = "/devgetall")
	public @ResponseBody Iterable<Match> getAllMatches() {
		return matchRepository.findAll();
	}

	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<Match> getAllActiveMatches() {
		return matchRepository.getAllActive();
	}

	/**
	 * This method creates a new match
	 * 
	 * @param newMatch
	 * @return
	 */
	@PostMapping(path = "/add")
	public @ResponseBody Object addMatch(@RequestBody Map<String, Object> body) {
		Messages msg = new Messages();
		msg = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (msg.getRole() != 1) {
			return msg;
		} else {
			boolean check = true;
			Integer season_id = Integer.parseInt(body.get("season_id").toString());
			String home_team_id = body.get("home_team_id").toString();
			String away_team_id = body.get("away_team_id").toString();
			String dateArr[] = body.get("match_date").toString().split("-");
			LocalDate date = LocalDate.of(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]), Integer.parseInt(dateArr[2]));
			Season season = seasonRepository.getById(season_id);
			LocalDate start = season.getStartDate();
			LocalDate end = season.getEndDate();
			
			//works, check default = false
			/*if(!(home_team_id.equals(away_team_id)) && (date.isAfter(start)) && (date.isBefore(end))){
				check = true;
			}*/
			//Test with better errors, check = true
			if(home_team_id.equals(away_team_id)){
				check = false;
				msg.setError("Home team and away team cannot be the same");
			} else if(!(date.isAfter(start)) || !(date.isBefore(end))){
				check = false;
				msg.setError("Date of match not in season");
			}
			if (check) {
				Match m = new Match();
				m.setMatchDate(date);	
				Integer location_id = Integer.parseInt(body.get("location_id").toString());
				m.setSeasonId(season);
				m.setLocationId(locationRepository.getById(location_id));
				m.setHomeTeamId(teamRepository.getByTeamId(home_team_id));
				m.setAwayTeamId(teamRepository.getByTeamId(away_team_id));
				matchRepository.save(m);
				// Return the id the new address got in the database.
				msg.setMessage(m.getId().toString());
			} /*else {
				msg.setError("Failure, match was not created.");
			}*/
			return msg;
		}
	}

	@PostMapping(path = "/update")
	public @ResponseBody Object updateMatch(@RequestBody Map<String, Object> body) {
		Messages msg = new Messages();
		msg = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (msg.getRole() != 1) {
			return msg;
		} else {
			boolean check = true;
			Integer match_id = Integer.parseInt(body.get("match_id").toString());
			Integer season_id = Integer.parseInt(body.get("season_id").toString());
			String home_team_id = body.get("home_team_id").toString();
			String away_team_id = body.get("away_team_id").toString();
			String dateArr[] = body.get("match_date").toString().split("-");
			LocalDate date = LocalDate.of(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]), Integer.parseInt(dateArr[2]));
			Season season = seasonRepository.getById(season_id);
			LocalDate start = season.getStartDate();
			LocalDate end = season.getEndDate();
			Match m = matchRepository.getById(match_id);			
			if(home_team_id.equals(away_team_id)){
				check = false;
				msg.setError("Home team and away team cannot be the same");
			} else if(!(date.isAfter(start)) || !(date.isBefore(end))){
				check = false;
				msg.setError("Date of match not in season");
			} else if(m == null){
				check = false;
				msg.setError("Invalid match id");
			}
			if (check) {
				m.setMatchDate(date);	
				Integer location_id = Integer.parseInt(body.get("location_id").toString());
				m.setSeasonId(season);
				m.setLocationId(locationRepository.getById(location_id));
				m.setHomeTeamId(teamRepository.getByTeamId(home_team_id));
				m.setAwayTeamId(teamRepository.getByTeamId(away_team_id));
				matchRepository.save(m);
				// Return the id the new address got in the database.
				msg.setMessage(m.getId().toString());
			}
			return msg;
		}
	}

	@PostMapping(path = "/delete")
	public @ResponseBody Object deleteMatch(@RequestBody Map<String, Object> body) {
		Messages msg = new Messages();
		msg = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (msg.getRole() != 1) {
			return msg;
		} else {
			boolean check = true;
			Integer match_id = Integer.parseInt(body.get("match_id").toString());
			Match m = matchRepository.getById(match_id);
			if(m == null){
				check = false;
				msg.setError("Invalid match id");
			}
			if (check) {
				List<Match_goal> matchGoals = match_goalRepository.getByMatchId(match_id);
				List<Match_position> matchPositions = match_positionRepository.getByMatchId(match_id);
				List<Result> matchResults = resultRepository.getByMatchId(match_id);
				for(Match_goal mg : matchGoals){
					mg.setStatus("inactive");
					match_goalRepository.save(mg);
				}
				for(Match_position mp : matchPositions){
					mp.setStatus("inactive");
					match_positionRepository.save(mp);
				}
				for(Result r : matchResults){
					r.setStatus("inactive");
					resultRepository.save(r);
				}
				m.setStatus("inactive");
				matchRepository.save(m);
				// Return the id the new address got in the database.
				msg.setMessage("Succesfully deleted");
			}
			return msg;
		}
	}
}
