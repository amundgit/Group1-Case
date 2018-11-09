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

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private FavouriteTeamsRepository favouriteTeamsRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@GetMapping(path = "/devgetall")
	public @ResponseBody Iterable<Team> getAllTeams() {
		return teamRepository.findAll();
	}

	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<Team> getAllActiveTeams() {
		return teamRepository.getAllActive();
	}

	@PostMapping(path = "/getteamsbyowner")
	public @ResponseBody Iterable<Team> getTeamsByOwner(@RequestBody Map<String, Object> body){
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (m.getError() != null) {
			return null;
		} else {
			Integer owner_id = Integer.parseInt(body.get("owner_id").toString());
			return teamRepository.getTeamsByOwner(owner_id);
		}
	}

	@PostMapping(path = "/getteamsbycoach")
	public @ResponseBody Iterable<Team> getTeamsByCoach(@RequestBody Map<String, Object> body){
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (m.getError() != null) {
			return null;
		} else {
			Integer coach_id = Integer.parseInt(body.get("coach_id").toString());
			return teamRepository.getTeamsByCoach(coach_id);
		}
	}

	@PostMapping(path = "/getteamsbyassociation")
	public @ResponseBody Iterable<Team> getTeamsByAssociation(@RequestBody Map<String, Object> body){
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (m.getError() != null) {
			return null;
		} else {
			Integer association_id = Integer.parseInt(body.get("association_id").toString());
			return teamRepository.getTeamsByAssociationId(association_id);
		}
	}

	@PostMapping(path = "/add")
	public @ResponseBody Messages addTeam(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (m.getRole() != 1) {
			return m;
		} else {
			boolean check = false;
			String team_id = body.get("team_name").toString();
			Integer owner_id = Integer.parseInt(body.get("team_owner").toString());
			Integer association_id = Integer.parseInt(body.get("team_association").toString());
			Integer coach_id = Integer.parseInt(body.get("team_coach").toString());
			Integer location_id = Integer.parseInt(body.get("team_location").toString());
			Team existenceCheck = teamRepository.getByTeamId(team_id);
			// Actually do stuff
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
				m.setMessage(t.getId().toString());
			} else {
				m.setError("Error: Team exists");
			}
			return m;
		}
	}

	@PostMapping(path = "/update")
	public @ResponseBody Messages updateTeam(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (m.getRole() != 1) {
			return m;
		} else {
			boolean check = true;
			String team_id = body.get("team_name").toString();
			
			Team t = teamRepository.getByTeamId(team_id);
			// Actually do stuff
			if (t == null) {
				check = false;
				m.setError("Error: Invalid team name");
			}
			if (check) {
				Integer owner_id = Integer.parseInt(body.get("team_owner").toString());
				Integer association_id = Integer.parseInt(body.get("team_association").toString());
				Integer coach_id = Integer.parseInt(body.get("team_coach").toString());
				Integer location_id = Integer.parseInt(body.get("team_location").toString());
				t.setOwnerId(ownerRepository.getById(owner_id));
				t.setAssociationId(associationRepository.getById(association_id));
				t.setCoachId(coachRepository.getById(coach_id));
				t.setLocationId(locationRepository.getById(location_id));
				teamRepository.save(t);
				m.setMessage("Successfully updated");
			}
			return m;
		}
	}

	@PostMapping(path = "/delete")
	public @ResponseBody Messages deleteTeam(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (m.getRole() != 1) {
			return m;
		} else {
			boolean check = true;
			String team_id = body.get("team_id").toString();
			Team t = teamRepository.getByTeamId(team_id);
			List<FavouriteTeams> teamFavourites = favouriteTeamsRepository.getByTeamId(team_id);
			List<Match> teamMatches = matchRepository.getByTeamId(team_id);
			List<Player> teamPlayers = playerRepository.getByTeam(team_id);
			// Actually do stuff
			if (t == null) {
				check = false;
				m.setError("Error: Invalid team name");
			} else if (teamPlayers.size() != 0){
				check = false;
				m.setError("Error: Player(s) assigned to this team, reassign or delete them before deleting the team");
			} else if (teamMatches.size() != 0){
				check = false;
				m.setError("Error: Match(es) assigned to this team, reassign or delete them before deleting the team");
			}
			if (check) {
				for(FavouriteTeams f : teamFavourites){
					f.setStatus("inactive");
					favouriteTeamsRepository.save(f);
				}
				t.setStatus("inactive");
				teamRepository.save(t);
				m.setMessage("Successfully deleted");
			}
			return m;
		}
	}
}
