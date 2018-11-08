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
import api.CompositeIds.Match_positionId;

//More imports
import api.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.time.*;

@CrossOrigin
@Controller // This means that this class is a Controller
@RequestMapping("/matchpositions")
public class Match_positionController {
	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private Match_positionRepository match_positionRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<Match_position> getAllMatchPositions() {
		return match_positionRepository.findAll();
	}

	@GetMapping(path = "/getallactive")
	public @ResponseBody Iterable<Match_position> getAllActiveMatchPositions() {
		return match_positionRepository.getAllActive();
	}

	/**
	 * This method creates a new matchposition
	 * 
	 * @param newMatchPosition
	 * @return
	 */
	@PostMapping(path = "/add")
	public @ResponseBody Object addMatchPosition(@RequestBody Map<String, Object> body) {
		Messages msg = new Messages();
		msg = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (msg.getRole() != 1) {
			return msg;
		} else {
			boolean check = true;
			Integer player_id = Integer.parseInt(body.get("player_id").toString());
			Integer match_id = Integer.parseInt(body.get("match_id").toString());
			Match_positionId position_id = new Match_positionId(playerRepository.getById(player_id),matchRepository.getById(match_id));
			/*Match_position existenceCheck = match_positionRepository.getById(position_id);
			//works, unnecessary. Exclusion makes this add and update
			if(existenceCheck != null){
				check = false;
				msg.setMessage("Already exists");
			}*/
			if (check) {
				Match_position mp = new Match_position();
				mp.setId(position_id);
				String position = body.get("position").toString();	
				mp.setPosition(position);
				//mp.setPlayerId(playerRepository.getById(player_id));
				//mp.setMatchId(matchRepository.getById(match_id));
				match_positionRepository.save(mp);
				msg.setMessage(mp.getId().toString());
			}
			return msg;
		}
	}

	@PostMapping(path = "/adddefaultpositions")
	public @ResponseBody Object addDefaultPositions(@RequestBody Map<String, Object> body) {
		Messages msg = new Messages();
		msg = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(), userRepository);
		if (msg.getRole() != 1) {
			return msg;
		} else {
			Integer match_id = Integer.parseInt(body.get("match_id").toString());
			String home_team_id = body.get("home_team_id").toString();
			String away_team_id = body.get("away_team_id").toString();
			List<Player> homeTeam = playerRepository.getByTeam(home_team_id);
			List<Player> awayTeam = playerRepository.getByTeam(away_team_id);

			for(Player p : homeTeam){
				Match_positionId position_id = new Match_positionId(p,matchRepository.getById(match_id));	
				Match_position mp = new Match_position();
				mp.setId(position_id);
				String position = p.getNormalPosition();	
				mp.setPosition(position);
				match_positionRepository.save(mp);
			}
			for(Player p : awayTeam){
				Match_positionId position_id = new Match_positionId(p,matchRepository.getById(match_id));	
				Match_position mp = new Match_position();
				mp.setId(position_id);
				String position = p.getNormalPosition();	
				mp.setPosition(position);
				match_positionRepository.save(mp);
			}
			msg.setMessage("Default positions set");
			return msg;
		}
	}

	@PostMapping(path = "/delete")
	public @ResponseBody Object deleteMatchPosition(@RequestBody Map<String, Object> body) {
		Messages msg = new Messages();
		msg = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (msg.getRole() != 1) {
			return msg;
		} else {
			boolean check = true;
			Integer player_id = Integer.parseInt(body.get("player_id").toString());
			Integer match_id = Integer.parseInt(body.get("match_id").toString());
			Match_positionId position_id = new Match_positionId(playerRepository.getById(player_id),matchRepository.getById(match_id));
			Match_position mp = match_positionRepository.getById(position_id);
			if(mp == null){
				check = false;
				msg.setMessage("Invalid combination");
			}
			if (check) {	
				mp.setStatus("inactive");
				match_positionRepository.save(mp);
				msg.setMessage("Successfully deleted");
			}
			return msg;
		}
	}
}