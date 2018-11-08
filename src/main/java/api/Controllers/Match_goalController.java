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
@RequestMapping("/matchgoals")
public class Match_goalController {
	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private Match_goalRepository match_goalRepository;

	@Autowired
	private Goal_typeRepository goal_typeRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping(path = "/devgetall")
	public @ResponseBody Iterable<Match_goal> getAllMatchGoals() {
		return match_goalRepository.findAll();
	}

	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<Match_goal> getAllActiveMatchGoals() {
		return match_goalRepository.getAllActive();
	}

	@PostMapping(path = "/getbygoaltype/")
	public @ResponseBody Goal_type getType(@RequestBody String type) {
		return goal_typeRepository.getByType(type);
	}

	/**
	 * This method creates a new matchposition
	 * 
	 * @param newMatchPosition
	 * @return
	 */
	@PostMapping(path = "/add")
	public @ResponseBody Object addMatchGoal(@RequestBody Map<String, Object> body) {
		// boolean check = true;
		Messages msg = new Messages();
		msg = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (msg.getRole() != 1) {
			return msg;
		} else {
			Integer player_id = Integer.parseInt(body.get("player_id").toString());
			Integer match_id = Integer.parseInt(body.get("match_id").toString());
			String goal_type = body.get("goal_type").toString();
			System.out.println(goal_type);
			String description = body.get("description").toString();
			/*
			 * Match_position existenceCheck =
			 * match_positionRepository.getByPlayerAndMatch(player_id, match_id); //cant't
			 * think of useful checks if(existenceCheck != null){ check = false;
			 * msg.setError("Failure, match was not created."); }
			 */
			// if (check) {
			Match_goal mg = new Match_goal();
			mg.setDescription(description);
			mg.setGoalTypeId(goal_typeRepository.getByType(goal_type));
			mg.setPlayerId(playerRepository.getById(player_id));
			mg.setMatchId(matchRepository.getById(match_id));
			match_goalRepository.save(mg);
			msg.setMessage(mg.getId().toString());
			// }
			return msg;
		}
	}

	@PostMapping(path = "/update")
	public @ResponseBody Object updateMatchGoal(@RequestBody Map<String, Object> body) {
		Messages msg = new Messages();
		msg = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (msg.getRole() != 1) {
			return msg;
		} else {
			boolean check = true;
			Integer goal_id = Integer.parseInt(body.get("goal_id").toString());
			Match_goal matchgoal = match_goalRepository.getById(goal_id);
			if (matchgoal == null) {
				check = false;
				msg.setError("Error: Invalid match goal id");
			}
			if (check) {
				Integer player_id = Integer.parseInt(body.get("player_id").toString());
				Integer match_id = Integer.parseInt(body.get("match_id").toString());
				String goal_type = body.get("goal_type").toString();
				String description = body.get("description").toString();
				matchgoal.setDescription(description);
				matchgoal.setGoalTypeId(goal_typeRepository.getByType(goal_type));
				matchgoal.setPlayerId(playerRepository.getById(player_id));
				matchgoal.setMatchId(matchRepository.getById(match_id));
				match_goalRepository.save(matchgoal);
				msg.setMessage(matchgoal.getId().toString());
			}
			return msg;
		}
	}

	@PostMapping(path = "/delete")
	public @ResponseBody Object deleteMatchGoal(@RequestBody Map<String, Object> body) {
		Messages msg = new Messages();
		msg = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (msg.getRole() != 1) {
			return msg;
		} else {
			boolean check = true;
			Integer goal_id = Integer.parseInt(body.get("goal_id").toString());
			Match_goal matchgoal = match_goalRepository.getById(goal_id);
			if (matchgoal == null) {
				check = false;
				msg.setError("Error: Invalid match goal id");
			}
			if (check) {
				matchgoal.setStatus("inactive");
				match_goalRepository.save(matchgoal);
				msg.setMessage(matchgoal.getId().toString());
			}
			return msg;
		}
	}
}