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
import api.CompositeIds.ResultId;

//More imports
import api.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.time.*;

@CrossOrigin
@Controller // This means that this class is a Controller
@RequestMapping("/results")
public class ResultController {
	@Autowired
	ResultRepository resultRepository;

	@Autowired
	MatchRepository matchRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TeamRepository teamRepository;

	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<Result> getAllResults() {
		return resultRepository.findAll();
	}

	@PostMapping(path = "/add")
	public @ResponseBody Messages addResult(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		String sessionid = body.get("sessionid").toString();
		String sessionuser = body.get("sessionuser").toString();
		m = SecurityUtil.verifySession(sessionid, sessionuser, userRepository);
		if (m.getRole() != 1) {
			return m;
		} else {
			boolean check = true;
			Integer match_id = Integer.parseInt(body.get("match_id").toString());
			String team_id = body.get("team_id").toString();
			//WORKS, but is unnecessary. Exclusion makes this add or update as necessary
			ResultId result_id = new ResultId(teamRepository.getByTeamId(team_id),matchRepository.getById(match_id));
			/*Result existenceCheck = resultRepository.getById(result_id);
			// Actually do stuff
			if (existenceCheck != null) {
				check = false;
			}*/
			if (check) {
				String score = body.get("score").toString();
				String result = body.get("result").toString();
				Result r = new Result();
				r.setId(result_id);
				r.setScore(score);
				r.setResult(result);
				resultRepository.save(r);
				m.setMessage("Success");
			} else {
				m.setError("Error: Result exists");
			}
			return m;
		}
	}
}