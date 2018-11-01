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
@RequestMapping("/matchpositions")
public class Match_positionController {
	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private Match_positionRepository match_positionRepository;

	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<Match_position> getAllMatchPositions() {
		return match_positionRepository.findAll();
	}

	/**
	 * This method creates a new matchposition
	 * 
	 * @param newMatchPosition
	 * @return
	 */
	@PostMapping(path = "/add")
	public @ResponseBody Object addMatchPosition(@RequestBody Map<String, Object> body) {
		boolean check = true;
		Messages msg = new Messages();
		Integer player_id = Integer.parseInt(body.get("player_id").toString());
		Integer match_id = Integer.parseInt(body.get("match_id").toString());
		Match_position existenceCheck = match_positionRepository.getByPlayerAndMatch(player_id, match_id);
		//works, check default = false
		if(existenceCheck != null){
			check = false;
			msg.setError("Failure, position was not created.");
		}
		if (check) {
			Match_position mp = new Match_position();
			String position = body.get("position").toString();	
			
			mp.setPosition(position);
			mp.setPlayerId(playerRepository.getById(player_id));
			mp.setMatchId(matchRepository.getById(match_id));
			match_positionRepository.save(mp);
			msg.setMessage(mp.getId().toString());
		}
		return msg;
	}
}